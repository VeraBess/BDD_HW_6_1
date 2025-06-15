package ru.netology.bdd.data;


import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    } //метод возвращающий код верификации

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    } //метод возвращающий пользователя

    public static CardInfo getFirstCardInfo() { //метод возвращающий первую карту
        return new CardInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static CardInfo getSecondCardInfo() { //метод возвращающий вторую карту
        return new CardInfo("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    public static int generateValidAmount(int balance) {
        return Math.abs(balance) / 10;
    } //метод считающий сумму перевода валидная сумма

    public static int generateInvalidAmount(int balance) {
        return Math.abs(balance) + 1;
    }  //метод считающий сумму перевода невалидная сумма

    @Value
    public static class VerificationCode { //класс описание объекта с кодом верификации
        String code;
    }

    @Value
    public static class CardInfo { //класс описание карты
        String cartNumber;
        String testId;
    }

    @Value
    public static class AuthInfo { //класс описание пользователя
        String login;
        String password;
    }
}
