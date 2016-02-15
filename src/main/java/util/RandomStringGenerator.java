package util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author - Srđan Milaković
 */
public class RandomStringGenerator {
    public static final int DEFAULT_LENGTH = 128;
    private static final int BITS_PER_LETTER = 5;

    private SecureRandom random;
    private int length;

    public String nextString() {
        return new BigInteger(length * BITS_PER_LETTER, random).toString(length);
    }

    public RandomStringGenerator() {
        this(DEFAULT_LENGTH * 5);
    }

    public RandomStringGenerator(int length) {
        random = new SecureRandom();
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
