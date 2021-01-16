package ex08데이터흐름.ex07널대신옵셔널;

import java.util.Optional;

import ex08데이터흐름.ex07널대신옵셔널.sub.Connection;

/**
 * 8.7 널 대신 옵셔널
 * @author jkoogi
 * - 객체를 가리키지 않는 참조 : null - null 참조시 메서드 호출할 경우 NPE
 *  > 객체 내부 상태 참조시 접근통제를 전재로 사용
 *  > 외부 노출시 호출자가 null을 검증하여 사용
 *  * null 참조에 대한 대안 : optional
 *
 */
public class Communicator {
	Connection connectionToEarth;
	
	void estableishConnection() {
		// connectionToEarth를 할당하는 데 쓰이지만 불안정할 수 있음
	}
	/* 대상소스 
	 * - Connection : 끊길수도, null일수도 (호출시 null을 검증하지 않으면 NPE 오류발생)
	 *  > if문으로 null 검증처리 (람다표현식과 명쾌하기 조함은 불가능 - 표현식을 몇개 줄로 분리)
	 *  . 'connectionToEarth가 없을 수 있다' 명시적 처리?
	 * */
//	Connection getConnectionToEarth() {
//		return connectionToEarth;
//	}
	
	/* 개선소스 
	 * - Optional : 있을수도 있고, 없을수도 있는 객체를 위한 임시 저장소
	 *  > 대상속성 : connectionToEarth
	 *  . Optional<Connection> 명세로 Connection이 없을 수 있다고 명시함 
	 * */
	Optional<Connection> getConnectionToEarth() {
		return Optional.ofNullable(connectionToEarth);
	}
	
	/* 대상소스 실행코드 */
//	public static void main(String[] args) {
//		Communicator communicator = new Communicator();
//		communicator.getConnectionToEarth()
//					.send("Houston, we got a problem!");
//	}
//	Exception in thread "main" java.lang.NullPointerException
//	at ex08데이터흐름.ex07널대신옵셔널.Communicator.main(Communicator.java:28)

	/* 개선소스1 실행코드 
	 * - 코드개선, 실행결과 동일(NPE)
	 *  > Connection 객체가 없으면(orElse) null 반환 가능 - 코드를 통해 null 인지
	 * */
//	public static void main(String[] args) {
//		Communicator communicator = new Communicator();
//		Connection connection = communicator.getConnectionToEarth()
//											.orElse(null);
//		connection.send("Houston, we got a problem!");
//	}
//	Exception in thread "main" java.lang.NullPointerException
//	at ex08데이터흐름.ex07널대신옵셔널.Communicator.main(Communicator.java:54)
	
	/* 개선소스2 실행코드 
	 * - Optional을 통해 반환되는 부재값(orElse)의 처리를 고려한 개선
	 *  > ifPresent() : Optional 내 값이 null이 아닐때만 Consumer 실행
	 *  . 예제 : 메시지를 전송하는 Consumer 전달 - NPE 방어
	 *  
	 * - Optional 선언 없이 if문을 이용가능. but, Optional의 ifPresent() 메소드로 편의성 제공
	 * - Connection의 null 객체도 Optional.empty()를 이용하여 해결 가능. but, 매번 널 객체의 명시적 처리 필요
	 * - 람다를 비롯해 Optional 클래스 방식도 유효 *
	 * */
	public static void main(String[] args) {
		Communicator communicationSystem = new Communicator();
		communicationSystem.getConnectionToEarth()
				.ifPresent(connection ->
					connection.send("Houston, we got a problem!")
				);
	}

}

