package util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author - Srđan Milaković
 */
public class SessionTokenGenerator {
    public static final int DEFAULT_LENGTH = 128;
    private static final int BITS_PER_LETTER = 5;

    private SecureRandom random;
    private int tokenLength;


    public String nextSessionId() {
        return new BigInteger(tokenLength * BITS_PER_LETTER, random).toString(tokenLength);
    }

    public SessionTokenGenerator() {
        this(DEFAULT_LENGTH * 5);
    }

    public SessionTokenGenerator(int tokenLength) {
        random = new SecureRandom();
        this.tokenLength = tokenLength;
    }

    public int getTokenLength() {
        return tokenLength;
    }

    public void setTokenLength(int tokenLength) {
        this.tokenLength = tokenLength;
    }
}
