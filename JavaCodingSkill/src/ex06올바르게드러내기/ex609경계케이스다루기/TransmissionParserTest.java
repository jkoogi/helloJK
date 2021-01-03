package ex06올바르게드러내기.ex609경계케이스다루기;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import ex06올바르게드러내기.ex609경계케이스다루기.sub.TransmissionParser;
import ex06올바르게드러내기.ex609경계케이스다루기.sub.Transmisson;

/**
 * 6.9 경계 케이스 다루기
 * @author jkoogi
 *  - 유효한 입력(String 파라미터)이 정상출력(Transmission 유효정보)을 생성하는지 테스트
 *  > 일반적이지 않은 입력문자열 처리 테스트
 *  일반적이지 않은 입력값 : 일반적 입력보다 시스템 내 버그를 더 잘 찾을 수 있음
 *   - null(null참조)
 *   - ""(빈 문자열)
 *   - " "(공백문자)
 *   - "a\ntlioan\nb"(영문이 아닌 특수문자를 포함한 문자열)
 * * 매개변수 데이터의 타입경계 정도는 최소한 테스트 필요
 *  int : 0, 1, -1, Integer.MAX_VALUE, Integer.MIN_VALUE
 *  double : 0, 1.0, -1.0, Double.MAX_VALUE, Double.MIN_VALUE
 *  Object[] : null, {}, {null}, {new Object(), null}
 *  List<Ojbect> : null, Collections.emptyList(), Collections.singletonList(null), Arryas.asList(new Object(),null)
 *  이메일주소, 파일주소 등
 */
public class TransmissionParserTest {
	@Test
	public void testValidTransmission() {
		TransmissionParser parser = new TransmissionParser();
		
		Transmisson transmisson = parser.parse("032Houston, UFO sighted!");
		
		Assertions.assertEquals(32, transmisson.getId());
		Assertions.assertEquals("Houston, UFO sighted!", 
				transmisson.getContent());
	}
	
	@Test
	public void nullShouldThrowIllegalArgumentException() {
		Executable when = () -> new TransmissionParser().parse(null);
		Assertions.assertThrows(IllegalArgumentException.class, when);
	}
	
	@Test
	private void malformedTransmissionShouldThrowIllegalArgumentException() {
		Executable when = () -> new TransmissionParser().parse("t-*liija");
		Assertions.assertThrows(IllegalArgumentException.class, when);
	}
	/*추가 가능한 테스트 유형
	 * - 기대 결과값과 일부 다른 메시지 
	 * - 메시지 부분들의 순서가 바뀐 경우
	 * - 요건에 따라 특정 입력 그룹에 대해 동작하는지 
	 * ...
	 * 
	 *  다양한 유형이 존재하지만, 경제적으로 판단하여 유효한 유형들을 구현한다.
	 *  테스트에 없는 유형을 발견하면 정상 동작을 확인하기 위해 테스트 추가
	 * */
}
