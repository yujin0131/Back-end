package common;

import java.util.Random;

public class Common {
	public static final String VIEW_PATH = "WEB-INF/views/";
	public String sessonKey() {
		
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		
		//숫자와 영문 대소문자 섞은 10자
		for (int i = 0; i < 10; i++) {
		    int rIndex = rnd.nextInt(3);
		    switch (rIndex) {
		    case 0:
		        // a-z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1:
		        // A-Z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
		        break;
		    case 2:
		        // 0-9
		        temp.append((rnd.nextInt(10)));
		        break;
		    }
		}
		String sessionKey = temp.toString();
		return sessionKey;
	}
	
}