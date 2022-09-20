/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/**
 * Frame Class -- Container for Screens
 */
public class Frame implements IFrame
{
    private IScreen current ;
    private IMenuInvoker menuA = new MenuOption() ;
    private IMenuInvoker menuB = new MenuOption() ;
    private IMenuInvoker menuC = new MenuOption() ;
    private IMenuInvoker menuD = new MenuOption() ;
    private IMenuInvoker menuE = new MenuOption() ;
    private IOrientationStrategy portraitStrategy ;
    private IOrientationStrategy landscapeStrategy ;
    private IOrientationStrategy currentStrategy ;

    /**
     * Return Screen Name
     * @return Screen Name
     */
    public String screen() { return current.name() ; }

    /** Switch to Landscape Strategy */
    public void landscape() { 
    	
    	if(current.getClass().getName().equals("starbucks.MyCardsPay")||current.getClass().getName().equals("starbucks.MyCards")) {
    		currentStrategy = landscapeStrategy ; 
    	}
    	
    }

    /** Switch to Portrait Strategy */
    public void portrait()  { currentStrategy = portraitStrategy ; }  

    /** Nav to Previous Screen */
    public void previousScreen() {
        current.prev();
    }

    /** Nav to Next Screen */
    public void nextScreen() {
        // add code here
    	current.next();
    }



   /**
     * Helper Debug Dump to STDERR
     * @param str Lines to print
     */
    private void dumpLines(String str) {
          String[] lines = str.split("\r\n|\r|\n");
          for ( int i = 0; i<lines.length; i++ ) {
            System.err.println( i + ": " + lines[i] ) ;
          }
    }

    /**
     * Helper:  Count lines in a String 
     * @param  str Lines to Count
     * @return     Number of Lines Counted
     */
    private int countLines(String str){

        /*
          // this approach doesn't work in cases: "\n\n"
          String lines = str ;
          String[] split = lines.split("\r\n|\r|\n") ;
          return split.length ;
        */

        if (str == null || str.isEmpty()) {
                return 0;
            }

        int lines = 0;
        int pos = 0;
        while ((pos = str.indexOf("\n", pos) + 1) != 0) {
                lines++;
        }

        return lines ;
    }

    /** 
     * Helper:  Pad lines for a Screen 
     * @param  num Lines to Padd
     * @return     Padded Lines
     */
    private String padLines(int num) {
        String lines = "" ;
        StringBuffer tmp = new StringBuffer(lines);
        for ( int i = 0; i<num; i++ ) {
            System.err.print(".") ;
            lines += "\n" ;
        	tmp.append("\n");
            lines = tmp.toString();
        }
        System.err.println("") ;
        return lines ;
    }
    
    /**
     * Helper:  Pad Spaces for a Line
     * @param  num Num Spaces to Pad
     * @return     Padded Line
     */
    private String padSpaces(int num) {
        String spaces = "" ;
        StringBuffer tmp = new StringBuffer(spaces);
        for ( int i = 0; i<num; i++ ){
        	tmp.append(" ");
            spaces = tmp.toString();
        }
        return spaces ;
    }            

