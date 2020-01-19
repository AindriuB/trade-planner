package ie.rsi.trader.exception;

public class NotFoundException extends Exception {

    
    /**
     * 
     */
    private static final long serialVersionUID = -7683676331964613955L;

    public NotFoundException() {
	super("Resource not found");
    }
}
