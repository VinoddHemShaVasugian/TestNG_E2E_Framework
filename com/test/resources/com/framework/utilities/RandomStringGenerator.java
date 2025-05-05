/**
 * Utility class for generating random strings.
 * This class provides a method to generate a random string of a specified length
 * using a combination of uppercase letters, lowercase letters, and digits.
 * 
 * The class uses a `SecureRandom` instance to ensure the randomness of the generated strings.
 */
package com.framework.utilities;

import java.security.SecureRandom;

public class RandomStringGenerator {
	// Characters used for generating random strings
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final SecureRandom RANDOM = new SecureRandom();

	/**
	 * Generates a random string of the specified length.
	 * 
	 * @param length The length of the random string to generate.
	 * @return A randomly generated string of the specified length.
	 */
	public static synchronized String generateRandomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int randomIndex = RANDOM.nextInt(CHARACTERS.length());
			sb.append(CHARACTERS.charAt(randomIndex));
		}
		return sb.toString();
	}
}
