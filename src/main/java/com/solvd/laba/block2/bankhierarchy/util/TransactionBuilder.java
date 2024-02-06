package com.solvd.laba.block2.bankhierarchy.util;

import com.solvd.laba.block2.bankhierarchy.domain.*;

import java.time.LocalDate;

public class TransactionBuilder {
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

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setSendDate(LocalDate sendDate) {
        this.sendDate = sendDate;
    }

    public void setRecieveDate(LocalDate recieveDate) {
        this.recieveDate = recieveDate;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public void setPriority(TransactionPriority priority) {
        this.priority = priority;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setMedium(MediumType medium) {
        this.medium = medium;
    }

    public void setMediumNumber(String mediumNumber) {
        this.mediumNumber = mediumNumber;
    }

    public Transaction getResult() {
        return new Transaction(account, amount, type, sendDate,
                recieveDate, fee, status, priority, card, medium, mediumNumber);
    }
}
