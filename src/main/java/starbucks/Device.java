/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

import java.util.HashMap;

/**
 * Authentication Proxy for App Controller
 */
public class Device implements IApp, IPinAuthObserver {
    
    private static Device theDevice = null;   
    private boolean fourPin = true ;
    private boolean sixPin = false ;
    private String pin = "" ; 
    private HashMap<String, String> props = new HashMap<String, String>() ;

    private IApp app ;
    private KeyPad kp ;
    private Passcode pc ;
    private Passcode pc4 ;
    private Passcode pc6 ;

    private PinScreen ps ;
    private Spacer sp ;
    private boolean authenticated = false ;
    private PinEntryMachine pm ;

    public static final int screen_frame_header = 3 ;
    public static final int portrait_screen_width = 15 ;
    public static final int portrait_screen_length = 10 ;
    public static final int landscape_screen_width = 32 ;
    public static final int landscape_screen_length = 6 ;

    
    /** Enums for Orientation Types */
    public enum ORIENTATION_MODE {
        PORTRAIT, LANDSCAPE
    }

    private ORIENTATION_MODE device_orientation_state ;

    /**
     * Get Device Orientation
     * @return ORIENTATION_MODE enum
     */
    public ORIENTATION_MODE getDeviceOrientation() {
        return this.device_orientation_state ;
    }

    public void setPortraitOrientation() {
        this.device_orientation_state = ORIENTATION_MODE.PORTRAIT ;
    }

    public void setLandscapeOrientation() {
        this.device_orientation_state = ORIENTATION_MODE.LANDSCAPE ;
    }

    private Device() { }

    /** Debug Device State */
    public static void debug()
    {
        Device d = Device.getInstance() ;
        System.err.println( "============================================" ) ;
        System.err.println( "--------- D E V I C E  S T A T E  ----------" ) ;
        System.err.println( "============================================" ) ;
        System.err.println( "Pin Option    = " + d.getPinOption() ) ;
        System.err.println( "Stored Pin    = " + d.getPin() ) ;
        System.err.println( "Authenticated = " + d.isAuthenticated() ) ;
        System.err.println( "Orientation   = " + d.getDeviceOrientation() ) ;
        System.err.println( "============================================" ) ;
    }

    /** Get/Set Device Secured Enclave for Apps 
     *  @param key API url
     *  @return Key String
    */
    public String getProps(String key) {
        return props.get(key) ;
    }

    /** Get/Set Device Secured Enclave for Apps 
     *  @param key API url
     *  @param value The value of url
    */
    public void setProps(String key, String value) {
        props.put(key, value) ;
    }

    /**
     * Get Current Auth State
     * @return Auth T/F
     */
    public String isAuthenticated() {
        return Boolean.toString( authenticated ) ;
    }    

    /**
     * Return the current Pin Option:
     *  0 = User Chosed No Pin
     *  4 = User Chosed 4-digit Pin
     *  6 = User Chosed 6-digit Pin
     * @return Pin Option int
     */
    public int getPinOption() {
        if ( fourPin )
            return 4 ;
        else if ( sixPin )
            return 6 ;
        else
            return 0 ;
    }

    /**
     * Get Current Pin
     * @return Pin String
     */
    public String getPin() {
        return pin ;
    }


    /**
     * Set Pin
     * @param p New Pin
     */
    private void setPin( String p ) {
        pin = p ;
        int len = p.length() ;
        switch ( len ) {
            case 0:
                fourPin = false ;
                sixPin = false ;
                break;
            case 4:
                fourPin = true ;
                sixPin = false ;
                break ;
            case 6:
                fourPin = false ;
                sixPin = true ;
                break ;
            default:
                fourPin = false ;
                sixPin = false ;
        }
    }

    /**
     * Get Singleton Instance
     * @return Reference to Current Device Config (Create if none exists)
     */
    public synchronized static Device getInstance() {
        if (theDevice == null) {
        	System.err.println("calling getInstance 1234");
            return getNewInstance( "1234" ) ;//246843  2468
        }
        else
            return theDevice;
    }

    /**
     * Get New Instance 
     * @return Reference to Device (Create New Singleton)
     */
    public synchronized static Device getNewInstance() {
        return getNewInstance( "1234" ) ;
    }

    /**
     * Get New Instance 
     * @return Reference to Device
     */
    public synchronized static Device getNewInstance( String pin ) {
    	System.err.println("Calling api for cards");
        theDevice = new Device() ;
        
        theDevice.setPin( pin ) ;
        theDevice.setProps( "apiurl", "http://localhost:3000" ) ;
        theDevice.setProps( "apikey", "2742a237475c4703841a2bf906531eb0" ) ;
        theDevice.setProps( "register", "5012349" ) ;
        debug();
        System.err.println("calling startup");
        theDevice.startUp() ;

        return theDevice ;
    }
    
