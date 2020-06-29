package pl.qbawalat.zpsbwebshopapi.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleUtils {
    public static Double roundDoubleToTwoPlaces(Double randomPrice) {
        return new BigDecimal(String.valueOf(randomPrice)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
