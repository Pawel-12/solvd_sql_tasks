package com.solvd.laba.block2.bankhierarchy.domain;

public class CardType {
    private String name;
    private Long limit;
    private boolean multicurrency;
    private float exchangeFeeRate;
    private float creditFeeRate;

    public CardType(String name, Long limit, boolean multicurrency, float exchangeFeeRate, float creditFeeRate){
        this.name = name;
        this.limit = limit;
        this.multicurrency = multicurrency;
        this.exchangeFeeRate = exchangeFeeRate;
        this.creditFeeRate = creditFeeRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public boolean isMulticurrency() {
        return multicurrency;
    }

    public void setMulticurrency(boolean multicurrency) {
        this.multicurrency = multicurrency;
    }

    public float getExchangeFeeRate() {
        return exchangeFeeRate;
    }

    public void setExchangeFeeRate(float exchangeFeeRate) {
        this.exchangeFeeRate = exchangeFeeRate;
    }

    public float getCreditFeeRate() {
        return creditFeeRate;
    }

    public void setCreditFeeRate(float creditFeeRate) {
        this.creditFeeRate = creditFeeRate;
    }
}
