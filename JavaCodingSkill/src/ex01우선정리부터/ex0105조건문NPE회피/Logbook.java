package ex01우선정리부터.ex0105조건문NPE회피;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Collections;

/**
 * 1.5 조건문에서 NullPointerException 피하기
 * @author jkoogi
 * 1. null 우선체크, isEmpty 사용
 * 2. 매개변수 검증 - 파라미터 순서에 따라 체크(체크누락 예방)
 * 
 * * 매개변수 검사는 public, protected, default 접근자의 메서드를 대상으로 구현
 *  - private는 호출부에서 통제 가능
 */
public class Logbook {

	// 1. null 체크순서 확인
	// 2. 매개변수 검증 필요 - 파라미터 순서에 따라 체크(체크누락 예방)
	void writeMessage(String message, Path location) throws IOException, IllegalAccessException{

		/* 대상소스 */
//		if(Files.isDirectory(location)) {
//			throw new IllegalAccessException("The path is invalid!");
//		}
//		if(message.trim().equals("") || message == null) {
//			throw new IllegalAccessException("The message is invalid!");
//		}

		/* 개선소스 */
		// 1. null 체크 우선, isEmpty 사용
		if(message == null || message.trim().isEmpty()) {
			throw new IllegalAccessException("The message is invalid!");
		}
		// 2. 매개변수 순서에 맞게 파라미터 검증 - 체크누락 방지
		if(location == null || Files.isDirectory(location)) {
			throw new IllegalAccessException("The path is invalid!");
		}
		
		String entry = LocalDate.now() + ":" +message;
		Files.write(location, Collections.singleton(entry),	
				StandardCharsets.UTF_8, StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);
	}
}
