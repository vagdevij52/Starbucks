/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Store Screen */
public class Store extends Screen
{

	
    private static Store store = null ;
    private static Store theStore = null ;

    private String regId;
    
   /**
     * Constructor
     */
    public Store() {
    }
    

    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen
     */
    public String name() {
        return "Find Store" ; 
    }

    /**
     * Get New Instance 
     * @return Reference to Device (Create New Singleton)
     */
    public synchronized static Store getNewInstance() {
    	theStore = new Store();
    	theStore.regId = "5012349";
    	return theStore;
    }
    
    /**
     * Get Singleton Instance
     * @return Reference to Current Device Config (Create if none exists)
     */
    public synchronized static Store getInstance() {
        if (theStore == null) {
        	theStore = new Store();
        	theStore.setRegId("5012349");
            return theStore;
        }
        else
            return store;
    }
    
    /**
     * Setting Register Id
     * @param regId Register Id of store
     */
    public void setTheRegId(String regId) {
    	if(theStore!=null)
    	theStore.regId = regId;
    }
    /**
     * Return Screen Contents
     * @return Map Screen Sample Content
     */
    public String display() {
        return "         X\n   X\n       X\n      X\n  X\n           X\n  X" ;
    }

    /**
     * Handle Touch Event -- Not Used.
     * @param x Touch X
     * @param y Touch Y
     */
    public void touch(int x, int y) {
       AppController app = AppController.getInstance() ;
       System.err.println( "X: " + x + " Y: " + y ) ;
       if( x==3 && y==2 ) {
    	   theStore.setTheRegId("5012349");
    	   System.err.println("Selected regId: "+getRegId());
           app.setScreen( AppController.SCREENS.MY_CARDS_PAY ) ;   
       }
       else if( x==3 && y==7 ) {
    	   theStore.setTheRegId("1287612");
    	   System.err.println("Selected regId: "+getRegId());
           app.setScreen( AppController.SCREENS.MY_CARDS_PAY ) ;   
       }
       else if( x==1 && y==3 ) {
    	   theStore.setTheRegId("6498234");
    	   System.err.println("Selected regId: "+getRegId());
           app.setScreen( AppController.SCREENS.MY_CARDS_PAY ) ;   
       }
       else if( x==2 && y==4 ) {
    	   theStore.setTheRegId("7812386");
    	   System.err.println("Selected regId: "+getRegId());
           app.setScreen( AppController.SCREENS.MY_CARDS_PAY ) ;   
       }
       else if( x==2 && y==5 ) {
    	   theStore.setTheRegId("8723098");
    	   System.err.println("Selected regId: "+getRegId());
           app.setScreen( AppController.SCREENS.MY_CARDS_PAY ) ;   
       }
       else if( x==1 && y==6 ) {
    	   theStore.setTheRegId("9621043");
    	   System.err.println("Selected regId: "+getRegId());
           app.setScreen( AppController.SCREENS.MY_CARDS_PAY ) ;   
       }
       else if( x==1 && y==7 ) {
    	   theStore.setTheRegId("1393478");
    	   System.err.println("Selected regId: "+getRegId());
           app.setScreen( AppController.SCREENS.MY_CARDS_PAY ) ;   
       }
       else {
           app.setScreen( AppController.SCREENS.STORE ) ;   
       }
    }

    /**
     * Setting Register Id
     * @return Register Id of Store
     */
	public  String getRegId() {
		return theStore.regId;
	}

    /**
     * Setting Register Id
     * @param regId Register Id of store
     */
	public  void setRegId(String regId) {
		this.regId = regId;
	}



}