    /** Constructor */
    public Frame(IScreen initial)
    {
        current = initial ;

        portraitStrategy = new IOrientationStrategy() 
        {
            /**
             * Display Screen Contents
             * @param s Reference to Screen
             */
            public void display(IScreen s)
            {
                System.out.println( contents(s) ) ;
            }         

                /**
             * Return String / Lines for Frame and Screen
             * @param  s [description]
             * @return   [description]
             */
            public String contents(IScreen s) 
            { 
                String out = "" ;
                out += "===============\n" ;
                int nameLen = s.name().length() ;
                if (nameLen < 14 ) {
                    int pad = (14 - nameLen) / 2 ;
                    out += padSpaces( pad ) ;
                }
                out += " "+s.name() + "\n" ;
                out += "===============\n" ;
                String className = s.getClass().getName();
                System.err.println("ClassName: "+className);
                String screen = s.display() + "\n" ;                
                int cnt1 = countLines( screen ) ;
                int pad1 = (10 - cnt1) / 2;
                //System.err.println( "cnt1: " + cnt1 ) ;                
                //System.err.println( "pad1: " + pad1 ) ;
                out += padLines( pad1 ) ;
                //out += screen  ;
                if(className.equals("starbucks.MyCardsPay")) {
            		String[] ans = s.display().split("\n");
                	System.err.println(ans[0].toString()+ " "+ans[3].toString());
                	int cp1 = (int) (Math.round((float)(15 - ans[0].toString().length()) / 2 ));
                    out += padSpaces( cp1 ) ;
                	out += ans[0].toString();
                	out += "\n\n\n";
                	int cp2 = (int) (Math.ceil((float)(15 - ans[3].toString().length()) / 2 ));
                    out += padSpaces( cp2 ) ;
                	out += ans[3].toString();
            	}else if(className.equals("starbucks.MyCards")) {
            		 int conpad = (int) (Math.ceil((float)(15 - s.display().length()) / 2 ));
                     out += padSpaces( conpad ) ;
                     out += screen;
            	}else if(className.equals("starbucks.Settings")) {
            		String[] ans = s.display().split("\n");
                	System.err.println("Add card"+ans[0].toString()+ "Delete Card"+ans[1].toString()+ "Billing"+ans[2].toString()+ "Passcode"+ans[3].toString()+ "About|Terms"+ans[5].toString()+ "Help"+ans[6].toString());
                	int cp0 = (int) (Math.ceil((float)(15 - ans[0].toString().length()) / 2 ));
                    out += padSpaces( cp0 ) ;
                	out += ans[0].toString();
                	out += "\n";
                	int cp1 = (int) (Math.ceil((float)(15 - ans[1].toString().length()) / 2)) ;
                    out += padSpaces( cp1 ) ;
                	out += ans[1].toString();
                	out += "\n";
                	int cp2 = (int) (Math.ceil((float)(15 - ans[2].toString().length()) / 2 ));
                    out += padSpaces( cp2 ) ;
                	out += ans[2].toString();
                	out += "\n";
                	int cp3 = (int) (Math.ceil((float)(15 - ans[3].toString().length()) / 2 ));
                    out += padSpaces( cp3 ) ;
                	out += ans[3].toString();
                	out += "\n\n";
                	int cp5 = (int) (Math.ceil((float)(15 - ans[5].toString().length()) / 2 ));
                    out += padSpaces( cp5 ) ;
                	out += ans[5].toString();
                	out += "\n";
                	int cp6 = (int) (Math.ceil((float)(15 - ans[6].toString().length()) / 2 ));
                    out += padSpaces( cp6 ) ;
                	out += ans[6].toString();
            	}else if(className.equals("starbucks.AddCard")) {
            		String[] ans = s.display().split("\n");
                	System.err.println("Card ID: "+ans[0].toString()+ "Card code: "+ans[1].toString());
                	int cp0 = (int) (Math.ceil((float)(15 - ans[0].toString().length()) / 2)) ;
                    out += padSpaces( cp0 ) ;
                	out += ans[0].toString();
                	out += "\n";
                	int cp1 = (int) (Math.ceil((float)(15 - ans[1].toString().length()) / 2)) ;
                    out += padSpaces( cp1 ) ;
                	out += ans[1].toString();
                	out += "\n\n";
                	int cp3 = (int) (Math.ceil((float)(15 - ans[3].toString().length()) / 2)) ;
                    out += padSpaces( cp3 ) ;
                	out += ans[3].toString();
                	out += "\n";
                	int cp4 = (int) (Math.ceil((float)(15 - ans[4].toString().length()) / 2)) ;
                    out += padSpaces( cp4 ) ;
                	out += ans[4].toString();
                	out += "\n";
                	int cp5 = (int) (Math.ceil((float)(15 - ans[5].toString().length())/ 2)) ;
                    out += padSpaces( cp5 ) ;
                	out += ans[5].toString();
                	out += "\n";
                	int cp6 = (int) (Math.ceil((float)(15 - ans[6].toString().length()) / 2 ));
                    out += padSpaces( cp6 ) ;
                	out += ans[6].toString();
            	}else{
                 out += screen;
            	}
            	
                //dumpLines( out ) ; 
                
                int cnt2 = countLines( out ) ;
                int pad2 = 13 - cnt2 ;
                //System.err.println( "cnt2: " + cnt2 ) ;                
                //System.err.println( "pad2: " + pad2 ) ;
                //dumpLines( out ) ;
                String padlines = padLines( pad2 ) ;
                out += padlines ;
                out +=  "===============\n" ;
                out +=  "[A][B][C][D][E]\n" ;
                dumpLines( out ) ;
                return out ;             
            }

            /** Select Command A */
            public void selectA() { menuA.invoke() ; }

            /** Select Command B */
            public void selectB() { menuB.invoke() ; }

            /** Select Command C */
            public void selectC() { menuC.invoke() ; }

            /** Select Command D */
            public void selectD() { menuD.invoke() ; }

            /** Select Command E */
            public void selectE() { menuE.invoke() ; }

        } ;

        landscapeStrategy = new IOrientationStrategy() 
        {
            /**
             * Display Screen Contents
             * @param s Reference to Screen
             */
            public void display(IScreen s)
            {
                System.out.println( contents(s) ) ;
            }         

           /**
             * Display Contents of Frame + Screen 
             * @param  s Screen to Display
             * @return   Contents for Screen
             */
            public String contents(IScreen s) 
            { 
                String out = "" ;
       		 	StringBuffer tmp = new StringBuffer(out);
                tmp.append("================================\n");
                int nameLen = s.name().length() ;
                if (nameLen < 32 ) {
                    int pad = (32 - nameLen) / 2 ;
                    tmp.append(padSpaces( pad ));
                }
                tmp.append(s.name() + "\n");
                tmp.append("================================\n");
                String screen = s.display() + "\n" ;
                int cnt1 = countLines( screen ) ;
                int pad1 =(int) Math.ceil((float)(5 - cnt1) / 2);
                tmp.append(padLines( pad1 ));
                
                int screenLen = s.display().length();
                System.err.println("Screen contents length: "+screenLen);
                String[] ans = s.display().split("\n");
        		for(int i=0; i<ans.length;i++) {
        			int cp = (int) (Math.ceil((float)(32 - ans[i].toString().length()) / 2)) ;
        			tmp.append(padSpaces( cp ));
                	tmp.append(ans[i].toString());
                	if(i<ans.length - 1) {
                		tmp.append("\n");
                	}
        		}
                int cnt2 = countLines( out ) ;
                int pad2 = 9 - cnt2 ;
                String padlines = padLines( pad2 ) ;
                tmp.append(padlines);
                tmp.append("================================\n");
                out = tmp.toString();
                dumpLines( out ) ;
                return out ;
            }

             /** Don't Respond in Landscaope Mode */
            public void selectA() {  }

            /** Don't Respond in Landscaope Mode */
            public void selectB() {
            	System.err.println("Current screen in Landscape: "+current.getClass().getName());
            	if(current.getClass().getName().equals("starbucks.MyCardsPay")||current.getClass().getName().equals("starbucks.MyCards")) {
            	}else {
                	currentStrategy = portraitStrategy ;
            	}
            
            }

            /** Don't Respond in Landscaope Mode */
            public void selectC() { 
            	if(current.getClass().getName().equals("starbucks.MyCardsPay")||current.getClass().getName().equals("starbucks.MyCards")) {
            	}else {
                	currentStrategy = portraitStrategy ;
            	}            }

            /** Don't Respond in Landscaope Mode */
            public void selectD() { 
            	if(current.getClass().getName().equals("starbucks.MyCardsPay")||current.getClass().getName().equals("starbucks.MyCards")) {
            	}else {
                	currentStrategy = portraitStrategy ;
            	}            }

            /** Don't Respond in Landscaope Mode */
            public void selectE() {
            	if(current.getClass().getName().equals("starbucks.MyCardsPay")||current.getClass().getName().equals("starbucks.MyCards")) {
            	}else {
                	currentStrategy = portraitStrategy ;
            	}            }

       } ;     

        /* set default layout strategy */
        currentStrategy = portraitStrategy ;
    }

