package ex01우선정리부터.ex0106스위치실패회피;

import java.util.Objects;

import ex01우선정리부터.ex0106스위치실패회피.sub.CruiseControl;
import ex01우선정리부터.ex0106스위치실패회피.sub.User;

/**
 * 1.6 스위치 실패 피하기
 * @author jkoogi
 * 1. 의도하지않은 switch fallthrough(무산되다) 주의 : break; 필수 (예외시 주석안내)
 * 2. 다른 관심사는 서로 다른 코드 블록에 배치
 */
public class BoardComputer {

	private static final String UNKNOWN   = "1";	//실행추가코드
	private static final String ASTRONAUT = "2";	//실행추가코드
	private static final String COMMANDER = "3";	//실행추가코드
	
	static CruiseControl cruiseControl;		// main 실행용 static
	
	static void authorize(User user) {		// main 실행용 static
		cruiseControl = new CruiseControl();		//실행추가코드
		
		Objects.requireNonNull(user);
		switch (user.getRank()) {
		//switch 문에서 정수. 문자. enum(열거형), 문자열(java7)
		case UNKNOWN:
			System.out.println("UNKNOWN");			//실행추가코드
			cruiseControl.logUnauthorizedAccessAttempt();
			break;	/* 추가소스 */// * switch fallthrough 방지코드 
		case ASTRONAUT:
			System.out.println("ASTRONAUT");		//실행추가코드
			cruiseControl.grantAccess(user);
			break;
		case COMMANDER:
			System.out.println("COMMANDER");		//실행추가코드
			cruiseControl.grantAccess(user);
			cruiseControl.grantAdminAccess(user);
			break;
		}
	}
	
	public static void main(String[] args) {		//실행추가코드
		User user = new User();
		System.out.println("start : "+user.getRank());
		
		authorize(user);
		/*
		UNKNOWN
		ASTRONAUT
		> break; 문이 누락되어 의도하지않은 switch fallthrough(무산되다) 실행 
		*/
		System.out.println("test Objects.requireNonNull");
		try {
			authorize(null);	
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
}
