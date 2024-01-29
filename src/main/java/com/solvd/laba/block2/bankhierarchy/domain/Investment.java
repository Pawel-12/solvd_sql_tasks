package com.solvd.laba.block2.bankhierarchy.domain;

import java.time.LocalDate;

public class Investment {
    private Long id = null;
    private Long amount;
    private float percentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private Account account;

    public Investment(Long amount, float percentage, LocalDate startDate, LocalDate endDate, Account account){
        this.amount = amount;
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
