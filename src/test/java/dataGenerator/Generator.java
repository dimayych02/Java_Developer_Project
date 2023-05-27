package dataGenerator;

import com.mifmif.common.regex.Generex;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;


import java.util.Random;

public class Generator {

    @Step("генерация почты")
    public static String generateEmail() {
        return new Generex("[A-Za-z0-9]{1,10}+\\@[A-Za-z0-9]{1,10}+\\.[a-zA-Z]{2,5}").random();
    }

    @Step("генерация номера телефона")
    public static String generatePhoneNumber() {
        return new Generex("\\d{10}").random();
    }

    @Step("Генерация строки")
    public static String generateString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    @Step("Генерация положительного числа")
    public static Integer generateInteger() {
        return new Random().nextInt(100) + 1;
    }

    @Step("Генерация пароля")
    public static String generatePassword(){
        return new Generex("^[A-Z]{6,10}+[a-z]{5,8}+\\d+[!@#\\$%\\^&\\*]{1,4}").random();
    }

}
