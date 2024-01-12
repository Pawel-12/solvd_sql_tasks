package com.solvd.laba.block2.bankhierarchy.domain;

public class CreditRequest {
    private Account account;
    private CreditRequestStatus status;
    private Credit credit;

    public CreditRequest(Account account, CreditRequestStatus status, Credit credit) {
        this.account = account;
        this.status = status;
        this.credit = credit;
    }

    public CreditRequestStatus getStatus() {
        return status;
    }

    public void setStatus(CreditRequestStatus status) {
        this.status = status;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
