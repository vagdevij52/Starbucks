/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** Settings Screen */
public class Settings extends Screen
{
   
    public Settings()
    {
       
    }
 
    /**
     * Return Settings Screen
     * @return Contents from Settings Screen
     */
    public String display() {
    	String result = "Add Card"+"\n" +"Delete Card"+"\n" +"Billing"+"\n" +"Passcode"+"\n\n"+"About|Terms"+"\n"+"Help"
;
    	return result;
    }
   
    
    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen
     */
    public String name() {
        return (this.getClass().getName()).split("\\.")[1] ; 
    }
    
    /**
     * Touch (X,Y) Event
     * @param x X Coord
     * @param y Y Coord
     */
    public void touch(int x, int y) {
        AppController app = AppController.getInstance() ;
        if ( x == 1 && y == 1 || x == 2 && y == 1 || x == 3 && y == 1 ) {
            app.setScreen( AppController.SCREENS.ADD_CARD ) ;
        }            
    }
    
}
