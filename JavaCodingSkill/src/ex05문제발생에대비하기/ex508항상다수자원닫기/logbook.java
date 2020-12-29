package ex05문제발생에대비하기.ex508항상다수자원닫기;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 5.8 항상 다수 자원 닫기
 * @author jkoogi
 * 1. 다수자원 사용시 자원간 사용이 종속될 경우 자원해제가 복잡
 *  - writer 생성에 실패시 메서드가 예외와 함께 종료되어 directoryStream이 닫히지 않음
 *  - directoryStream 닫기 실패시 writer 닫기 오류
 */
public class logbook {

	static final Path LOG_FOLDER = Paths.get("/var/log");
	static final Path STATISTIC_CSV = LOG_FOLDER.resolve("stats.csv");
	static final String FILE_FILTER = "*.log";
	
	void createStatistics() throws IOException {

		/* 대상소스 */
//		DirectoryStream<Path> directoryStream = 
//				Files.newDirectoryStream(LOG_FOLDER, FILE_FILTER);
//		BufferedWriter writer = 
//				Files.newBufferedWriter(STATISTIC_CSV);
//		try {
//			for (Path logFile : directoryStream) {
//				final String csvLine = String.format("", 
//						logFile,
//						Files.size(logFile),
//						Files.getLastModifiedTime(logFile));
//				writer.write(csvLine);
//				writer.newLine();
//			}
//		} finally {
//			directoryStream.close();
//			writer.close();
//		}

		/* 개선소스 
		 *  - try-with-resource 블록은 여러자원 처리 가능 : 세미콜록으로 구분
		 *  try(open resource1; open resource2;){자원사용}
		 * */
		try (
				DirectoryStream<Path> directoryStream = 
				Files.newDirectoryStream(LOG_FOLDER, FILE_FILTER);
				BufferedWriter writer = 
				Files.newBufferedWriter(STATISTIC_CSV)
			){
			
			for (Path logFile : directoryStream) {
				final String csvLine = String.format("", 
						logFile,
						Files.size(logFile),
						Files.getLastModifiedTime(logFile));
				writer.write(csvLine);
				writer.newLine();
			}
		}

		/* 컴파일 처리 
		 *  - 각 자원을 확장해 여러 중첩 블록 생성, 가장 안쪽 블록에서 열린 자원 사용
		 * */
		//resource1 열기
		DirectoryStream<Path> directoryStream = 
				Files.newDirectoryStream(LOG_FOLDER, FILE_FILTER);
		try {
			//resource2 열기
			BufferedWriter writer = 
					Files.newBufferedWriter(STATISTIC_CSV);
			try {
				//resource1, resource2 사용
				for (Path logFile : directoryStream) {
					final String csvLine = String.format("", 
							logFile,
							Files.size(logFile),
							Files.getLastModifiedTime(logFile));
					writer.write(csvLine);
					writer.newLine();
				}
			} finally {
				writer.close();
			}
			
		}finally {
			directoryStream.close();
		}
	}
}
