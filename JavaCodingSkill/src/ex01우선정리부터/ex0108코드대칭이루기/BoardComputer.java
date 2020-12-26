package ex01우선정리부터.ex0108코드대칭이루기;

import java.util.Objects;

import ex01우선정리부터.ex0107괄호사용.sub.CruiseControl;
import ex01우선정리부터.ex0107괄호사용.sub.User;

/**
 * 1.8 코드 대칭 이루기
 * @author jkoogi
 * 1. 코드대칭성 구조
 * 켄트백 : 거의 같은 것들은 똑같은 부분과 완전히 다른 부분으로 나눌 수 있다.
 *  - 모든 분기가 비슷한 관심사를 표현하는지
 *  - 병렬구조인지
 *  - 분기가 모두 대칭인지
 */
public class BoardComputer {

	static CruiseControl cruiseControl;		// main 실행용 static
	
	static void authorize(User user) {		// main 실행용 static
		cruiseControl = new CruiseControl();		//실행추가코드
		
		Objects.requireNonNull(user);

		/* 대상소스 */
		// 모든 조건과 명령문을 한번에 이해해야 함 - 코드 대칭성(code symmetry)의 부재
//		if(user.isUnknown()) {
//			// 분기 속성 : 접근거절
//			cruiseControl.logUnauthorizedAccessAttempt();
//		}else if(user.isAstronaut()) {
//			// 분기 속성 : 접근부여
//			cruiseControl.grantAccess(user);
//		}else if(user.isCommander()) {
//			// 분기 속성 : 접근부여
//			cruiseControl.grantAccess(user);
//			cruiseControl.grantAdminAccess(user);
//		}
		
		/* 개선소스 */
		// * 코드 대칭성에 따른 분리
		// 분기 속성 : 접근거절
		if(user.isUnknown()) {
			cruiseControl.logUnauthorizedAccessAttempt();
			return;
		}
		// 분기 속성 : 접근부여
		if(user.isAstronaut()) {
			cruiseControl.grantAccess(user);
		}else if(user.isCommander()) {
			cruiseControl.grantAccess(user);
			cruiseControl.grantAdminAccess(user);
		}
		// jkoogi : cruiseControl.grantAccess(user); 코드가 접근부여 두 분기에 모두 실행되니 분기전에 실행하고 commander 여부에 따라 남은 분기처리가 어떨지.
	}
	
	public static void main(String[] args) {		//실행추가코드
		User user = new User();
		System.out.println("start : "+user.getRank());
		
		authorize(user);
	}
}
