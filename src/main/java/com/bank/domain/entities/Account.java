package com.bank.domain.entities;

import com.bank.domain.finance.Money;
import com.bank.domain.finance.MoneyExchanger;
import com.bank.domain.people.Customer;

import java.util.ArrayList;
import java.util.Currency;

/**
 * Created by pafanasenko on 28.09.15.
 */
public class Account extends DeletableEntity {

    private Money value;
    private Customer owner;
    private ArrayList<Transaction> transactions;

    //TODO review transfering from RuntimeException superclass
    public static final class NegativeAccountValueException extends RuntimeException {
        NegativeAccountValueException(String aMessage){
            super(aMessage);
        }
    }

    //TODO review transfering from RuntimeException superclass
    public static final class DeletedAccountException extends RuntimeException {
        DeletedAccountException(String aMessage){
            super(aMessage);
        }
    }

    public Account (Customer owner, Money value) {
        this.owner = owner;
        this.value = value;
    }

    public Account (Customer owner, Currency currency) {
        this.owner = owner;
        this.value = new Money(0, currency);
    }

    //TODO review public
    //TODO move operation to another class
    public void transferTo (Money money, Account to) {
        if (isDeleted())
            throw new DeletedAccountException ("It's not allowed to withdraw money from a deleted account");
        if (to.isDeleted())
            throw new DeletedAccountException ("It's not allowed to deposit money to a deleted account");

        Money toTransfer = new Money (money);
        operator().Withdraw(toTransfer);
        if (to.getCurrency().equals(getCurrency()) == false)
            toTransfer = MoneyExchanger.exchangeTo(money, to.getCurrency());
        to.operator().Deposit(toTransfer);
    }

    //TODO review public
    public Currency getCurrency () { return getValue().getCurrency(); }
    //TODO review public
    public Money getValue() { return value; }
    //TODO review public
    public Operator operator () { return new Operator(); }
    //TODO review public
    public class Operator {
        //TODO review public
        public void Deposit (Money m) {
            value = value.plus(m);
        }

        //TODO review public
        public void Withdraw (Money m) {
            if (value.lt(m))
                throw new NegativeAccountValueException("It's not allowed to withdraw more money than account has " +
                        "(account money value is " + value.toString() + ", value to subtract is " + m.toString());
            value = value.minus(m);
        }
    }

}
