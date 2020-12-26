package ex01우선정리부터.ex0107괄호사용;

import java.util.Objects;

import ex01우선정리부터.ex0107괄호사용.sub.CruiseControl;
import ex01우선정리부터.ex0107괄호사용.sub.User;

/**
 * 1.7 항상 괄호 사용하기
 * @author jkoogi
 * 1. 괄호를 적용하여 시인성 확보
 */
public class BoardComputer {

	static CruiseControl cruiseControl;		// main 실행용 static
	
	static void authorize(User user) {		// main 실행용 static
		cruiseControl = new CruiseControl();		//실행추가코드
		
		Objects.requireNonNull(user);
		
		/* 대상소스 */
//		if(user.isUnknown())
//			cruiseControl.logUnauthorizedAccessAttempt();
//		if(user.isAstronaut())
//			cruiseControl.grantAccess(user);
//		if(user.isCommander())
//			cruiseControl.grantAccess(user);
//			cruiseControl.grantAdminAccess(user); // * switch 실패의 변형
		
		/* 개선소스 */
		//*괄호를 적용하여 시인성 확보
		if(user.isUnknown()) {
			cruiseControl.logUnauthorizedAccessAttempt();
		}
		if(user.isAstronaut()) {
			cruiseControl.grantAccess(user);
		}
		if(user.isCommander()) {
			cruiseControl.grantAccess(user);
		}
		cruiseControl.grantAdminAccess(user); // 보안위험
	}
	
	public static void main(String[] args) {		//실행추가코드
		User user = new User();
		System.out.println("start : "+user.getRank());
		
		authorize(user);
	}
}
