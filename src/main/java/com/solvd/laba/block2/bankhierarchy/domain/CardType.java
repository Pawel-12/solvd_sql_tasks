package com.solvd.laba.block2.bankhierarchy.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class CardType {
    @XmlAttribute(name = "id")
    private Long id = null;
    private String name;
    private Long limit;
    private Boolean multicurrency;
    private Float exchangeFeeRate;
    private Float creditFeeRate;

    public CardType(String name, Long limit, Boolean multicurrency, Float exchangeFeeRate, Float creditFeeRate) {
        this.name = name;
        this.limit = limit;
        this.multicurrency = multicurrency;
        this.exchangeFeeRate = exchangeFeeRate;
        this.creditFeeRate = creditFeeRate;
    }

    public CardType() {
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

    public Boolean isMulticurrency() {
        return multicurrency;
    }

    public void setMulticurrency(Boolean multicurrency) {
        this.multicurrency = multicurrency;
    }

    public Float getExchangeFeeRate() {
        return exchangeFeeRate;
    }

    public void setExchangeFeeRate(Float exchangeFeeRate) {
        this.exchangeFeeRate = exchangeFeeRate;
    }

    public Float getCreditFeeRate() {
        return creditFeeRate;
    }

    public void setCreditFeeRate(Float creditFeeRate) {
        this.creditFeeRate = creditFeeRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CardType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", limit=" + limit +
                ", multicurrency=" + multicurrency +
                ", exchangeFeeRate=" + exchangeFeeRate +
                ", creditFeeRate=" + creditFeeRate +
                '}';
    }
}
