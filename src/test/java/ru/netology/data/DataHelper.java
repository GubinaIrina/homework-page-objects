package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }


    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor() {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        String number;
    }

    public static CardInfo getFirstCard() {
        return new CardInfo("5559 0000 0000 0001");
    }


    public static CardInfo getSecondCard() {
        return new CardInfo("5559 0000 0000 0002");
    }


    public static CardInfo getInvalidCard() {
        return new CardInfo("5559 0000 0000 0000");
    }
}
