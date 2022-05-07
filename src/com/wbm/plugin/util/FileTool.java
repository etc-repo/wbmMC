package com.wbm.plugin.util;

import java.io.File;
import java.io.IOException;

public class FileTool {
	public static boolean isValidFileName(String fileName) {
		try {
			new File(fileName).createNewFile();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
