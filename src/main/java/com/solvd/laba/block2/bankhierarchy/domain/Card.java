package com.solvd.laba.block2.bankhierarchy.domain;

import java.time.LocalDate;

public class Card {
    private String cardNumber;
    private Account account;
    private CardType cardType;
    private boolean isBlocked;
    private LocalDate validUntil;

    public Card(String cardNumber, Account account, CardType cardType, boolean isBlocked, LocalDate validUntil){
        this.cardNumber = cardNumber;
        this.account = account;
        this.cardType = cardType;
        this.isBlocked = isBlocked;
        this.validUntil = validUntil;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
