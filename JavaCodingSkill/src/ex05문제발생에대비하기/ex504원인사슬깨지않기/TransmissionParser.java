package ex05문제발생에대비하기.ex504원인사슬깨지않기;

import ex05문제발생에대비하기.ex502구체적인예외잡기.sub.Transmission;

/**
 * 5.4 원인 사슬 깨지 않기
 * @author jkoogi
 * 1. 예외를 잡았지만, 처리할 수 없다면 다시 던져라
 *  - 예외에 버그가 있을경우 프로그래이 충돌시 까지 전달
 *  - 오류를 추적할 때 원인사슬(각 예외가 예외를 일으킨 예외와 연결된 리스트를 보여주는 스택 표시)이 자세할 수록 좋다.
 *  - 사슬이 끊기지 않도록.
 */
public class TransmissionParser {

	static Transmission parse(String rawMessage) {
		if(rawMessage != null
				&& rawMessage.length() != Transmission.MESSAGE_LENGTH
				) {
			throw new IllegalArgumentException(
					String.format("Exception %d, but got %d characters in '%s'", 
							Transmission.MESSAGE_LENGTH,rawMessage.length(),
							rawMessage));
		}
		
		String rawId = rawMessage.substring(0,Transmission.ID_LENGTH);
		String rawContent = rawMessage.substring(Transmission.ID_LENGTH);
		try {
			int id = Integer.parseInt(rawId);
			String content = rawContent.trim();
			return new Transmission(id, content);

		} catch (NumberFormatException e) {
			/* 대상소스 
			 *  - NumberFormatException 을 잡고 참조하지 않는 IllegalArgumentException을 처리
			 *  > 연결고리가 없으면 연결사슬도 없다.
			 *  */
//			throw new IllegalArgumentException(
//					String.format("Exception number, but got '%s' in '%s'", 
//							rawId, rawMessage));
			
			/* 실수소스 
			 *  - NumberFormatException이 throw에서 빠지면서 원인만 연결되고 예외 자체가 연결되지 않음
			 *  * catch 블록에서 예외를 던질때, 잡은 예외(e)를 전달한다.
			 **/
//			throw new IllegalArgumentException(e.getCause());
			
			/* 개선소스 
			 * - Throwable을 전달하는 생성자 추가 : Exception(String message, Throwable cause)
			 *     
			 * */
			throw new IllegalArgumentException(
					String.format("Exception number, but got '%s' in '%s'", 
							rawId, rawMessage), e);
		}
	}
	
	public static void main(String[] args) {
		parse("A2345");

		/* 대상소스 실행결과 */
//		Exception in thread "main" java.lang.IllegalArgumentException: Exception number, but got 'A234' in 'A2345'
//		at item05문제발생에대비하기.ex504원인사슬깨지않기.TransmissionParser.parse(TransmissionParser.java:41)
//		at item05문제발생에대비하기.ex504원인사슬깨지않기.TransmissionParser.main(TransmissionParser.java:48)

		/* 개선소스 실행결과 */
//		Exception in thread "main" java.lang.IllegalArgumentException: Exception number, but got 'A234' in 'A2345'
//		at item05문제발생에대비하기.ex504원인사슬깨지않기.TransmissionParser.parse(TransmissionParser.java:52)
//		at item05문제발생에대비하기.ex504원인사슬깨지않기.TransmissionParser.main(TransmissionParser.java:59)
//	Caused by: java.lang.NumberFormatException: For input string: "A234"
//		at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
//		at java.lang.Integer.parseInt(Integer.java:580)
//		at java.lang.Integer.parseInt(Integer.java:615)
//		at item05문제발생에대비하기.ex504원인사슬깨지않기.TransmissionParser.parse(TransmissionParser.java:32)
//		... 1 more

	}
}