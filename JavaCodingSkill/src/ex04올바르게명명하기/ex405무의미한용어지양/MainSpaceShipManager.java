package ex04올바르게명명하기.ex405무의미한용어지양;

import ex04올바르게명명하기.ex405무의미한용어지양.sub.AbstractRocketPropulsionEngine;
import ex04올바르게명명하기.ex405무의미한용어지양.sub.Engine;
import ex04올바르게명명하기.ex405무의미한용어지양.sub.INavigationController;
import ex04올바르게명명하기.ex405무의미한용어지양.sub.LogHelper;
import ex04올바르게명명하기.ex405무의미한용어지양.sub.Navigation;
import ex04올바르게명명하기.ex405무의미한용어지양.sub.Planet;
import ex04올바르게명명하기.ex405무의미한용어지양.sub.PlanetInfo;
import ex04올바르게명명하기.ex405무의미한용어지양.sub.RouteData;

/**
 * 4.5 무의미한 용어 쓰지 않기
 * @author jkoogi
 * 1. 긴 이름도 부담(축약없이 모든 이름 풀자?) : 삭제해도 되는 무의미한 단어 정리
 *  - main, manager, data, info, flag : 자주쓰이는 무의미한 용어(low-hanging fruits 가장쉽게 해결할 수 있는 방법)
 *  - abstract, invoke 메서드명에서의 의미?
 *  * 의미를 그대로 전달하면서 뺄수 있는 부분?
 */
public class MainSpaceShipManager {

	/* 대상소스 */
//	AbstractRocketPropulsionEngine abstractRocketPropulsionEngine;
//	INavigationController navigationController;
//	boolean turboEnabledFlag;
//	
//	void navigateSpaceShipTo(PlanetInfo planetInfo) {
//		RouteData data = navigationController.calculateRouteData(planetInfo);
//		LogHelper.logRouteData(data);
//		abstractRocketPropulsionEngine.invokeTask(data, turboEnabledFlag);
//	}
	
	/* 개선소스 
	 * 1. 코드를 읽고 생각할 양이 가벼워짐
	 *  - 무의미한 용어 제거 : data, info, flag - 코드에서 의미없는 내용
	 *  - 타입제거 : abstract, impl, I(인터페이스 접두사) - 선언으로 타입이 명시됨
	 *  - 클래스명 :SpaceShip, rocket - 도메인 지정자 제거 (멤버에 대한 맥락 파악 가능)
	 *  - 메서드명 : 매개변수 종류는 중복표현으로 제거. invoke, call, do 외 간결하고 의미있는 동사 사용
	 *  - 패키지명 : misc, other, util 등 무의미한 단어 지양
	 *  
	 *  * Spring 프레임워크 클래스명 : 장미는 붉고 잎사귀는 푸른데 유독 자바에만 AbstractSingletonProxyFactoryBean
	 * */
	Engine engine;
	Navigation navigator;
	boolean turboEnabled;
	
	void navigateTo(Planet destination) {
		RouteData route = navigator.calculateRouteData(destination);
		LogHelper.logRouteData(route);
		engine.follow(route, turboEnabled);
	}
}
//4.4축약쓰지않기