package com.bank.domain.finance;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by pafanasenko on 29.09.15.
 */
public class Rounder {
    private static MathContext divContext = null;
    private static RoundingMode roundingMode = null;
    static{
        roundingMode = RoundingMode.HALF_UP;
        divContext = new MathContext(24, roundingMode);
    }
    public static BigDecimal round(BigDecimal data, int digits){
        if (data.scale()>digits){
            if (data.precision() - (data.scale()-digits)>=0){
                MathContext ctx = new MathContext( data.precision() - (data.scale()-digits), roundingMode );
                return data.round( ctx );
            }
            else{
                System.out.println("!!!!!! Trying to round " + data + " up to " + digits + " digits. Failure!!!");
            }

        }
        return data;
    }
    public static MathContext getDivContext(){
        return divContext;
    }
}
