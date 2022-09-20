/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

import java.util.ArrayList;

/**
 * Add New Card Screen
 */
public class AddCard extends Screen
{
	
    private String focusState;
    private String cardFocus = "card";
    private String codeFocus = "code";
    private KeyPad kp ;
    private CardNumber cn ;
    private CodeNumber cdn ;
    private Spacer sp ;
    StarbucksAPI api = new StarbucksAPI();

    public AddCard()
    {
    	kp = new KeyPad() ;
        cn = new CardNumber();
        cdn = new CodeNumber() ;
        sp = new Spacer();
    System.err.println("Focused on card id");
    this.addSubComponent(cn);
    this.addSubComponent(cdn);
    this.addSubComponent(sp);
    this.addSubComponent(kp);
    }
    
    /**
     * Display
     * @return Screen Content
     */
    public String display() {
        return super.display() ;
    }
    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen
     */
    public String name() {
    	return "Add Card";
    }
    
    /**
     * Frame Previous Screen
     */
    public void prev() {
    	// << Change Me!  Hard Coded to Get a Random Card for Now >>
        AppController app = AppController.getInstance() ;
        app.setScreen( AppController.SCREENS.SETTINGS ) ;
    }

   /**
     * Frame Next Screen
     */
    public void next() {
        AppController app = AppController.getInstance() ;
		if(cn.getCount()<9||cdn.getCount()<3||cn.getCardNumber().equals("000000000")) {
			this.cn.setCardNumber("");
 	        this.cn.setCount(0);
 	        this.cdn.setCodeNumber("");
 	        this.cdn.setCount(0);
 	        this.kp.setCountPinDigits(0);
 	        this.focusState = cardFocus;
			app.setScreen( AppController.SCREENS.ADD_CARD ) ;
		}else {
		starbucks.StarbucksAPI.Card card = null;
        try {
 			card = api.activateCard(cn.getCardNumber(), cdn.getCodeNumber());
 		} catch (Exception e) {
 			System.err.println("Api call failed as this card doesn't exist");
 			app.setScreen( AppController.SCREENS.MY_CARDS_MAIN ) ;
 		}
        if(card != null) {
	        Card setcard = Card.getInstance();
	        setcard.setTheCard(card.getCardId(), card.getCardCode(), card.getBalance());
	        this.cn.setCardNumber("");
	        this.cn.setCount(0);
	        this.cdn.setCodeNumber("");
	        this.cdn.setCount(0);
	        this.kp.setCountPinDigits(0);
	        this.focusState = cardFocus;
	        app.setScreen( AppController.SCREENS.MY_CARDS_MAIN ) ;
	        }
		}
    }
    
    /**
     * touch
     * @param x [x]
     * @param y [y]
     */
    public void touch(int x, int y) {
        if(x==1&&y==2||x==2&&y==2||x==3&&y==2) {
        	focusState = cardFocus;
        }else if(x==2&&y==3) {
        	focusState = codeFocus;
        }else {
        	if(focusState == null || focusState.isEmpty()) {
        		focusState = cardFocus;
        	}
        	if(focusState.equals(cardFocus)) {
        		kp.removeObserver(cdn);
        		if(kp != null && kp.getObservers() != null && kp.getObservers().size() == 0)
        		{
        			((IKeyPadSubject)kp).attach( cn ) ;
        		}
        		kp.setCountPinDigits(cn.getCount());
        		cn.setFocusState(focusState);
        		cn.touch(x, y);
        		
        	}else if(focusState.equals(codeFocus)){
        		kp.removeObserver(cn);
        		if(kp != null && kp.getObservers() != null && kp.getObservers().size() == 0)
        		{
        			((IKeyPadSubject)kp).attach( cdn ) ;
        		}
        		kp.setCountPinDigits(cdn.getCount());
        		cdn.setFocusState(focusState);
        		cdn.touch(x, y);
        	}
        }

    } 
}
