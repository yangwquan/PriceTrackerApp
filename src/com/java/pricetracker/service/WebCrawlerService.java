package com.java.pricetracker.service;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.Connection;

@Service
public class WebCrawlerService {
	//public static Connection conn = null;
	
	//private WebCrawlerService webCrawlerService;
	
	//public void setWebCrawlerService(WebCrawlerService webCrawlerService){
	//	this.webCrawlerService = webCrawlerService;
	//}
	
	//public WebCrawlerService getWebCrawlerService(){
	//	return this.webCrawlerService;
	//}
	private String link;
	private boolean historyStatus;
	
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		
		this.link = link;
	}	
	
	public void setLinkHistoryStatus(String response){
		JSONObject obj = new JSONObject(response);
		this.link = obj.getString("link");
		this.historyStatus =obj.getBoolean("historyStatus");
	}
	
	public double crawPriceValue() throws IOException{
		//Class.forName("com.mysql.jdbc.Driver");
		//String url = "jdbc:mysql://localhost:3306/pricetracker";
		//conn = (Connection) DriverManager.getConnection(url, "root", "letmein");
		//System.out.println("conn built");
		
		// TODO Auto-generated method stub
		//Document doc = Jsoup.connect("http://www.apmex.com/product/1/1-oz-gold-american-eagle-bu-random-year").get();
		Document doc = Jsoup.connect(link).get();
		String ele = doc.select("table.table-volume-pricing").select("tbody").select("td").last().text();
		String price1Value = ele.substring(1,2)+ele.substring(3);
		Double priceValue = Double.valueOf(price1Value);
		System.out.println(price1Value);
		System.out.println(priceValue);
		return priceValue;		
	}
	//public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException{
	public String crawPriceTitle() throws IOException{
		//Class.forName("com.mysql.jdbc.Driver");
		//String url = "jdbc:mysql://localhost:3306/pricetracker";
		//conn = (Connection) DriverManager.getConnection(url, "root", "letmein");
		//System.out.println("conn built");
				
		// TODO Auto-generated method stub
		//Document doc = Jsoup.connect("http://www.apmex.com/product/1/1-oz-gold-american-eagle-bu-random-year").get();
		Document doc = Jsoup.connect(link).get();
		String priceTitle = doc.select(".product-title").text();
		//String price1Value = ele.substring(1,2)+ele.substring(3);
		//Double priceValue = Double.valueOf(price1Value);
		System.out.println(priceTitle);
		//System.out.println(priceValue);
		return priceTitle;		
	}

	public boolean isHistoryStatus() {
		return historyStatus;
	}

	public void setHistoryStatus(boolean historyStatus) {
		
		this.historyStatus = historyStatus;
	}
}