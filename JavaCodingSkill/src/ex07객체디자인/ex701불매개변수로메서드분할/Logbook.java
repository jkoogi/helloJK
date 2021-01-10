package ex07객체디자인.ex701불매개변수로메서드분할;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Collections;

/**
 * 7.1 불 매개변수로 메서드 분할
 * @author jkoogi
 * - 디자인의 필요성 : 집은 기능만 하기보다 아름답고 편리하게 지어야 한다. 
 * 1. 메서드는 하나의 작업메만 특화
 *  - 불 메서드 매개변수는 메스다가 적어도 두가지 작업을 수행함.
 * 2. log() 메서드를 boolean 매개변수로 공개 메시지와 비공개 메시지로 기능함
 *  - 호출하는 편에서 boolean 매개변수의 역할을 이해하지 못하거나 잘못 사용할 수 있음
 *  - 기장, 승무원 별 로그 기능은 서로 연관되어 각각의 변경이 상호 영향을 줄 수 있음
 *  
 */
public class Logbook {
	private static final Path CAPTAIN_LOG = Paths.get("/var/log/captain.log");
	private static final Path CREW_LOG = Paths.get("/var/log/crew.log");

	/* 대상소스 */
	void log(String message, boolean classified) throws IOException {
		if(classified) {
			writeMessage(message,CAPTAIN_LOG);
		}else {
			writeMessage(message,CREW_LOG);
		}
	}
	/* 대상소스 실행코드 */
	//logbook.log("Aliens sighted!", true);
	//logbook.log("Toilet broken.", false);
	
	/* 개선소스 
	 *  - 입력매개변수에 boolean 이 쓰인 메서드 : 여러개로 분리
	 *  > boolean 매개변수 제거, 각 제어흐름경로별 메서드 추가 (메서드명으로 가독성 확보)
	 * */  
	void writeToCaptainLog(String message) throws IOException {
		writeMessage(message, CAPTAIN_LOG);
	}
	void writeToCrewLog(String message) throws IOException {
		writeMessage(message, CAPTAIN_LOG);
	}

	/* 개선소스 실행코드 */
	//logbook.writeToCaptainLog("Aliens sighted!");
	//logbook.writeToCrewLog("Toilet broken.");
	
	private void writeMessage(String message, Path location) throws IOException {
		String entry = LocalDate.now()+" "+ message;
		Files.write(location, Collections.singleton(entry), 
				StandardCharsets.UTF_8, StandardOpenOption.APPEND);
	}

}

/*
 * > 훌륭한 디자인은 결코 쉽지 않다
 * 무엇이 훌륭한 디자인인지 처음부터 제대로 이해하는 것은 불가능에 가깝습니다. 
 * 달리 보면 객체 지향 디자인에 능숙할수록 부르는 곳이 많을 테니 희소식인 것은 분명합니다. 
 * 하지만 코드에서 잘못된 줄을 찾는 것보다 잘못된 디자인과 옳은 디자인을 구분하는 것이 더어렵습니다. 
 * 훌륭한 디자인이란 별로 명확하지 않고 직관과 “감정"까지 요구합니다. 
 * 현실적으로 이러한 직관을 쌓는 유일한 방법은 코딩을 많이 해보고 다양한 디자인을 시도해보면서 무엇이 실패하고 무엇이 옳게 느껴지는지 깨닫는 것뿐입니다
 */