package com.wbm.plugin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoTool {

	public static byte[] hash(byte[] data) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			return digest.digest(data);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String hashToHex(byte[] bytes) {
		return bytesToHex(hash(bytes));
	}

	public static String bytesToHex(byte[] bytes) {
		String hex = "";
		for (byte b : bytes) {
			hex += String.format("%02x", b);
		}
		return hex;

	}

}
