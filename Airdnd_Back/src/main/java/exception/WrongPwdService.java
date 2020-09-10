package exception;

public class WrongPwdService {
	public void WrongPwdMethod(String result) throws WrongPwd{
        System.out.println("비밀번호 체크를 시작합니다.");
        if(result.equals("Fail")){
            throw new NoId("비밀번호가 틀렸습니다.");
        }
        System.out.println("비밀번호 체크를 종료합니다.");
    }
}
