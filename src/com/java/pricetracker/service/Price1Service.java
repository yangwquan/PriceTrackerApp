package com.java.pricetracker.service;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.pricetracker.DAO.Price1;
import com.java.pricetracker.DAO.Price1DAO;
import com.java.pricetracker.DAO.Price2;
import com.java.pricetracker.DAO.PriceHistory;

@Service
public class Price1Service {
	@Autowired
	private Price1DAO price1DAO;
	
	public void setPrice1DAO(Price1DAO price1DAO){
		this.price1DAO = price1DAO;
	}
	
	@Transactional
    public void addPrice(Price1 p) {
        this.price1DAO.addPrice(p);
    }
 
	@Transactional
    public void updatePrice(Price1 p) {
        this.price1DAO.updatePrice(p);
    }
 
	@Transactional
    public List<Price1> listPrices() {
        return this.price1DAO.listPrices();
    }

	@Transactional
    public Price2 getPriceById(int id) {
        return this.price1DAO.getPriceById(id);
    }
	
	@Transactional
	public Set<PriceHistory> getPriceHistoryById(int id) {        
        return this.price1DAO.getPriceHistoryById(id);
    }
 
	@Transactional
    public void removePrice(int id) {
        this.price1DAO.removePrice(id);
    }
}
