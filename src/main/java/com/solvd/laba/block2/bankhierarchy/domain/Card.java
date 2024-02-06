package com.solvd.laba.block2.bankhierarchy.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import parsing.json.LocalDateDeserialize;

import java.time.LocalDate;

public class Card {
    private Long id = null;
    private String cardNumber;
    private Account account;
    private CardType cardType;
    private Boolean isBlocked;

    @JsonDeserialize(using = LocalDateDeserialize.class)
    private LocalDate validUntil;

    public Card(String cardNumber, Account account, CardType cardType, Boolean isBlocked, LocalDate validUntil) {
        this.cardNumber = cardNumber;
        this.account = account;
        this.cardType = cardType;
        this.isBlocked = isBlocked;
        this.validUntil = validUntil;
    }

    public Card() {
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

    public Boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
