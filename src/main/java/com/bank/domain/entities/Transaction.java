package com.bank.domain.entities;

import com.bank.domain.finance.Money;

/**
 * Created by pafanasenko on 28.09.15.
 */
public class Transaction {

    private Account accountFrom;
    private Account accountTo;
    private Money value;

    public Transaction (Account accoutFrom, Account accountTo, Money value) {
        this.accountFrom = accoutFrom;
        this.accountTo = accountTo;
        this.value = value;
    }
}
