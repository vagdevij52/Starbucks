/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** My Card More Options Screen */
public class MyCardsMoreOptions extends Screen
{
  
    public MyCardsMoreOptions()
    {
    }
    
    /**
     * Get Display Contents
     * @return Display Contents String
     */
    public String display() {
    	String result = "\n"+"Refresh"+"\n" +"Reload"+"\n" +"Auto Reload"+"\n" +"Transactions"+"\n";
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
    	System.err.println("Touched at :"+ x + " "+ y);
        AppController app = AppController.getInstance() ;
        app.setScreen( AppController.SCREENS.MY_CARDS_MORE_OPTIONS ) ;
               
    }

}
