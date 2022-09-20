/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Payments Screen */
public class Payments extends Screen
{

    

    public Payments()
    {
    }

    /**
     * Return Payments Screen
     * @return Contents from Payments Screen String
     */
    public String display() {
        return "Find Store\nEnable Payments" ;
    }
    
    /**
     * Touch (X,Y) Event
     * @param x X Coord
     * @param y Y Coord
     */
    public void touch(int x, int y) {
    	System.err.println("Touched at :"+ x + " "+ y);
    }


}
