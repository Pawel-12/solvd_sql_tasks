package com.solvd.laba.block2.bankhierarchy.domain;

public class Account {
    private Long id = null;
    private Client client;
    private Long balance;
    private String currency;

    public Account(Client client, Long balance, String currency) {
        this.client = client;
        this.balance = balance;
        this.currency = currency;
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
}
