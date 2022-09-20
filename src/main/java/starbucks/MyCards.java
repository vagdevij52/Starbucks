/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** My Cards Screen */
public class MyCards extends Screen
{
   
    Card card ;
    
    public MyCards()
    {
    	card = Card.getNewInstance();
    	card.setBalance(0);
    	
    }
    /**
     * Get Screen Display Contents
     * @return Screen Contents
     */
    public String display() {
    	card = Card.getInstance();
        return card.getBalance() ;
    }
    
    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen String
     */
    public String name() {
    	return "My Cards";
    }

    /**
     * Touch (X,Y) Event
     * @param x X Coord
     * @param y Y Coord
     */
    public void touch(int x, int y) {
        AppController app = AppController.getInstance() ;
        if ( x == 3 && y == 3 ) {
            app.setScreen( AppController.SCREENS.MY_CARDS_PAY ) ;
        } else if( x == 2 && y == 4 ){
            app.setScreen( AppController.SCREENS.MY_CARDS_OPTIONS ) ;
        }            
    }
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	} 
	
    /**
     * Set Card (cardId,code,balance)
     * @param cardId Card Number
     * @param code Code Number
     * @param balance Card Balance
     */
	public void setCard(String cardId, String code, float balance ) {
		card.setBalance(balance);
		card.setCardCode(code);
		card.setCardId(cardId);
	}
    
    
   
}
