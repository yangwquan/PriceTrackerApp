package com.java.pricetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.pricetracker.DAO.PriceHistory;
import com.java.pricetracker.DAO.PriceHistoryDAO;

@Service
public class PriceHistoryService {
	@Autowired
	private PriceHistoryDAO priceHistoryDAO;
	
	public void setPriceHistoryDAO(PriceHistoryDAO priceHistoryDAO){
		this.priceHistoryDAO = priceHistoryDAO;
	}
	
	@Transactional
    public void addPriceHistory(PriceHistory p) {
        this.priceHistoryDAO.addPriceHistory(p);
    }
 
	@Transactional
    public void updatePriceHistory(PriceHistory p) {
        this.priceHistoryDAO.updatePriceHistory(p);
    }
 
	@Transactional
    public List<PriceHistory> listPriceHistory() {
        return this.priceHistoryDAO.listPriceHistory();
    }

	@Transactional
    public PriceHistory getPriceHistoryById(int id) {
        return this.priceHistoryDAO.getPriceHistoryById(id);
    }
 
	@Transactional
    public void removePriceHistory(int id) {
        this.priceHistoryDAO.removePriceHistory(id);
    }
}
