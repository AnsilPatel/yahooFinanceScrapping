package com.scrapping.yahoofinance.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrapping.yahoofinance.model.StockInfo;

@Service
public class ScrappingService {
	private final static Logger LOG = Logger.getLogger(ScrappingService.class.getName());
	@Autowired
	FirebaseInitService firebaseInitService;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

	@PostConstruct
	private void manageStock() {
		scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				UpdateStockData();
			}
		}, 0, 1, TimeUnit.MICROSECONDS);
	}

	void UpdateStockData() {
		try {
			final Document doc = Jsoup.connect("https://finance.yahoo.com/most-active?offset=0&count=100")
					.timeout(50000).get();
			final Elements trElements = doc.select("table").select("tbody").select("tr");
			for (Element trEle : trElements) {
				final Elements tdElememts = trEle.select("td");
				for (int i = 0; i <= tdElememts.size() - 1; i++) {
					final Element tdEle = tdElememts.get(i);
					if (i == 0) {
						raceDocument(tdEle.select("a").attr("href"), tdEle.text());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void raceDocument(String stockDetails, String stockName) {
		try {
			final Document doc = Jsoup.connect("https://finance.yahoo.com" + stockDetails).timeout(20000).get();
			final Elements trElements = doc.select("table").select("tbody").select("tr");
			final Elements previousClosedTd = trElements.get(0).select("td");
			final Elements openTd = trElements.get(1).select("td");
			final Elements bidTd = trElements.get(2).select("td");
			final Elements askTd = trElements.get(3).select("td");
			final Elements dayRangeTd = trElements.get(4).select("td");
			final Elements fiftyTWoWeekRangeTd = trElements.get(5).select("td");
			final Elements volumeTd = trElements.get(6).select("td");
			final Elements avgVolume = trElements.get(7).select("td");
			final Elements marketCap = trElements.get(8).select("td");
			final Elements beta = trElements.get(9).select("td");
			final Elements pERatio = trElements.get(10).select("td");
			final Elements ePS = trElements.get(11).select("td");
			final Elements earningsDate = trElements.get(12).select("td");
			final Elements forwardDividendYield = trElements.get(13).select("td");
			final Elements exDividendDate = trElements.get(14).select("td");
			final Elements oneYearTargetEst = trElements.get(15).select("td");
			final StockInfo stockInfo = new StockInfo(previousClosedTd.get(1).text(), openTd.get(1).text(),
					bidTd.get(1).text(), askTd.get(1).text(), dayRangeTd.get(1).text(),
					fiftyTWoWeekRangeTd.get(1).text(), volumeTd.get(1).text(), avgVolume.get(1).text(),
					marketCap.get(1).text(), beta.get(1).text(), pERatio.get(1).text(), ePS.get(1).text(),
					earningsDate.get(1).text(), forwardDividendYield.get(1).text(), exDividendDate.get(1).text(),
					oneYearTargetEst.get(1).text());
			save(stockName, stockInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void save(String stockName, StockInfo stockInfo) {
		firebaseInitService.getDataBaseRef().child("financeData").child(LocalDate.now().toString()).child(stockName)
				.child(String.valueOf(DateTime.now().getMillis())).setValueAsync(stockInfo);
		LOG.info("Stock Update=" + stockName);
	}

}
