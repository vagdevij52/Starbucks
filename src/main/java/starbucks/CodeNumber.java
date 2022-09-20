package starbucks;
/**
 * CodeNumber class that represents Code number object to enter the Card Code
 */
public class CodeNumber implements ITouchEventHandler, IDisplayComponent, IKeyPadObserver{

	private String codeNumber;
	private int count;
	private String focusState;
	ITouchEventHandler nextHandler ;
	
	
	public String getFocusState() {
		return focusState;
	}

	public void setFocusState(String focusState) {
		this.focusState = focusState;
	}

	public CodeNumber() {
		this.codeNumber = "";
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
		if(key.equals("X")) {
			codeNumber = codeNumber.substring(0,c);
			count = c;
		}
		else{ 
			if(count<3) {
			System.err.println( "Key: " + key ) ;
			codeNumber+=key;
			count = c;
		}
		}
		
		
	}

	@Override
    /**
     * Display Screen Contents to Terminal
     */
	public String display() {
		// TODO Auto-generated method stub
		return "["+codeNumber+"]";
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
		if ( nextHandler != null )
            nextHandler.touch(x,y) ;
		System.err.println(x + " " + y);
		
	}

	@Override
	public void setNext(ITouchEventHandler next) {
		// TODO Auto-generated method stub
		
		nextHandler = next;
		
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCodeNumber() {
		return codeNumber;
	}

	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}
	
	
	

}
