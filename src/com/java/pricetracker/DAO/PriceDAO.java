package com.java.pricetracker.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
 
    public Price getPriceById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        Price p = (Price) session.load(Price.class, new Integer(id));
        return p;
    }
 
    public void removePrice(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Price p = (Price) session.load(Price.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
    }
}
