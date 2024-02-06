package com.solvd.laba.block2.bankhierarchy.util;

import com.solvd.laba.block2.bankhierarchy.domain.*;

import java.time.LocalDate;

public class TransactionDirector {
    public TransactionDirector() {
    }

    public Transaction buildNewTransferTransaction(Account account, Account account2, Long amount, TransactionType type, Integer fee) {
        TransactionBuilder builder = new TransactionBuilder();

        builder.setAccount(account);
        builder.setAmount(amount);
        builder.setType(type);
        builder.setSendDate(LocalDate.now());
        builder.setRecieveDate(LocalDate.of(9999, 12, 31));
        builder.setFee(fee);
        builder.setStatus(new TransactionStatus("Pending"));
        builder.setPriority(new TransactionPriority("NextDay", 180));
        builder.setCard(null);
        builder.setMedium(new MediumType("MoneyTransfer"));
        builder.setMediumNumber(String.valueOf(account2.getId()));

        return builder.getResult();
    }
}