    /**
     * Get New Instance 
     * @param pin Pin code
     * @param url API url
     * @return Reference to Device
     */
    public synchronized static Device getNewInstance( String pin, String url ) {
        if (theDevice == null) {
            theDevice = getNewInstance( "1234" ) ;
            theDevice.setProps( "apiurl", url ) ;
        }
        debug() ;
        return theDevice ;
    }

    /**
     * Device Starup Process.  
     * Starts Up with Default 4-Pin Option
     */
    public void startUp()
    {
        System.err.println("In startup");

        kp = new KeyPad() ;
        pc = new Passcode() ;
        if(getPinOption()==4) {
        pc4 = new Passcode4();
        pc = pc4;
        }else if(getPinOption()==6) {
        pc6 = new Passcode6();
        pc = pc6;
        }else if(getPinOption() == 0) {	
        	this.authenticated = true;
        }
        sp = new Spacer() ;
        ps = new PinScreen() ;
        pm = new PinEntryMachine() ;
        pm.setPin(pin);
        // setup the composite pattern
        ps.addSubComponent( pc ) ;
        System.err.println("Adding Passcode cmpt");
        ps.addSubComponent( sp ) ;
        ps.addSubComponent( kp ) ;

        // setup the observer pattern
        ((IKeyPadSubject)kp).attach( pc ) ;
        ((IKeyPadSubject)kp).attach( pm ) ;
        ((IPinAuthSubject)pm).registerObserver(this) ;

        // get app controller reference
        app = AppController.getNewInstance() ;   

        // startup in portrait
        this.device_orientation_state = ORIENTATION_MODE.PORTRAIT ;
    }

    /**
    * Switch to Landscape View
    */
    public void landscape() {
        if ( authenticated )
            app.landscape() ;
    }

    /**
     * Switch to Portait View
     */
    public void portrait() {
        if ( authenticated )
            app.portrait() ;
    }

    /**
     * User Touch at X,Y Coordinates
     * @param x X Coordinate
     * @param y Y Coordinate
     */
    public void touch(int x, int y) {
        if ( authenticated )
            app.touch(x, y) ;
        else
        	ps.setDisp("");
            ps.touch(x, y) ;
    }

    /**
     * Display Screen Contents to Terminal
     */
    public void display() {
        System.out.println( screenContents() ) ;
    }

    /**
     * Get Class Name of Screen
     * @return Class Name of Current Screen String
     */
    public String screen() {
        if ( authenticated )
            return app.screen() ;
        else
            return ps.name() ;
    }

    /**
     * Get Screen Contents as a String
     * @return Screen Contents of Current Screen String
     */
    public String screenContents() {
        if ( authenticated ) {
            return app.screenContents() ;
        } else {
            String out = "" ;
   		 	StringBuffer tmp = new StringBuffer(out);
            tmp.append("----------------\n");
            tmp.append("   " + ps.name() + "  \n");
            tmp.append("----------------\n");
            //out += ps.display() ;

    		String[] ans = ps.display().split("\n");
    		for(int i=0; i<ans.length;i++) {
    			int cp = (int) (Math.ceil((float)(15 - ans[i].toString().length()) / 2)) ;
    			tmp.append(padSpaces( cp ));
            	tmp.append(ans[i].toString());
            	if(i<ans.length - 1) {
    	        	tmp.append("\n");

            	}
    		}
            tmp.append("\n\n\n----------------\n");
            out = tmp.toString();
            dumpLines(out);
            return out ;
        }
    }
    
    /**
     * dumplines
     * @param str variable
     */
    private void dumpLines(String str) {
        String[] lines = str.split("\r\n|\r|\n");
        for ( int i = 0; i<lines.length; i++ ) {
          System.err.println( i + ": " + lines[i] ) ;
        }
  }
    
    /**
     * padspaces
     * @param num total number
     * @return Spaces String 
     */
    private String padSpaces(int num) {
        String spaces = "" ;
        StringBuffer tmp = new StringBuffer(spaces);
        for ( int i = 0; i<num; i++ ) {
        	tmp.append(" ");
            spaces = tmp.toString();
        }
        return spaces ;     
    }

    /**
     * Select a Menu Command
     * @param c Menu Option (A, B, C, E, or E)
     */
    public void execute( String c ) {
        if ( authenticated )
            app.execute( c ) ;
    }

    /**
     * Navigate to Previous Screen
     */
    public void prev() {
        if ( authenticated )
            app.prev() ;
    }

    /**
     * Navigate to Next Screen
     */
    public void next() {
        if ( authenticated )
            app.next() ;
    }

    /**
     * Receive Authenticated Event from Authenticator
     */
    public void authEvent() {
        this.authenticated = true ;
    }
    
    /**
     * Receive Not Authenticated Event from Authenticator
     */
    public void notAuthEvent() {
    	this.authenticated = false;
    	ps.setDisp("Invalid Pin");
    	kp.setCountPinDigits(0);
    }



}
