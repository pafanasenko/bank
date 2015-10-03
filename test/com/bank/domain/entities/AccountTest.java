package com.bank.domain.entities;

import com.bank.domain.finance.IExchangeRateSource;
import com.bank.domain.finance.Money;
import com.bank.domain.finance.MoneyExchanger;
import com.bank.domain.finance.MockExchangeRateSource;
import com.bank.domain.people.Customer;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by pafanasenko on 29.09.15.
 */
public class AccountTest {
    private final Customer c = new Customer("Peter", "Smith");

    @Test
    public void testCreateAccountWithNoMoney() throws Exception {
        Account a = new Account (c, MoneyExchanger.USD);
        assertEquals(new Money(0, MoneyExchanger.USD), a.getValue());
    }

    @Test
    public void testCreateAccountWithInitialMoney() throws Exception {
        Account a = new Account (c, new Money(75391.759, MoneyExchanger.EUR));
        assertEquals(new Money(75391.759, MoneyExchanger.EUR), a.getValue());
    }

    @Test
    public void testTransferMoneyFromOneAccountToAntoher() throws Exception {
        Account a1 = new Account (c, new Money(75391.759, MoneyExchanger.EUR));
        Account a2 = new Account (c, MoneyExchanger.EUR);
        a1.transferTo(new Money (25.74, MoneyExchanger.EUR), a2);
        assertEquals(new Money(75366.02, MoneyExchanger.EUR), a1.getValue());
        assertEquals(new Money(25.74, MoneyExchanger.EUR), a2.getValue());
    }

    @Test
    public void testTransferMoneyFromOneAccountToAntoherWithCurrencyConversion() throws Exception {
        Account a1 = new Account (c, new Money(75391.759, MoneyExchanger.EUR));
        Account a2 = new Account (c, MoneyExchanger.USD);

        MockExchangeRateSource mock = new MockExchangeRateSource();
        mock.set(MoneyExchanger.EUR, MoneyExchanger.USD, new BigDecimal(1), new BigDecimal(2));
        IExchangeRateSource source = MoneyExchanger.setExchangeRateSource(mock);
        try {
            a1.transferTo(new Money(25.74, MoneyExchanger.EUR), a2);
        } finally {
            MoneyExchanger.setExchangeRateSource(source);
        }

        assertEquals(new Money(75366.02, MoneyExchanger.EUR), a1.getValue());
        assertEquals(new Money(51.48, MoneyExchanger.USD), a2.getValue());
    }

    @Test
    public void testTransferMoneyFromNoMoneyAccount() throws Exception {
        Account a1 = new Account (c, MoneyExchanger.EUR);
        Account a2 = new Account (c, MoneyExchanger.EUR);
        try {
            a1.transferTo(new Money(25.74, MoneyExchanger.EUR), a2);
            fail ("Account.NegativeAccountValueException expected");
        } catch (Account.NegativeAccountValueException exception) {
            assertEquals("It's not allowed to withdraw more money than account has " +
                    "(account money value is 0 EUR, value to subtract is 25.74 EUR", exception.getMessage());
        }
    }

    @Test
    public void testTransferMoneyFromDeletedAccount() throws Exception {
        Account a1 = new Account (c, new Money(75391.759, MoneyExchanger.EUR));
        a1.delete ();
        Account a2 = new Account (c, MoneyExchanger.EUR);


        try {
            a1.transferTo(new Money(25.74, MoneyExchanger.EUR), a2);
            fail ("Account.DeletedAccountException expected");
        } catch (Account.DeletedAccountException exception) {
            assertEquals("It's not allowed to withdraw money from a deleted account", exception.getMessage());
        }

        assertEquals(new Money(75391.759, MoneyExchanger.EUR), a1.getValue());
        assertEquals(new Money(0, MoneyExchanger.EUR), a2.getValue());
    }

    @Test
    public void testTransferMoneyToDeletedAccount() throws Exception {
        Account a1 = new Account (c, new Money(75391.759, MoneyExchanger.EUR));
        Account a2 = new Account (c, MoneyExchanger.EUR);
        a2.delete();


        try {
            a1.transferTo(new Money(25.74, MoneyExchanger.EUR), a2);
            fail ("Account.DeletedAccountException expected");
        } catch (Account.DeletedAccountException exception) {
            assertEquals("It's not allowed to deposit money to a deleted account", exception.getMessage());
        }

        assertEquals(new Money(75391.759, MoneyExchanger.EUR), a1.getValue());
        assertEquals(new Money(0, MoneyExchanger.EUR), a2.getValue());
    }
}