package ex07객체디자인.ex705상태와동작결합하기;

/**
 * 7.5 상태와 동작 결합하기
 * @author jkoogi
 * . 객체지향 기본 (상태와 동작으로 구성)
 * Hull - 상태
 * HullRepairUnit - 동작(holes 수정)
 *  - 정보은닉 불가(Hull은 자신의 상태를 노출, 매개변수 검증 누락)
 *  - 장황해지는 코드
 * * 상태와 동작의 분리 경향 : 큰 클래스, 자신에 속한 메서드 매개변수만 연산 
 *  > 비슷한 작업을 수행하는 변수와 메서드를 하나의 클래스로 간소화
 */
/* 대상소스 */
//public class Hull {
//	int holes;
//}
//
//class HullRepairUnit{
//	void repairHole(Hull hull) {
//		if(isIntact(hull)) {
//			return;
//		}
//		hull.holes--;
//	}
//
//	boolean isIntact(Hull hull) {
//		return hull.holes == 0;
//	}
//}

/* 개선소스 
 * - 간결해진 코드(HullRepareUnit 제거)
 *  . 클래스의 메서드는 내부상태를 직접 쉽게 처리
 *  . 메서드 매개변수 개수도 줄고, 이해도 증가
 *  . 매개변수 검증(필요시)
 *  . getter, setter로 holes 속성을 외부에 노출할 필요없음
 * - 메서드 내 입력 매개변수만 다루고 자신이 속한 클래스의 인스턴스 변수는 다루지 않은 경우
 *  . 상태와 동작이 분리되었다는 의미
 *  . 정보은닉 불가
 *  . 많은정보 공개시 버그유발
 * - 상태없는 클래스
 *  . 웹 프레임워크의 컨트롤러에는 필드없이 메서드 매개변수만 있음
 *  . 상태를 DB로 관리하는 대량의 병렬요청 처리 컨트롤러   
 * */
class Hull{
	int holes;
	
	void repairHole() {
		if(isIntact()) {
			return;
		}
		holes--;
	}

	boolean isIntact() {
		return holes == 0;
	}
}

/*이해필요
 * .현실적으로 Hull을 수정하는 어떤 로봇이 있을텐데 처음에는 다소 이상해보인다.
 * .하지만 프로그램에서 상태와 동작이 없는 단위라면 그러한 단위를 표현하는 클래스도 있으면 안됨.
 * .대신 Hull 클래스 스스로 기능을 제공.
 * .이와 비슷하게 다른 시스템 유형에 들어 있을만한 Order, User 도 
 *   매니저, 컨트롤러, 서비스, 그 외 비상태(stateless)클래스 없이 스스로 기능을 제공할 수 있음.
 * */
