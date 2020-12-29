package ex05문제발생에대비하기.ex503메시지로원인설명;

import ex05문제발생에대비하기.ex502구체적인예외잡기.sub.Transmission;

/**
 * 5.3 메시지로 원인 설명
 * @author jkoogi
 * 1. 예외를 잡는것에 더해, 던지는것까지 예외처리에 포함
 *  - 예외 타입을 기준으로 처리 : 전후맥락정보를 보완하여 예외추적 필요
 */
public class TransmissionParser {

	static Transmission parse(String rawMessage) {
		if(rawMessage != null
				&& rawMessage.length() != Transmission.MESSAGE_LENGTH
				) {
			/* 대상소스 */
//			throw new IllegalArgumentException();

			/* 개선소스 
			 * - 예외정보 : 예외 조치에 필요한 정보(문자140개), 제공된 정보(문자3개), 전체 맥락(메시지:"abc")
			 * > 구성한 예외정보를 바탕으로 테스트케이스 구현현으로 재사용 : JUnit 버그픽스 작성, 회귀테스트 사용(6장)
			 * */
			throw new IllegalArgumentException(
					String.format("Exception %d, but got %d characters in '%s'", 
							Transmission.MESSAGE_LENGTH, rawMessage.length(),
							rawMessage));
		}
		
		String rawId = rawMessage.substring(0,Transmission.ID_LENGTH);
		String rawContent = rawMessage.substring(Transmission.ID_LENGTH);
		try {
			int id = Integer.parseInt(rawId);
			String content = rawContent.trim();
			return new Transmission(id, content);
		} catch (NumberFormatException e) {

			/* 대상소스 */
			throw new IllegalArgumentException("Bad message received!");
			
			/* 개선소스 */
//			throw new IllegalArgumentException(
//					String.format("Exception number, but got '%s' in '%s'", 
//							rawId,rawMessage));
		}
	}
	
	public static void main(String[] args) {
		
		parse("");
		/* 대상소스 실행결과 */
//		Exception in thread "main" java.lang.IllegalArgumentException
//		at item05문제발생에대비하기.ex503메시지로원인설명.TransmissionParser.parse(TransmissionParser.java:18)
//		at item05문제발생에대비하기.ex503메시지로원인설명.TransmissionParser.main(TransmissionParser.java:49)

		/* 개선소스 실행결과 */
//		Exception in thread "main" java.lang.IllegalArgumentException: Exception 5, but got 0 characters in ''
//		at item05문제발생에대비하기.ex503메시지로원인설명.TransmissionParser.parse(TransmissionParser.java:24)
//		at item05문제발생에대비하기.ex503메시지로원인설명.TransmissionParser.main(TransmissionParser.java:49)

		parse("abc45");
		/* 대상소스 실행결과 */
//		Exception in thread "main" java.lang.IllegalArgumentException: Bad message received!
//		at item05문제발생에대비하기.ex503메시지로원인설명.TransmissionParser.parse(TransmissionParser.java:39)
//		at item05문제발생에대비하기.ex503메시지로원인설명.TransmissionParser.main(TransmissionParser.java:54)

		/* 개선소스 실행결과 */
//		Exception in thread "main" java.lang.IllegalArgumentException: Exception number, but got 'abc4' in 'abc45'
//		at item05문제발생에대비하기.ex503메시지로원인설명.TransmissionParser.parse(TransmissionParser.java:42)
//		at item05문제발생에대비하기.ex503메시지로원인설명.TransmissionParser.main(TransmissionParser.java:49)

	}
}
//5.4 원인 사슬 깨지 않기 - 예외를 직접 처리하기 어려울 때, 다시 던지거나 일반예외 유형으로 변환 처리
