package com.bank.domain.finance;

import com.bank.domain.finance.Money;
import com.bank.domain.finance.MoneyAmount;
import com.bank.domain.finance.MoneyExchanger;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pafanasenko on 29.09.15.
 */
public class MoneyTest {

    @Test
    public void testGetAmountCurrency() throws Exception {
        Money m = new Money(2, MoneyExchanger.USD);
        assertEquals(new MoneyAmount(2), m.getAmount());
        assertEquals(MoneyExchanger.USD, m.getCurrency());
        m = new Money(14.5, MoneyExchanger.EUR);
        assertEquals(new MoneyAmount(14.5), m.getAmount());
        assertEquals(MoneyExchanger.EUR, m.getCurrency());
        m = new Money("13.544", MoneyExchanger.RUB);
        assertEquals(new MoneyAmount(13.544), m.getAmount());
        assertEquals(MoneyExchanger.RUB, m.getCurrency());
    }

    @Test
    public void testPlus() throws Exception {
        Money x = new Money (2.344, MoneyExchanger.USD);
        Money y = new Money (5.474, MoneyExchanger.USD);
        assertEquals(new Money (7.81, MoneyExchanger.USD), x.plus(y));
    }

    @Test
    public void testPlusDiffCurrencies() throws Exception {
        try {
            Money x = new Money(2.344, MoneyExchanger.USD);
            Money y = new Money(5.474, MoneyExchanger.EUR);
            assertEquals(new Money(7.81, MoneyExchanger.USD), x.plus(y));
            fail("Expected MismatchedCurrencyException");
        } catch (Money.MismatchedCurrencyException exception) {
            assertEquals("EUR doesn't match the expected currency : USD", exception.getMessage());
        }
    }

    @Test
    public void testMinusDiffCurrencies() throws Exception {
        try {
            Money x = new Money (2.343, MoneyExchanger.USD);
            Money y = new Money (5.474, MoneyExchanger.EUR);
            assertEquals(new Money (-3.13, MoneyExchanger.USD), x.minus(y));
            fail("Expected MismatchedCurrencyException");
        } catch (Money.MismatchedCurrencyException exception) {
            assertEquals("EUR doesn't match the expected currency : USD", exception.getMessage());
        }
    }

    @Test
    public void testEquals() throws Exception {
        Money x = new Money ("5.474", MoneyExchanger.USD);
        Money y = new Money (5.474, MoneyExchanger.USD);
        assertEquals(x, y);
    }

    @Test
    public void testNotEqual() throws Exception {
        Money x = new Money (4245346.55, MoneyExchanger.USD);
        Money y = new Money (5.474, MoneyExchanger.USD);
        assertNotSame(x, y);

        x = new Money (5.474, MoneyExchanger.USD);
        y = new Money (5.474, MoneyExchanger.EUR);
        assertNotSame(x, y);
    }

    @Test
    public void testLessThan () throws Exception {
        Money x = new Money (4245346.55, MoneyExchanger.USD);
        Money y = new Money (5.474, MoneyExchanger.USD);
        assertTrue(y.lt(x));
        assertFalse(x.lt(y));
        assertFalse(x.lt(x));
    }

    @Test
    public void testLessThanWithDifferentCurrencies () throws Exception {
        try {
            Money x = new Money (4245346.55, MoneyExchanger.USD);
            Money y = new Money (5.474, MoneyExchanger.EUR);
            assertTrue(y.lt(x));
            fail("Expected MismatchedCurrencyException");
        } catch (Money.MismatchedCurrencyException exception) {
            assertEquals("USD doesn't match the expected currency : EUR", exception.getMessage());
        }
    }

    @Test
    public void testGreaterThan () throws Exception {
        Money x = new Money (4245346.55, MoneyExchanger.USD);
        Money y = new Money (5.474, MoneyExchanger.USD);
        assertTrue(x.gt(y));
        assertFalse(y.gt(x));
        assertFalse(x.gt(x));
    }

    @Test
    public void testGreaterThanWithDifferentCurrencies () throws Exception {
        try {
            Money x = new Money (4245346.55, MoneyExchanger.USD);
            Money y = new Money (5.474, MoneyExchanger.EUR);
            assertTrue(x.lt(y));
            fail("Expected MismatchedCurrencyException");
        } catch (Money.MismatchedCurrencyException exception) {
            assertEquals("EUR doesn't match the expected currency : USD", exception.getMessage());
        }
    }}