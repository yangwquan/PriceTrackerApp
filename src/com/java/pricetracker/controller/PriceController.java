package com.java.pricetracker.controller;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.java.pricetracker.service.PriceService;
import com.java.pricetracker.service.WebCrawlerService;
import com.java.pricetracker.DAO.*;

@RestController
public class PriceController {
	@Autowired
	private WebCrawlerService webCrawlerService;
	@Autowired
	private PriceService priceService;
	
//-------------------Retrieve All Prices--------------------------------------------------------
    
    @RequestMapping(value = "/price1", method = RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<List<Price>> listPrice() {
        List<Price> prices = priceService.listPrices();
        //if(users.isEmpty()){
        //    return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        //}
       // System.out.println(Arrays.toString(prices.toArray()));
        return new ResponseEntity<List<Price>>(prices, HttpStatus.OK);
    }
     
    //-------------------Retrieve Single Price--------------------------------------------------------
      
    @RequestMapping(value = "/price1/{id}", method = RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<TemporaryPrice> getPriceById(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        TemporaryPrice price1 = priceService.getPriceById(id);
        if (price1 == null) {
            System.out.println("Price with id " + id + " not found");
            return new ResponseEntity<TemporaryPrice>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TemporaryPrice>(price1, HttpStatus.OK);
    }
 
    @RequestMapping(value = "/price1/{id}/priceHistory", method = RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<Set<PriceHistory>> getPriceHistoryByPriceId(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        Set<PriceHistory> priceHistory = priceService.getPriceHistoryById(id);
        //Set <PriceHistory> priceHistory =price1.getPriceHistory();
        if (priceHistory == null) {
            System.out.println("Price with id " + id + " not found");
            return new ResponseEntity<Set<PriceHistory>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Set<PriceHistory>>(priceHistory, HttpStatus.OK);
    }
      
  //-------------------Create a Price with only a link--------------------------------------------------------      
    @RequestMapping(value = "/price1", method = RequestMethod.POST)
    public ResponseEntity<Void> createPrice(@RequestBody String response, UriComponentsBuilder ucBuilder) throws IOException {
    	webCrawlerService.setLinkHistoryStatus(response);
    	//webCrawlerService.setLink(response);
    	//webCrawlerService.setHistoryStatus(response);
    	String link = webCrawlerService.getLink();
    	boolean historyStatus = webCrawlerService.isHistoryStatus();
    	String priceTitle = webCrawlerService.crawPriceTitle();
    	double priceValue = webCrawlerService.crawPriceValue();
    	Price price = new Price();
    	price.setLink(link);
    	price.setTitle(priceTitle);
    	price.setValue(priceValue);
    	price.setStore("APMEX");
    	price.setHistoryStatus(historyStatus);
        System.out.println("Creating price " + price.getLink());
  
       // if (userService.isUserExist(user)) {
       //     System.out.println("A User with name " + user.getUsername() + " already exist");
       //     return new ResponseEntity<Void>(HttpStatus.CONFLICT);
       // }
  
        priceService.addPrice(price);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/price1/{id}").buildAndExpand(price.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    /* 
    
    //-------------------Create a Price--------------------------------------------------------      
    @RequestMapping(value = "/price1", method = RequestMethod.POST)
    public ResponseEntity<Void> createPrice1(@RequestBody Price price, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating price " + price.getLink());
  
       // if (userService.isUserExist(user)) {
       //     System.out.println("A User with name " + user.getUsername() + " already exist");
       //     return new ResponseEntity<Void>(HttpStatus.CONFLICT);
       // }
  
        priceService.addPrice(price);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/price/{id}").buildAndExpand(price.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
      
    //------------------- Update a Price --------------------------------------------------------
      
    @RequestMapping(value = "/price1/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Price1> updatePrice(@PathVariable("id") int id, @RequestBody Price1 price1) {
        System.out.println("Updating Price " + id);
          
        Price1 currentPrice = price1Service.getPriceById(id);
          
        //if (currentUser==null) {
        //    System.out.println("User with id " + id + " not found");
        //    return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        //}
  
        currentPrice.setLink(price1.getLink());
        currentPrice.setValue(price1.getValue());
          
        price1Service.updatePrice(currentPrice);
        return new ResponseEntity<Price1>(currentPrice, HttpStatus.OK);
    }
 */  
    //------------------- Update a Price --------------------------------------------------------
   /* 
    @RequestMapping(value = "/price1/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Price1> updatePrice(@PathVariable("id") int id, @RequestBody Price1 price1) {
        System.out.println("Updating Price " + id);
          
        Price1 currentPrice = price1Service.getPriceById(id);
          
        //if (currentUser==null) {
        //    System.out.println("User with id " + id + " not found");
        //    return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        //}
  
        currentPrice.setLink(price1.getLink());
        currentPrice.setValue(price1.getValue());
          
        price1Service.updatePrice(currentPrice);
        return new ResponseEntity<Price1>(currentPrice, HttpStatus.OK);
    }   
  */   
    //------------------- Delete a Price --------------------------------------------------------
      
    @RequestMapping(value = "/price1/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Price> removePrice(@PathVariable("id") int id){
        System.out.println("Fetching & Deleting User with id " + id);
        
        //Integer id = Integer.parseInt(priceid);
  
        //Price price = priceService.getPriceById(id);
        //if (user == null) {
        //    System.out.println("Unable to delete. User with id " + id + " not found");
        //    return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        //}
  
        priceService.removePrice(id);
        return new ResponseEntity<Price>(HttpStatus.NO_CONTENT);
    }
  
      
     
    //------------------- Delete All Users --------------------------------------------------------
      
   // @RequestMapping(value = "/user", method = RequestMethod.DELETE)
   // public ResponseEntity<User> deleteAllUsers() {
   //     System.out.println("Deleting All Users");
  
   //     userService.deleteAllUsers();
   //     return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
   // }
  

}
