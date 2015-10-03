package com.bank.domain.finance;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by pafanasenko on 29.09.15.
 */
public class MoneyExchangerTest {

    @Test
    public void testExchangeNormal() throws Exception {
        MockExchangeRateSource mock = new MockExchangeRateSource();
        mock.set(MoneyExchanger.USD, MoneyExchanger.EUR, new BigDecimal(1), new BigDecimal(2));
        IExchangeRateSource source = MoneyExchanger.setExchangeRateSource(mock);
        try {
            Money m = MoneyExchanger.exchangeTo(new Money (new MoneyAmount(25.2), MoneyExchanger.USD), MoneyExchanger.EUR);
            Assert.assertEquals(m, new Money (new MoneyAmount(50.4), MoneyExchanger.EUR));
        } finally {
            MoneyExchanger.setExchangeRateSource(source);
        }
    }
}