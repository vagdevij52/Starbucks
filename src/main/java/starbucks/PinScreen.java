/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Pin Screen */
public class PinScreen extends Screen
{
	private String disp;
   
    /**
     * Get Screen Display Contents
     * @return Screen Contents
     */
    public PinScreen()
    {
    	
    }
    
    @Override
    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen String
     */
    public String name() {
        return ("") ; 
    }

	public String getDisp() {
		return disp;
	}

	public void setDisp(String disp) {
		this.disp = disp;
	}
	
	public String display() {
		if(getDisp() == null || getDisp().isEmpty())
			return "\n\n"+super.display();
		else
			return getDisp()+"\n\n"+super.display();
		
		
	}
    
    
  
}
