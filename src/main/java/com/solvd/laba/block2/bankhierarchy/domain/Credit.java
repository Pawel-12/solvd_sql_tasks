package com.solvd.laba.block2.bankhierarchy.domain;

import java.time.LocalDate;

public class Credit {
    private Long id = null;
    private Long amount;
    private float percentage;
    private Long paidBackAmount;
    private LocalDate dueDate;
    private Account account;

    public Credit(Long amount, float percentage, Long paidBackAmount, LocalDate dueDate, Account account) {
        this.amount = amount;
        this.percentage = percentage;
        this.paidBackAmount = paidBackAmount;
        this.dueDate = dueDate;
        this.account = account;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public Long getPaidBackAmount() {
        return paidBackAmount;
    }

    public void setPaidBackAmount(Long paidBackAmount) {
        this.paidBackAmount = paidBackAmount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
