package com.bank.domain.finance;

import com.bank.domain.finance.MoneyAmount;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pafanasenko on 29.09.15.
 */
public class MoneyAmountTest {

    @Test
    public void testtoString() throws Exception {
        Assert.assertEquals("2.5", new MoneyAmount(2.5).toString());
        Assert.assertEquals("2", new MoneyAmount(2).toString());
        Assert.assertEquals("2.55", new MoneyAmount(2.55).toString());
        Assert.assertEquals("2.55", new MoneyAmount(2.551).toString());
        Assert.assertEquals("2.50", new MoneyAmount(2.495).toString());
        Assert.assertEquals("2.49", new MoneyAmount(2.494).toString());
    }

    @Test
    public void testCorrectness() throws Exception {
        Assert.assertEquals(new MoneyAmount(2.550), new MoneyAmount(2.551));
        Assert.assertEquals(new MoneyAmount(2.55), new MoneyAmount("2.551"));
        Assert.assertEquals(new MoneyAmount(2.55), new MoneyAmount("2.549"));
        //TODO review
        //Assert.assertEquals(new MoneyAmount(-12.50), new MoneyAmount("-12.501"));
        //Assert.assertEquals(new MoneyAmount(2.50), new MoneyAmount("2.501"));
    }
}