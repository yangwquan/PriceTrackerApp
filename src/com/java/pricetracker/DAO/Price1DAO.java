package com.java.pricetracker.DAO;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class Price1DAO {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	public void addPrice(Price1 p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
    }
	
	public void updatePrice(Price1 p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
    }
	
	@SuppressWarnings("unchecked")
    public List<Price1> listPrices() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Price1> pricesList = session.createQuery("from Price1").list();
        return pricesList;
    }
	/*
	 public Price1 getPriceById(int id) {
	        Session session = this.sessionFactory.getCurrentSession();      
	        Price1 p = (Price1) session.load(Price1.class, new Integer(id));
	        return p;
	    }
	*/
 
    public Price2 getPriceById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        Price1 p = (Price1) session.load(Price1.class, new Integer(id));
        Hibernate.initialize(p.getPriceHistory());
        Price2 pp = new Price2();
        pp.setId(p.getId());
        pp.setHistoryStatus(p.getHistoryStatus());
        pp.setLink(p.getLink());
        pp.setStore(p.getStore());
        pp.setTitle(p.getTitle());
        pp.setValue(p.getValue());
        pp.setPriceHistory(p.getPriceHistory());
        return pp;
    }
 
    public Set<PriceHistory> getPriceHistoryById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        Price1 p = (Price1) session.load(Price1.class, new Integer(id));
        Set<PriceHistory> sets =p.getPriceHistory();
        return sets;
    }
    
    public void removePrice(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Price1 p = (Price1) session.load(Price1.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
    }
}
