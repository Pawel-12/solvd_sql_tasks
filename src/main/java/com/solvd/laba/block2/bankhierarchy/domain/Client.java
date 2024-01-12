package com.solvd.laba.block2.bankhierarchy.domain;

public class Client {
    private String name;
    private String surname;
    private String phone;
    private int monthlyIncome;
    private int totalDebt;

    public Client(String name, String surname, String phone, int monthlyIncome, int totalDebt){
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.monthlyIncome = monthlyIncome;
        this.totalDebt = totalDebt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public int getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(int totalDebt) {
        this.totalDebt = totalDebt;
    }
}
