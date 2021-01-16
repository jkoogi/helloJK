package ex08데이터흐름.ex08선택필드나매개변수피하기;

import java.util.Objects;
import java.util.Optional;

import ex08데이터흐름.ex08선택필드나매개변수피하기.sub.Connection;

/**
 * 8.8 선택 필드나 매개변수 피하기
 * @author jkoogi
 * - null 참조 대시 Optional 반환(8.7 널 대신 옵셔널), but 코드복잡도 증가, 오용 주의
 * > 예제 : Optional 적용- Connection이 없을 수 있음(명시적 표현) 
 *  . 게터(Optional<Connection> 반환) : 유익
 *  . 세터(Optional<Connection> 요청) : 해익
 *  . 속성(connectionToEarth) : 해익
 *  
 *  * Optional : 부재[absent-Optional.empty()]/존재[present] & null
 *    (세터 파라미터, 객체 속성은 null이 적용 가능 - null 검증과 존재여부 검증 필요.)
 *    
 *   
 */
public class Communicator {
	/* 대상소스 */
//	Optional<Connection> connectionToEarth;
//	
//	void setConnectionToEarth(Optional<Connection> connectionToEarth) {
//		this.connectionToEarth = connectionToEarth;
//	}
//
//	Optional<Connection> getConnectionToEarth(){
//		return connectionToEarth;
//	}
	
	/* 개선소스 
	 * 1. 필드, 메서드 매개변수 타입에서 Optional 제거 : 반환값에만 사용(게터)
	 *  > Optional.empty(), null 값간의 의미상 혼란 방지
	 * 2. 세터 매개변수 : null 검증
	 *  - Objects.requireNonNull()
	 * 3. 매개변수를 null로 설정할 필요가 있을 경우 reset() 메서드를 추가하여 명시적으로 상태 관리
	 *  - 세터에 null을 사용하지 않도록(용도 구분 7.2 옵션 매개변수로 메서드 분할)
	 *  > 모든 null 참조는 필드 접근을 통제하여 '클래스 내부에서만' 발생
	 * 4. 게터에 적용한 Optional
	 *  - Optional.ofNullable()로 상태를 조회하여 필드를 Optional로 변환하고,
	 *    응답결과에 null의 가능성을 명시적으로 표현
	 *  * 속성(connectionToEarth)의 게터, 세터 메서드의 타입이 다르다.
	 *    - 게터 : Optional<Connection>, 세터 : Connection
	 *    > 자바 빈스 규칙에 부합하지 않는 지적이 있을 수 있다. (자체 가이드를 정하여 장단점을 활용)
	 *     
	 * */
	Connection connectionToEarth;
	
	void setConnectionToEarth(Connection connectionToEarth) {
		this.connectionToEarth = Objects.requireNonNull(connectionToEarth);
	}
	
	Optional<Connection> getConnectionToEarth(){
		return Optional.ofNullable(connectionToEarth);
	}

	void reSet() {
		connectionToEarth = null;
	}
}
