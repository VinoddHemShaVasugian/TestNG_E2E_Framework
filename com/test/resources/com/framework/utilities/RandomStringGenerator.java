package com.framework.utilities;

import java.security.SecureRandom;

/**
 * Utility class for generating random strings and numbers. Provides methods to
 * generate alphanumeric strings, alphabetic strings, numeric strings, and
 * auto-generated names with specific formatting.
 */
public class RandomStringGenerator {
	// Characters used for generating random alphanumeric strings
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final String NUMERIC = "0123456789";
	private static final SecureRandom RANDOM = new SecureRandom();

	/**
	 * Generates a random alphanumeric string of the specified length.
	 * 
	 * @param length The length of the string to generate.
	 * @return A randomly generated alphanumeric string.
	 */
	public static synchronized String generateRandomStringAlphaNumeric(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int randomIndex = RANDOM.nextInt(CHARACTERS.length());
			sb.append(CHARACTERS.charAt(randomIndex));
		}
		return sb.toString();
	}

	/**
	 * Generates a random alphabetic string of the specified length.
	 * 
	 * @param length The length of the string to generate.
	 * @return A randomly generated alphabetic string.
	 */
	public static synchronized String generateRandomStringAlphabet(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int randomIndex = RANDOM.nextInt(ALPHABET.length());
			sb.append(ALPHABET.charAt(randomIndex));
		}
		return sb.toString();
	}

	/**
	 * Generates a random numeric string of the specified length.
	 * 
	 * @param length The length of the string to generate.
	 * @return A randomly generated numeric string.
	 */
	public static synchronized String generateRandomNumber(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int randomIndex = RANDOM.nextInt(NUMERIC.length());
			sb.append(NUMERIC.charAt(randomIndex));
		}
		return sb.toString();
	}

	/**
	 * Automatically generates a name with specific formatting. The name is a random
	 * alphabetic string of 5 characters, with the first character capitalized and
	 * all occurrences of 'f' or 'F' replaced with 'z' or 'Z'.
	 * 
	 * @return A formatted, auto-generated name.
	 */
	public static synchronized String autoGenerateName() {
		return firstCharUppercase(generateRandomStringAlphabet(5)).replaceAll("f", "z").replaceAll("F", "Z");
	}

	/**
	 * Capitalizes the first character of the given string.
	 * 
	 * @param name The string to capitalize.
	 * @return The string with the first character capitalized.
	 */
	private static String firstCharUppercase(String name) {
		return Character.toString(name.charAt(0)).toUpperCase() + name.substring(1);
	}
}
