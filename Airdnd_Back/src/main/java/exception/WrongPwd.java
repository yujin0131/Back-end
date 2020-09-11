package exception;

public class WrongPwd extends RuntimeException{
	public WrongPwd(String msg){
        super(msg);
    }       
    public WrongPwd(Exception ex){
        super(ex);
    }
}
