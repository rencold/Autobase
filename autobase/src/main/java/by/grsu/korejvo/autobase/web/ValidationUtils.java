package by.grsu.korejvo.autobase.web;

public abstract class ValidationUtils {

	private ValidationUtils() {
	}

	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean isBoolean(String str) {
		if (str == null) {
			return false;
		}
		try {
			Boolean.parseBoolean(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}