package ex05문제발생에대비하기.ex507항상자원닫기;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 5.7 항상 자원 닫기
 * @author jkoogi
 * 1. 프로그램이 사용하는 자원을 공유하여 사용 : 디스크, DB, 네트워크, CPU 스레드, RAM 등
 *  - 프로그램이 자원을 해제하지 못하고 점유하면 시스템이 망가짐
 *
 */
public class logbook {

	static final Path LOG_FOLDER = Paths.get("/var/log");
	static final String FILE_FILTER = "*.log";
	
	List<Path> getLogs() throws IOException {
		List<Path> result = new ArrayList<>();

		/* 대상소스 */		
//		// 자원사용
//		DirectoryStream<Path> directoryStream = 
//				Files.newDirectoryStream(LOG_FOLDER, FILE_FILTER);
//		
//		for (Path logFile : directoryStream) {
//			result.add(logFile);
//		}
//		
//		//자원해제 - 해제 전 예외 발생시 프로그램 종료시까지 해제불가 :자원누출(resource leak)
//		directoryStream.close();

		/* 개선소스 
		 * 1. try-with-resources 구문을 이용한 자원관리
		 *  - AutoCloseable 인터페이스를 구현한 클래스에서 자동 자원관리
		 *  > 소괄호에 자원연결 - try 블럭에서 사용 뒤 자동 close() 처리
		 * */
		try(DirectoryStream<Path> directoryStream = 
				Files.newDirectoryStream(LOG_FOLDER, FILE_FILTER)) {
			
			for (Path logFile : directoryStream) {
				result.add(logFile);
			}
		}
		
		/* 컴파일러 처리 
		 *  - try-with-resources 를 이용해 자원해제는 컴파일러가 하도록 하자.
		 * */
		DirectoryStream<Path> directoryStream = 
				Files.newDirectoryStream(LOG_FOLDER, FILE_FILTER);

		try {
			for (Path logFile : directoryStream) {
				result.add(logFile);
			} 
		} finally {
			if(directoryStream != null) {
				directoryStream.close();
			}
		}
		
		return result;
	}
}
