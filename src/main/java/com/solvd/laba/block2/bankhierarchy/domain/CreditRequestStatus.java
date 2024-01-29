package com.solvd.laba.block2.bankhierarchy.domain;

public class CreditRequestStatus {
    private Long id = null;
    private String name;

    public CreditRequestStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
