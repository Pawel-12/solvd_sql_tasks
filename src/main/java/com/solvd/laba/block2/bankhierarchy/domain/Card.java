package com.solvd.laba.block2.bankhierarchy.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import parsing.jaxb.LocalDateAdapter;
import parsing.json.LocalDateDeserialize;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Card {
    @XmlAttribute(name = "id")
    private Long id = null;
    private String cardNumber;
    private Account account;
    @XmlElement(name = "cardtype")
    private CardType cardType;
    private Boolean isBlocked;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
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

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", account=" + account +
                ", cardType=" + cardType +
                ", isBlocked=" + isBlocked +
                ", validUntil=" + validUntil +
                '}';
    }
}
