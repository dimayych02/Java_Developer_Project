package dataGenerator;

import com.mifmif.common.regex.Generex;
import org.apache.commons.lang3.RandomStringUtils;


import java.util.Random;

public class Generator {

    public static String generateEmail() {
        return new Generex("[A-Za-z0-9]{1,10}+\\@[A-Za-z0-9]{1,10}+\\.[a-zA-Z]{2,5}").random();
    }

    public static String generatePhoneNumber() {
        return new Generex("\\d{10}").random();
    }

    public static String generateString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public static Integer generateInteger() {
        return new Random().nextInt(100) + 1;
    }

    public static String generatePassword(){
        return new Generex("^[A-Z]{6,10}+[a-z]{5,8}+\\d+[!@#\\$%\\^&\\*]{1,4}").random();
    }


}
