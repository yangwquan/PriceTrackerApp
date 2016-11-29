package com.java.pricetracker.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.java.pricetracker.DAO.Price;
import com.java.pricetracker.DAO.PriceHistory;
import com.java.pricetracker.service.PriceService;
import com.java.pricetracker.service.PriceHistoryService;
import com.java.pricetracker.service.WebCrawlerService;

@Component("taskScheduler")
public class TaskScheduler {
	
	@Autowired
	private WebCrawlerService webCrawlerService;
	@Autowired
	private PriceService priceService;
	@Autowired
	private PriceHistoryService priceHistoryService;
	
	
	public void printMessage() throws IOException {
        System.out.println("I am called by Spring scheduler");

        List<Price> prices = priceService.listPrices();
        for(Price price_each : prices){
        	String link = price_each.getLink();
        	webCrawlerService.setLink(link);
        	price_each.setValue(webCrawlerService.crawPriceValue());
        	PriceHistory priceHistory = new PriceHistory();
        	priceHistory.setPrice(price_each);
        	priceHistory.setValue(price_each.getValue());
        	java.util.Date date= new java.util.Date();
        	priceHistory.setCrawlDate(new Timestamp(date.getTime()));
        	priceService.updatePrice(price_each);
        	priceHistoryService.addPriceHistory(priceHistory);
        }
         
    }
   
}
