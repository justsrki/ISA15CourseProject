package beans.util;

import util.RandomStringGenerator;

import javax.ejb.Stateless;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
@Stateless
public class TokenGeneratorBean implements TokenGeneratorLocal {

    public static final int DEFAULT_LENGTH = 128;

    private RandomStringGenerator stringGenerator;

    public TokenGeneratorBean() {
        this(DEFAULT_LENGTH);
    }

    public TokenGeneratorBean(int length) {
        stringGenerator = new RandomStringGenerator(length);
    }

    @Override
    public synchronized String generateSessionToken() {
        return stringGenerator.nextString();
    }
}
