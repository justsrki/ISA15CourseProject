package util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author - Srđan Milaković
 */
public class RandomStringGenerator {
    public static final int DEFAULT_LENGTH = 128;
    private static final int BITS_PER_LETTER = 5;
    private static final int CHARACTER_SET_SIZE = 32;

    private SecureRandom random;
    private int length;

    public RandomStringGenerator() {
        this(DEFAULT_LENGTH);
    }

    public RandomStringGenerator(int length) {
        random = new SecureRandom();
        this.length = length;
    }

    public String nextString() {
        return new BigInteger(length * BITS_PER_LETTER, random).toString(CHARACTER_SET_SIZE);
    }

    public int getLength() {
        return length;
    }
}
