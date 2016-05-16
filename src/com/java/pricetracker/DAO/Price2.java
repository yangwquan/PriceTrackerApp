package com.java.pricetracker.DAO;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Price2 {
	
	private int id;
	private String title;
	private String store;
	private String link;
	private boolean historyStatus;
	private double value;
	
	private Set<PriceHistory> priceHistory;
	
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setLink(String link){
		this.link = link;
	}
	public String getLink(){
		return this.link;
	}
	public void setValue(double value){
		this.value = value;
	}
	public double getValue(){
		return this.value;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public Set<PriceHistory> getPriceHistory(){
		return priceHistory;
	}
	public void setPriceHistory(Set<PriceHistory> priceHistory){
		this.priceHistory=priceHistory;
	}
	public boolean getHistoryStatus() {
		return historyStatus;
	}
	public void setHistoryStatus(boolean historyStatus) {
		this.historyStatus = historyStatus;
	}
}
