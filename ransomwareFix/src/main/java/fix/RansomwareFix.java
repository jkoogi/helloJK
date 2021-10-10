package main.java.fix;

import java.io.File;
import java.io.FilenameFilter;

public class RansomwareFix {

	private static String DIR_PATH_BASE = "/share/CACHEDEV1_DATA";
	private static String DIR_PATH_RESCUE = "/share/rescue/restore1/share/CACHEDEV1_DATA";
	private static String EXTENTION = ".7z";
	private static String README_FILE = "!!!READ_ME.txt";
	
	public static void main(String[] args) {
		
		System.out.println("### RansomwareFix START ###");
		
		if(args.length > 2 ) {
			
			System.out.println("param set...");
			
			DIR_PATH_BASE = args[0];
			DIR_PATH_RESCUE = args[1];
			if(args.length > 3) {
				EXTENTION = args[2];
			}
		}
		
		System.out.println(" - DIR_PATH_BASE : "+DIR_PATH_BASE);
		System.out.println(" - DIR_PATH_RESCUE : "+DIR_PATH_RESCUE);
		System.out.println(" - EXTENTION : "+EXTENTION);
		
		File baseDir = new File(DIR_PATH_BASE);
		showFilesInDirRecursive(baseDir.getPath());
		
		System.out.println("### RansomwareFix END ###");
	}

	// 디렉토리 탐색
	private static void showFilesInDirRecursive(String path) {
		
		File dir = new File(path);
		File filse[] = dir.listFiles();
		
		if(filse == null) return;
		for (File file : filse) {
			if(file.isDirectory()) {
				showFilesInDirRecursive(file.getPath());
				isReadmeDelete(file.getPath());
			}else if (file.getName().endsWith(".7z")) {
				System.out.println("file : " + file);
				File rescueFile = getRescueFile(file); 
				if(rescueFile != null && rescueFile.exists()) {
					moveRescuFile(file, rescueFile);
				}
			}
		}
	}


	// 복구파일 이동
	private static void moveRescuFile(File file, File rescueFile) {
		
		File newFile = new File(file.getParent()+"/"+rescueFile.getName());
		boolean success = rescueFile.renameTo(newFile);
		if (success) {
		    System.out.println("move newFile : "+newFile);
		    file.delete();
		}
	}

	// 복구파일 조회
	private static File getRescueFile(File file) {
		
		String fileName = file.getName().substring(0,file.getName().length()-EXTENTION.length());
		String parantPath = file.getParentFile().toString();
		String rescuePath = parantPath.replace(DIR_PATH_BASE, DIR_PATH_RESCUE);
		
		File rescueFile = new File(rescuePath+"/"+fileName);
		return rescueFile;
	}

	// 디렉토리에 7z파일이 없으면 ReadMe 파일 삭제 
	private static void isReadmeDelete(String path) {
		
		File checkDir = new File(path);
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".7z");
			}
		};
		
		File files[] = checkDir.listFiles(filter);
		File readmeFile = new File(path+"/"+README_FILE);
		if(files.length==0 && readmeFile.isFile()) {
			System.out.println(">> delete ReadMe : "+readmeFile);
			readmeFile.delete();
		}
	}
}