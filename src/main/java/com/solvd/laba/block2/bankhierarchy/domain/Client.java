package com.solvd.laba.block2.bankhierarchy.domain;

public class Client {
    private Long id = null;
    private String name;
    private String surname;
    private String phone;
    private Integer monthlyIncome;
    private Integer totalDebt;

    public Client(String name, String surname, String phone, Integer monthlyIncome, Integer totalDebt) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.monthlyIncome = monthlyIncome;
        this.totalDebt = totalDebt;
    }

    public Client() {
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

    public Integer getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Integer monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Integer getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(Integer totalDebt) {
        this.totalDebt = totalDebt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", monthlyIncome=" + monthlyIncome +
                ", totalDebt=" + totalDebt +
                '}';
    }
}
