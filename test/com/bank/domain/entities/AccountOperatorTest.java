package com.bank.domain.entities;

import com.bank.domain.entities.Account;
import com.bank.domain.finance.Money;
import com.bank.domain.finance.MoneyExchanger;
import com.bank.domain.people.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pafanasenko on 29.09.15.
 */
public class AccountOperatorTest {
    private final Customer c = new Customer("Peter", "Smith");

    @Test
    public void testDeposit() throws Exception {
        Account a = new Account (c, MoneyExchanger.USD);
        a.operator().Deposit(new Money(25.6363, MoneyExchanger.USD));
        assertEquals(new Money(25.64, MoneyExchanger.USD), a.getValue());
    }

    public void testDepositWtihAnotherCurrency() throws Exception {
        try {
            Account a = new Account (c, MoneyExchanger.USD);
            a.operator().Deposit(new Money(25.6363, MoneyExchanger.EUR));
            fail("Expected MismatchedCurrencyException");
        } catch (Money.MismatchedCurrencyException exception) {
            assertEquals("EUR doesn't match the expected currency : USD", exception.getMessage());
        }
    }

    @Test
    public void testWithdraw() throws Exception {
        Account a = new Account (c, new Money(181.444, MoneyExchanger.USD));
        a.operator().Withdraw(new Money(25.6363, MoneyExchanger.USD));
        assertEquals(new Money(155.80, MoneyExchanger.USD), a.getValue());
    }

    @Test
    public void testWithdrawWithNegativeAccountState() throws Exception {
        try {
            Account a = new Account (c, new Money(11.444, MoneyExchanger.USD));
            a.operator().Withdraw(new Money(25.6363, MoneyExchanger.USD));
            fail("Expected NegativeAccountValueException");
        } catch (Account.NegativeAccountValueException exception) {
            assertEquals("It's not allowed to withdraw more money than account has " +
                    "(account money value is 11.44 USD, value to subtract is 25.64 USD", exception.getMessage());
        }
    }

    @Test
    public void testWithdrawWithAnotherCurrency() throws Exception {
        try {
            Account a = new Account (c, new Money(181.444, MoneyExchanger.EUR));
            a.operator().Withdraw(new Money(25.6363, MoneyExchanger.USD));
            fail("Expected MismatchedCurrencyException");
        } catch (Money.MismatchedCurrencyException exception) {
            assertEquals("USD doesn't match the expected currency : EUR", exception.getMessage());
        }
    }
}