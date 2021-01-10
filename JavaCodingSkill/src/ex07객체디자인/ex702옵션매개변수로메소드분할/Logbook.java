package ex07객체디자인.ex702옵션매개변수로메소드분할;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.naming.directory.ModificationItem;

/**
 * 7.2 옵션 매개변수로 메서드 분할
 * @author jkoogi
 * 1. readEntries() : 로그파일로부터 데이터를 읽어드린다.
 *  - 로그항목마다 날짜정보 : date 매개변수로 명시해 항목 선택
 *  - date 대신 null을 전달할 경우 전체항목 반환
 *  > null 처리용 서비스, 파라미터 처리 서비스 분리
 */
public class Logbook {
	static final Path CREW_LOG = Paths.get("/var/log/crew.log");

	/* 대상소스 */
//	List<String> readEntries(LocalDate date) throws IOException{
//		final List<String> entries = Files.readAllLines(CREW_LOG,
//				StandardCharsets.UTF_8);
//		if(date == null) {
//			return entries;
//		}
//		
//		List<String> result = new LinkedList<>();
//		for (String entry : entries) {
//			if(entry.startsWith(date.toString())) {
//				result.add(entry);
//			}
//		}
//		return result;
//	}

/* 대상소스 실행코드 
 * [null 사용 = 매개변수는 선택사항] -> 옵션 매개변수 구성도 두가지 이상의 작업을 수행하는것.
 * */
//{
//	List<String> conpleteLog = logbook.readEntries(null);
//	
//	final LocalDate moonLanding = LocalDate.of(1969, Month.JULY, 20);
//	List<String> moonLandingLog = logbook.readEntries(moonLanding);
//}
	
	/* 개선소스 */
	List<String> readEntries(LocalDate date) throws IOException{
		Objects.requireNonNull(date);
		
		List<String> result = new LinkedList<>();
		for (String entry : readAllEntries()) {
			if(entry.startsWith(date.toString())) {
				result.add(entry);
			}
		}
		return result;
	}
	List<String> readAllEntries() throws IOException {
		return Files.readAllLines(CREW_LOG, StandardCharsets.UTF_8);
	}
/* 개선소스 실행코드 
 * null 파라미터로 전체 로그 조회 > readAllEntries() 조회
 *  - null을 없앨수록 NPE 가능성을 줄일수 있다.
 * */
//{	
//	List<String> conpleteLog = logbook.readAllEntries();
//	
//	final LocalDate moonLanding = LocalDate.of(1969, Month.JULY, 20);
//	List<String> moonLandingLog = logbook.readEntries(moonLanding);
//}
}

