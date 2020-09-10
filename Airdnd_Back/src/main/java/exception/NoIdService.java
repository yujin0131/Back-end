package exception;

public class NoIdService {
	public void NoIdMethod(int idx)throws NoId{
        System.out.println("이메일체크를 시작합니다.");
        if(idx == -1){
            throw new NoId("이메일이 존재하지 않습니다.");
        }
        System.out.println("이메일체크를 종료합니다.");
    }
}
