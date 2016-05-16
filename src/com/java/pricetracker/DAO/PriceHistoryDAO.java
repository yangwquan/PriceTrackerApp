package com.java.pricetracker.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PriceHistoryDAO {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	public void addPriceHistory(PriceHistory p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
    }
	
	public void updatePriceHistory(PriceHistory p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
    }
	
	@SuppressWarnings("unchecked")
    public List<PriceHistory> listPriceHistory() {
        Session session = this.sessionFactory.getCurrentSession();
        List<PriceHistory> pricesList = session.createQuery("from PriceHistory").list();
        return pricesList;
    }
 
    public PriceHistory getPriceHistoryById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        PriceHistory p = (PriceHistory) session.load(PriceHistory.class, new Integer(id));
        return p;
    }
 
    public void removePriceHistory(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        PriceHistory p = (PriceHistory) session.load(PriceHistory.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
    }
}
