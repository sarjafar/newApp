package uz.parahat.newsApp.utils;

import java.util.UUID;

public class MyUtils {
	public static String generateNewToken() {
		return UUID.randomUUID().toString();
	}
}
