package main.java.fix;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class RansomwareFix {

	private static String DIR_PATH_BASE = "/share/CACHEDEV1_DATA/";
	private static String DIR_PATH_RESCUE = "/share/external/DEV3301_1/restore1/share/CACHEDEV1_DATA/";
	private static String EXTENTION = ".7z";
	private static String README_FILE = "!!!READ_ME.txt";
	private static String TYPE = "BOTH";
	private static final Long ZERO = 0L;
	
	
	public static void main(String[] args) {
		
		System.out.println("### RansomwareFix START ### ");
		
		if (args.length > 1) {
			
			System.out.println("param set : "+args.length);
			
			DIR_PATH_BASE = args[0];
			DIR_PATH_RESCUE = args[1];
			if (args.length >= 3) {
				TYPE = args[2];
			}
			if (args.length >= 4) {
				EXTENTION = args[3];
			}
		} else {
			System.out.println("param is default.");
		}
		
		System.out.println(" - DIR_PATH_BASE : "+DIR_PATH_BASE);
		System.out.println(" - DIR_PATH_RESCUE : "+DIR_PATH_RESCUE);
		System.out.println(" - TYPE : "+TYPE);
		System.out.println(" - EXTENTION : "+EXTENTION);
		System.out.println(" ------------------------------ ");
		
//		RunType[] arr = RunType.values();
//		for (RunType rt : arr) {
//			System.out.println("RunType - "+rt+"("+rt.getValue()+")");
//		}
		
		File baseDir = new File(DIR_PATH_BASE);
		if (baseDir.exists()) {
			if (TYPE.equals(RunType.BOTH.getValue()) || TYPE.equals(RunType.FIX.getValue() )) {
				showFilesInDirRecursive(baseDir.getPath());
			} else {
				System.out.println("! Except Fix");
			}
			if (TYPE.equals(RunType.BOTH.getValue()) || TYPE.equals(RunType.LIST.getValue())) {
				System.out.println(":: Ransomware File Cnt = "+findRansomwareList(baseDir.getPath()));
			} else {
				System.out.println("! Except List");
			}
		} else {
			System.out.println("* baseDir is null");
		}
		System.out.println("### RansomwareFix END ###");
	}
	
	// 디렉토리 탐색 - 파일이동
	private static void showFilesInDirRecursive(String path) {
		
		File dir = new File(path);
		File filse[] = dir.listFiles();
		
		if (filse == null) return;
		for (File file : filse) {
			if (file.isDirectory()) {
				
				// root depth에 [.]로 시작하는 숨김폴더 제외
				if (file.getPath().startsWith(DIR_PATH_BASE+".")) {
					System.out.println(" - [~] hidden dir : "+file);
					continue;
				} else {
					
					showFilesInDirRecursive(file.getPath());
					readmeDelete(file.getPath());
					emptyRescueDirRemove(file.getPath());
				}
				
			} else if (file.getName().endsWith(".7z")) {
				
				System.out.println("file : " + file);
				File rescueFile = getRescueFile(file); 
				
				if (rescueFile != null && rescueFile.exists()) {
					System.out.println(" - rescueFile exists : "+rescueFile);
					moveRescuFile(file, rescueFile);
				} else {
					System.out.println(" - rescueFile exists : NO");
				}
			}
		}
	}

	// 디렉토리 탐색 - 감염파일 조회
	private static long findRansomwareList(String path) {
		File dir = new File(path);
		File filse[] = dir.listFiles();
		
		if (filse == null) return ZERO;
		long fileCnt = ZERO;
		for (File file : filse) {
			if (file.isDirectory()) {
				fileCnt += findRansomwareList(file.getPath());
			} else if (file.getName().endsWith(".7z")) {
				System.out.println("[7z] "+file);
				fileCnt++;
			}
		}
		return fileCnt;
	}

	// 복구파일 이동 후 빈 복구디렉토리 삭제
	private static void emptyRescueDirRemove(String path) {
		
		File rescueDir = new File(path.replace(DIR_PATH_BASE, DIR_PATH_RESCUE));
		if (rescueDir.exists()) {
			
			File files[] = rescueDir.listFiles();
			
			if (files.length==0) {
				if (rescueDir.delete()) {
					System.out.println(" - remove rescue dir : "+rescueDir);
				} else {
					System.out.println(" - fail remove rescue dir : "+rescueDir);
				}
			}
		}
	}

	// 복구파일 이동
	private static void moveRescuFile(File file, File rescueFile) {
		
		Path rescuePath = Paths.get(rescueFile.getPath());
		Path newPath = Paths.get(file.getParent()+"/"+rescueFile.getName());
		
		try {
			Path path = Files.move(rescuePath, newPath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("path :" + path);
			
			if (path.toFile().exists()) {
			    System.out.println(" -> [*] move newFile : "+path.toFile());
			    file.delete();
			} else {
				System.out.println(" -> [!] move fail : "+file.getParent()+"/"+rescueFile.getName()+" | file.getParent() : "+file.getParent());
			}
		} catch (IOException e) {
			System.out.println(" -> [!] move fail : "+file.getParent()+"/"+rescueFile.getName()+" | file.getParent() : "+file.getParent());
			e.printStackTrace();
		}
	}

	// 복구파일 조회
	private static File getRescueFile(File file) {
		
		String fileName = file.getName().substring(0,file.getName().length()-EXTENTION.length());
		String parantPath = file.getParentFile().toString();
		String rescuePath = parantPath.replace(DIR_PATH_BASE, DIR_PATH_RESCUE);
		
		File rescueFile = new File(rescuePath+"/"+fileName);
		System.out.println(" - getRescueFile.file.org : "+file);
		System.out.println(" - getRescueFile.file.new : "+rescueFile);
		
		return rescueFile;
	}

	// 디렉토리에 7z파일이 없으면 ReadMe 파일 삭제 
	private static void readmeDelete(String path) {
		
		File checkDir = new File(path);
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".7z");
			}
		};
		
		File files[] = checkDir.listFiles(filter);
		File readmeFile = new File(path+"/"+README_FILE);
		if (files.length==0 && readmeFile.isFile()) {
			System.out.println(">> delete ReadMe : "+readmeFile);
			readmeFile.delete();
		}
	}
}

enum RunType {
	FIX("1"), LIST("2"), BOTH("3");
	
	private final String value;

	RunType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	
}