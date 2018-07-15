package com.codefans.practicetask.scannerdisk;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class FileFilter implements FilenameFilter {

	private FileType fileType;
	private static List<String> appFileExtension = new ArrayList<String>();
	private static List<String> videoFileExtension = new ArrayList<String>();
	private static List<String> imageFileExtension = new ArrayList<String>();

	public FileFilter(FileType fileType) {
		this.fileType = fileType;
	}

	static {
		appFileExtension.add(".exe");
		appFileExtension.add(".apk");

		videoFileExtension.add(".rmvb");
		videoFileExtension.add(".avi");
		videoFileExtension.add(".flv");
		videoFileExtension.add(".mp4");
		videoFileExtension.add(".wmv");

		imageFileExtension.add(".jpg");
		imageFileExtension.add(".jpeg");
		imageFileExtension.add(".png");
		imageFileExtension.add(".gif");

	}

	public static boolean filter(String file, FileType fileType) {
		boolean flag = false;

		String extension = "";

		if (file.lastIndexOf(".") >= 0) {
			extension = file.substring(file.lastIndexOf("."));
			if (fileType.equals(FileType.App)) {
				if (appFileExtension.contains(extension)) {
					flag = true;
				}
			} else if (fileType.equals(FileType.Video)) {
				if (videoFileExtension.contains(extension)) {
					flag = true;
				}
			}
		}

		return flag;
	}

	@Override
	public boolean accept(File dir, String name) {
		boolean flag = false;

		String extension = name.substring(name.lastIndexOf("."));

		if (fileType.equals(FileType.App)) {
			if (this.appFileExtension.contains(extension)) {
				flag = true;
			}
		} else if (fileType.equals(FileType.Video)) {
			if (this.videoFileExtension.contains(extension)) {
				flag = true;
			}
		}

		return flag;
	}

}
