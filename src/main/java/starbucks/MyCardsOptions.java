/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** My Card Options Screen */
public class MyCardsOptions extends Screen
{
   
    public MyCardsOptions()
    {
       
    }

    /**
     * Get Display Contents
     * @return Display Contents String
     */
    public String display() {
    	String result = "\n"+"Reload"+"\n" +"Refresh"+"\n" +"More Options"+"\n" +"Cancel"+"\n";
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
        if ( x == 1 && y == 7 ||  x == 2 && y == 7 || x == 3 && y == 7 ) {
            app.setScreen( AppController.SCREENS.MY_CARDS_MORE_OPTIONS ) ;
        }          
    }   
    
}
