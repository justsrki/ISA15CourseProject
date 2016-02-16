package beans.util;

import util.RandomStringGenerator;

import javax.ejb.Stateless;

/**
 * @author - Srđan Milaković
 */
@Stateless
public class PasswordGeneratorBean implements PasswordGeneratorLocal {

    public static final int DEFAULT_LENGTH = 12;

    private RandomStringGenerator stringGenerator;

    public PasswordGeneratorBean() {
        this(DEFAULT_LENGTH);
    }

    public PasswordGeneratorBean(int length) {
        stringGenerator = new RandomStringGenerator(length);
    }

    @Override
    public synchronized String generatePassword() {
        return stringGenerator.nextString();
    }
}
