package com.java.pricetracker.DAO;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

@Entity
@Table(name="priceHistory")
//@Proxy(lazy = false)
public class PriceHistory {
	private static final String TemporalType = null;

	@Id
	@GenericGenerator(name="kaugen" , strategy="increment")
	@GeneratedValue(generator="kaugen")
	@Column(name="id")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="priceId")
	private Price price;
	
	@Column(name="value")
	private double value;
	
	@Column(name="crawlDate")
	//@Temporal(TemporalType.TIMESTAMP)
    //private java.util.Date crawlDate;
	//@Type(type="timestamp")
	//private Date crawlDate;
	private Timestamp crawlDate;
	
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setPrice(Price price){
		this.price = price;
	}
	public Price getPrice(){
		return this.price;
	}
	public void setValue(double value){
		this.value = value;
	}
	public double getValue(){
		return this.value;
	}
	public Timestamp getCrawlDate() {
		return crawlDate;
	}
	public void setCrawlDate(Timestamp crawlDate) {
		this.crawlDate = crawlDate;
	}

}
