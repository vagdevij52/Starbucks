package starbucks;

/**
 * CardNumber class that represents Card number object to enter the Card Id
 */
public class CardNumber implements ITouchEventHandler, IDisplayComponent, IKeyPadObserver{

	private String cardNumber;
	private String focusState;
	private int count;
	ITouchEventHandler nextHandler ;
	
	public String getFocusState() {
		return focusState;
	}

	public void setFocusState(String focusState) {
		this.focusState = focusState;
	}

	public CardNumber() {
		this.cardNumber = "";
		count = 0;
	}
	
	@Override
    /**
     * Key Event to Notify Observers 
     * @param numKeys Number of Digits So Far
     * @param key     Key/Digit Pressed
     */
	public void keyEventUpdate(int c, String key) {
		// TODO Auto-generated method stub
		System.err.println( "Key: " + key ) ;
		if(key.equals("X")) {
			cardNumber = cardNumber.substring(0,c);
			count = c;
		}
		else{
			 if(count < 9) {
			 cardNumber+=key;
		     count = c ;
			 }
		}
		
	}

	@Override
    /**
     * Display Screen Contents to Terminal
     */
	public String display() {
		// TODO Auto-generated method stub
		return "["+cardNumber+"]";
	}

	@Override
    /**
     * Add Display Component to Screen
     * @param c Display Component
     */
	public void addSubComponent(IDisplayComponent c) {
		// TODO Auto-generated method stub
		
	}

	@Override
    /**
     * User Touch at X,Y Coordinates
     * @param x X Coordinate
     * @param y Y Coordinate
     */
	public void touch(int x, int y) {
		// TODO Auto-generated method stub
		 if ( nextHandler != null )
             nextHandler.touch(x,y) ;
		System.err.println(x + " " + y);
		
	}

	@Override
	public void setNext(ITouchEventHandler next) {
		// TODO Auto-generated method stub
		nextHandler = next ;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	

}
