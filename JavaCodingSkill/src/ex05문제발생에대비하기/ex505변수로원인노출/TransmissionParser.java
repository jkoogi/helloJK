package ex05문제발생에대비하기.ex505변수로원인노출;

import ex05문제발생에대비하기.ex502구체적인예외잡기.sub.Transmission;
import ex05문제발생에대비하기.ex505변수로원인노출.sub.MalformedMessageException;

/**
 * 5.5 변수로 원인 노출
 * @author jkoogi
 * 1. 예외 : 필드와 메서드, 생성자를 갖는 클래스 *  - 기계가 읽을 수 있는 방식으로 예외의 원인 표현  
 *  > 중복코드(rawMessage) : 일관성취약 
 *  > 감춰진 정보(rawMessage > 예외 message에 매핑) : 접근성 취약
 */
public class TransmissionParser {

	static Transmission parse(String rawMessage) {
		if(rawMessage != null
				&& rawMessage.length() != Transmission.MESSAGE_LENGTH
				) {
			/* 대상소스 */
//			throw new IllegalArgumentException(
//					String.format("Exception %d, but got %d characters in '%s'",
//							Transmission.MESSAGE_LENGTH, rawMessage.length(),
//							rawMessage));
			/* 개선소스 
			 * - 사용자예외(MalformedMessageException)에 raw 메시지 필드를 정의(생성자로 설정)하여 활용
			 *  추가 정보, 처리를 보완하여 활용하기 용이함
			 *  * 예외 클래스의 raw 필드를 final로 선언하여 일관성 추구
			 *  
			 * */
			throw new MalformedMessageException(
					String.format("Exception %d, but got %d characters in '%s'", 
							Transmission.MESSAGE_LENGTH, rawMessage.length()),
							rawMessage);
		}
		
		String rawId = rawMessage.substring(0,Transmission.ID_LENGTH);
		String rawContent = rawMessage.substring(Transmission.ID_LENGTH);
		try {
			int id = Integer.parseInt(rawId);
			String content = rawContent.trim();
			return new Transmission(id, content);

		} catch (NumberFormatException e) {

			/* 대상소스 */
//			throw new IllegalArgumentException(
//					String.format("Exception number, but got '%s' in '%s'", 
//							rawId,rawMessage), e);
			/* 개선소스 - MalformedMessageException : 사용자예외 정의 */
			throw new MalformedMessageException(
					String.format("Exception number, but got '%s'", rawId),
					rawMessage, e);
		}
	}
	
	public static void main(String[] args) {
		parse("");
		/* 대상소스 실행결과 */
//		Exception in thread "main" java.lang.IllegalArgumentException: Exception 5, but got 0 characters in ''
//		at item05문제발생에대비하기.ex505변수로원인노출.TransmissionParser.parse(TransmissionParser.java:21)
//		at item05문제발생에대비하기.ex505변수로원인노출.TransmissionParser.main(TransmissionParser.java:58)

		/* 개선소스 실행결과 */
//		Exception in thread "main" java.util.MissingFormatArgumentException: Format specifier '%s'
//		at java.util.Formatter.format(Formatter.java:2519)
//		at java.util.Formatter.format(Formatter.java:2455)
//		at java.lang.String.format(String.java:2940)
//		at item05문제발생에대비하기.ex505변수로원인노출.TransmissionParser.parse(TransmissionParser.java:32)
//		at item05문제발생에대비하기.ex505변수로원인노출.TransmissionParser.main(TransmissionParser.java:58)

		parse("A2345");
		/* 대상소스 실행결과 */
//		Exception in thread "main" java.lang.IllegalArgumentException: Exception number, but got 'A234' in 'A2345'
//		at item05문제발생에대비하기.ex505변수로원인노출.TransmissionParser.parse(TransmissionParser.java:47)
//		at item05문제발생에대비하기.ex505변수로원인노출.TransmissionParser.main(TransmissionParser.java:58)
//	Caused by: java.lang.NumberFormatException: For input string: "A234"
//		at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
//		at java.lang.Integer.parseInt(Integer.java:580)
//		at java.lang.Integer.parseInt(Integer.java:615)
//		at item05문제발생에대비하기.ex505변수로원인노출.TransmissionParser.parse(TransmissionParser.java:40)
//		... 1 more

		/* 개선소스 실행결과 */
//		Exception in thread "main" item05문제발생에대비하기.ex505변수로원인노출.sub.MalformedMessageException: Exception number, but got 'A234' in 'A2345'
//		at item05문제발생에대비하기.ex505변수로원인노출.TransmissionParser.parse(TransmissionParser.java:51)
//		at item05문제발생에대비하기.ex505변수로원인노출.TransmissionParser.main(TransmissionParser.java:58)
//	Caused by: java.lang.NumberFormatException: For input string: "A234"
//		at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
//		at java.lang.Integer.parseInt(Integer.java:580)
//		at java.lang.Integer.parseInt(Integer.java:615)
//		at item05문제발생에대비하기.ex505변수로원인노출.TransmissionParser.parse(TransmissionParser.java:40)
//		... 1 more

	}
}
