package com.solvd.laba.block2.bankhierarchy.domain;

public class TransactionPriority {
    private String name;
    private Integer priority;

    public TransactionPriority(String name, Integer priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
