/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

import java.text.NumberFormat ;
import java.util.Locale;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

/* 

    REST CLIENT:  

    http://unirest.io/java.html 
    https://www.baeldung.com/unirest
    http://stleary.github.io/JSON-java/index.html
    https://www.programcreek.com/java-api-examples/?api=com.mashape.unirest.http.Unirest

*/


/**
 * Card Class for Managing Card Balance & Transactions
 */
public class Card {

    private double balance = 0.00f ; // default
    private String cardId = "000000000" ;
    private String cardCode = "000" ;
	StarbucksAPI api = new StarbucksAPI();

    
    private static Card theCard = null ;
    
    /**
     * Get New Instance of Card
     * @return Card
     */
    public synchronized static Card getNewInstance() {
        theCard = new Card();
        theCard.cardId = "000000000";
        theCard.cardCode = "000";
        theCard.balance = 0.00f;
        return theCard;
    }
    
    /**
     * Get Instance of Card
     * @return Card
     */
    public synchronized static Card getInstance() {
        if (theCard == null) {
            theCard = new Card();
            theCard.setCard();
            return theCard;
        }
        else
            return theCard;
    }

    private Card() { }

    /**
     * Create a new card API call
     */
    public void setCard()
    { 
        Device theDevice = Device.getInstance() ;
        String apiurl = theDevice.getProps("apiurl") ;
        String apikey = theDevice.getProps("apikey") ;

        try {

            HttpResponse<JsonNode> response = Unirest.post( apiurl + "/cards")
                .header( "apikey", apikey )
                .asJson() ;
            JSONObject json = response.getBody().getObject() ;
            System.err.println( json.toString() ) ;

            this.cardId = json.getString( "CardNumber" ) ;
            this.cardCode = json.getString( "CardCode" ) ;
            this.balance = json.getDouble( "Balance" )  ;     

            System.err.println( "New Card: " + cardId + "[" + cardCode + "] Balance: " + balance ) ;

        		
        } catch (Exception e) {

            System.err.println( e ) ;
            
        }
    }
    
    /**
     * Setting Card Values into object
     * @param cardNumber Card Number
     * @param code Card Code
     * @param balance Card Balance
     */
    public void setTheCard(String cardNumber, String code, double balance) {
    	if(theCard != null) {
    		theCard.cardId = cardNumber ;
    		theCard.cardCode = code ;
    		theCard.balance = balance  ;
    	}
    	
    }

    /**
     * Get Balance of Card from API
     * <exception cref="Exception" />
     */
    public void getBalanceFromApi() throws Exception {
    	starbucks.StarbucksAPI.Card card = api.getCard(cardId);
    	setBalance(card.balance);    	
    }
    public String getCardId() {
        return cardId ;
    }
    
    public String getCardCode() {
        return cardCode ;
    }
    
    /**
     * Get Balance of Card
     * @return Balance
     */
    public String getBalance() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        return formatter.format(balance) ;        
    }

    /**
     * Payment for an order
     * @param regId register Id
     */
    public void pay(String regId) {

        Device theDevice = Device.getInstance() ;
        String apiurl = theDevice.getProps("apiurl") ;
        String apikey = theDevice.getProps("apikey") ;
        
        try {

            HttpResponse<JsonNode> response = 
                Unirest.post( apiurl + "/order/register/"+regId+"/pay/"+cardId)
                    .header( "apikey", apikey )
                    .asJson() ;
            System.err.println("Res Status for Pay api: "+response.getStatus());
            if(response.getStatus()==200) {
            	System.err.println("Register id in Card: "+regId);
            	api.clearOrder(regId);
            }
            JSONObject json = response.getBody().getObject() ;
            System.err.println( json.toString() ) ;

            double new_bal = json.getDouble( "Balance" )  ;  
            this.balance = new_bal ;
        
        } catch (Exception e) {

            System.err.println( e ) ;
            
        }

    }
    /**
     * Get Display Contents
     * @return Display Contents
     */
    public void display()
    {
        System.err.format( "Card ID: %s%n", cardId ) ;
        System.err.format( "Card Balance: $%4.2f%n", balance ) ;
    }

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
    
    
}
 

