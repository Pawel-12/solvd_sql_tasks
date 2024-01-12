package com.solvd.laba.block2.bankhierarchy.domain;

import java.time.LocalDate;

public class Transaction {
    private Account account;
    private Long amount;
    private TransactionType type;
    private LocalDate sendDate;
    private LocalDate recieveDate;
    private Integer fee;
    private TransactionStatus status;
    private TransactionPriority priority;
    private Card card;
    private MediumType medium;
    private String mediumNumber;

    public Transaction(Account account, Long amount, TransactionType type, LocalDate sendDate,
                       LocalDate recieveDate, Integer fee, TransactionStatus status, TransactionPriority priority, Card card, MediumType medium, String mediumNumber) {
        this.account = account;
        this.amount = amount;
        this.type = type;
        this.sendDate = sendDate;
        this.recieveDate = recieveDate;
        this.fee = fee;
        this.status = status;
        this.priority = priority;
        this.card = card;
        this.medium = medium;
        this.mediumNumber = mediumNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDate getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public LocalDate getRecieveDate() {
        return recieveDate;
    }

    public void setRecieveDate(LocalDate recieveDate) {
        this.recieveDate = recieveDate;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public TransactionPriority getPriority() {
        return priority;
    }

    public void setPriority(TransactionPriority priority) {
        this.priority = priority;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public MediumType getMedium() {
        return medium;
    }

    public void setMedium(MediumType medium) {
        this.medium = medium;
    }

    public String getMediumNumber() {
        return mediumNumber;
    }

    public void setMediumNumber(String mediumNumber) {
        this.mediumNumber = mediumNumber;
    }
}
