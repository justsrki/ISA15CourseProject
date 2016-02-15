package util;

/**
 * @author - Srđan Milaković
 */
public class PasswordGenerator {
    public static final int DEFAULT_LENGTH = 128;

    private RandomStringGenerator generator;

    public String generatePassword() {
        return generator.nextString();
    }

    public PasswordGenerator() {
        this(DEFAULT_LENGTH);
    }

    public PasswordGenerator(int passwordLength) {
        generator = new RandomStringGenerator(passwordLength);
    }

    public int getPasswordLength() {
        return generator.getLength();
    }
}
