package com.solvd.laba.block2.bankhierarchy.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Account {
    @XmlAttribute(name = "id")
    private Long id = null;
    private Client client;
    private Long balance;
    private String currency;

    public Account(Client client, Long balance, String currency) {
        this.client = client;
        this.balance = balance;
        this.currency = currency;
    }

    public Account() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", client=" + client +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }
}
