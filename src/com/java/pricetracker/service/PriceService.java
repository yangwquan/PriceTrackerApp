package com.java.pricetracker.service;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.pricetracker.DAO.Price;
import com.java.pricetracker.DAO.PriceDAO;
import com.java.pricetracker.DAO.TemporaryPrice;
import com.java.pricetracker.DAO.PriceHistory;

@Service
public class PriceService {
	@Autowired
	private PriceDAO priceDAO;
	
	public void setPrice1DAO(PriceDAO priceDAO){
		this.priceDAO = priceDAO;
	}
	
	@Transactional
    public void addPrice(Price p) {
        this.priceDAO.addPrice(p);
    }
 
	@Transactional
    public void updatePrice(Price p) {
        this.priceDAO.updatePrice(p);
    }
 
	@Transactional
    public List<Price> listPrices() {
        return this.priceDAO.listPrices();
    }

	@Transactional
    public TemporaryPrice getPriceById(int id) {
        return this.priceDAO.getPriceById(id);
    }
	
	@Transactional
	public Set<PriceHistory> getPriceHistoryById(int id) {        
        return this.priceDAO.getPriceHistoryById(id);
    }
 
	@Transactional
    public void removePrice(int id) {
        this.priceDAO.removePrice(id);
    }
}
