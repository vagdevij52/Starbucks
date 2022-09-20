/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** My Card Pay Screen */
public class MyCardsPay extends Screen
{
    Card card ;
    Store store = Store.getNewInstance();

    
    public MyCardsPay()
    {
        card = Card.getInstance() ;
    }

    /**
     * Get Screen Display Contents
     * @return Screen Contents
     */
    public String display() {
        String result = "[" + card.getCardId() + "]\n" + "\n\n" + "Scan Now" ;
        return result ;
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
            app.setScreen( AppController.SCREENS.MY_CARDS_MAIN ) ;
        }            
        if ( x == 2 && y == 2 || x == 3 && y == 2) {
        	if(store.getRegId() != null) {
        	System.err.println("Store: "+store.getRegId());
        	String regid = store.getRegId();
        	StarbucksAPI api = new StarbucksAPI();
        	api.newOrder(regid);
            card.pay(regid) ;
        	}
        }
    } 

}


