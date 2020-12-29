package ex04올바르게명명하기.ex404축약지양;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 4.4 축약 쓰지 않기
 * @author jkoogi
 * 1. 축약은 작성자만 안다. 쓰지말자
 *  - DIR > LOG_FOLDER
 *  - CSV(comma-separated values) > STATISTICSJ2SV
 *  - GLOB > FILE_FILTER
 *  - dirStr > directoryStream > logs(대체)
 *  - bufw > bufferedWriter > writer(목적 대표단어) 
 *  - lFile > log(대체)
 *  - csvLn > csvLine(의미검토)
 */
public class Logbook {

	/* 대상소스 */
//	static final Path DIR = Paths.get("/var/log");
//	static final Path CSV = DIR.resolve("stats.csv");
//	static final String GLOB = "*.log";
//	
//	void createStats() throws IOException {
//		try(DirectoryStream<Path> dirStr = 
//				Files.newDirectoryStream(DIR,GLOB);
//			BufferedWriter bufw = Files.newBufferedWriter(CSV))	{
//			for (Path lFile : dirStr) {
//				String csvLn = String.format("%s,%d,%s",
//						lFile,
//						Files.size(lFile),
//						Files.getLastModifiedTime(lFile));
//				bufw.write(csvLn);
//				bufw.newLine();
//			}
//		}
//	}
	
	/* 개선소스 */
	static final Path LOG_FOLDER = Paths.get("/var/log");
	static final Path STATISTICS_CSV = LOG_FOLDER.resolve("stats.csv");
	static final String FILE_FILTER = "*.log";
	
	void createStats() throws IOException {
		try(DirectoryStream<Path> logs = 
				Files.newDirectoryStream(LOG_FOLDER,FILE_FILTER);
			BufferedWriter writer = Files.newBufferedWriter(STATISTICS_CSV))	{
			for (Path log : logs) {
				String csvLine = String.format("%s,%d,%s",
						log,
						Files.size(log),
						Files.getLastModifiedTime(log));
				writer.write(csvLine);
				writer.newLine();
			}
		}
	}	
}
