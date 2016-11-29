package com.java.pricetracker.DAO;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PriceDAO {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	public void addPrice(Price p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
    }
	
	public void updatePrice(Price p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
    }
	
	@SuppressWarnings("unchecked")
    public List<Price> listPrices() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Price> pricesList = session.createQuery("from Price").list();
        return pricesList;
    }
	/*
	 public Price1 getPriceById(int id) {
	        Session session = this.sessionFactory.getCurrentSession();      
	        Price1 p = (Price1) session.load(Price1.class, new Integer(id));
	        return p;
	    }
	*/
 
    public TemporaryPrice getPriceById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        Price p = (Price) session.load(Price.class, new Integer(id));
        //Within the session, the priceHistory is setup from the database via the hibernate.initialize
        //But after the session is closed, the priceHistory will be empty automatically
        Hibernate.initialize(p.getPriceHistory());
        TemporaryPrice pp = new TemporaryPrice();
        pp.setId(p.getId());
        pp.setHistoryStatus(p.getHistoryStatus());
        pp.setLink(p.getLink());
        pp.setStore(p.getStore());
        pp.setTitle(p.getTitle());
        pp.setValue(p.getValue());
        pp.setPriceHistory(p.getPriceHistory());
        return pp;
        //return p;
    }
 
    public Set<PriceHistory> getPriceHistoryById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        Price p = (Price) session.load(Price.class, new Integer(id));
        Hibernate.initialize(p.getPriceHistory());
        Set<PriceHistory> sets =p.getPriceHistory();
        return sets;
    }
    
    public void removePrice(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Price p = (Price) session.load(Price.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
    }
}
