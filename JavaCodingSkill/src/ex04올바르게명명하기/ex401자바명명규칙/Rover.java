package ex04올바르게명명하기.ex401자바명명규칙;

/**
 * 4.1 자바 명명 규칙 사용하기
 * @author jkoogi
 * ! CamelCase 명명규칙으로 모두 대문자 사용
 * - 자바 페키지명은 모두 소문자
 * - 클래스(생성자), 인터페이스, enum 명 : 대문자시작 CamelCase => 파스칼
 * - 상수(static final) : 모두대문자, 밑줄 단어구분자
 * - 메서드, 필드 매개변수, 변수 : 소문자시작 CamelCase
 * > 메서드, 변수 구분 : 메서드(동작:동사or동사시작), 변수(상태:명사)
 */
public class Rover {

	/* 대상소스 */
//	static final double WalkingSpeed = 3;
//	
//	final String SerialNumber;
//	double MilesPerHour;
//	
//	Rover(String NewSerialNumber) {
//		SerialNumber = NewSerialNumber;
//	}
//	
//	void Drive() {
//		MilesPerHour = WalkingSpeed;
//	}
//	
//	void Stop() {
//		MilesPerHour = 0;
//	}
	
	/* 개선소스 */
	static final double WALKING_SPEED = 3;
	
	final String serialNumber;
	double milesPerHour;
	
	Rover(String newSerialNumber) {
		serialNumber = newSerialNumber;
	}
	
	void drive() {
		milesPerHour = WALKING_SPEED;
	}
	
	void stop() {
		milesPerHour = 0;
	}
}
