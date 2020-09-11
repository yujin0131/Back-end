package exception;

public class NoId extends RuntimeException{
	public NoId(String msg){
        super(msg);
    }       
    public NoId(Exception ex){
        super(ex);
    }
}