    /**
     * Change the Current Screen
     * @param s Screen Object
     */
    public void setCurrentScreen( IScreen s )
    {
        current = s ;
    }

    /**
     * Configure Selections for Command Pattern 
     * @param slot A, B, ... E
     * @param c    Command Object
     */
    public void setMenuItem( String slot, IMenuCommand c )
    {
        if ( "A".equals(slot) ) { menuA.setCommand(c) ;  }
        if ( "B".equals(slot) ) { menuB.setCommand(c) ; }
        if ( "C".equals(slot) ) { menuC.setCommand(c) ; }
        if ( "D".equals(slot) ) { menuD.setCommand(c) ; } 
        if ( "E".equals(slot) ) { menuE.setCommand(c) ; }   
    }

    /** 
     * Send Touch Event
     * @param x X Coord
     * @param y Y Coord
     */
    public void touch(int x, int y)
    {
    	System.err.println("Current Strategy is: "+currentStrategy.toString());
        if ( current != null && currentStrategy!=landscapeStrategy )
            current.touch(x,y) ;

    }

    /**
     * Get Contents of the Frame + Screen 
     * @return Frame + Screen Contents
     */
    public String contents() 
    { 
        if ( current != null )
        {
            return currentStrategy.contents( current ) ; 
        } 
        else 
        {
            return "" ;
        }
    }

    /** Display Contents of Frame + Screen */
    public void display()
    {
        if ( current != null )
        {
            currentStrategy.display( current ) ;
        }
    }
 
    /**
     *  Execute a Command 
     * @param c Command
     */
    public void cmd( String c ) 
    {
        if ( "A".equals(c) ) { selectA() ; }
        if ( "B".equals(c) ) { selectB() ; }
        if ( "C".equals(c) ) { selectC() ; }
        if ( "D".equals(c) ) { selectD() ; }        
        if ( "E".equals(c) ) { selectE() ; }        
    }

    /** Select Command A */
    public void selectA() { currentStrategy.selectA() ;  }

    /** Select Command B */
    public void selectB() { currentStrategy.selectB() ;  }

    /** Select Command C */
    public void selectC() { currentStrategy.selectC() ;  }

    /** Select Command D */
    public void selectD() { currentStrategy.selectD() ;  }

    /** Select Command E */
    public void selectE() { currentStrategy.selectE() ;  }    

}
