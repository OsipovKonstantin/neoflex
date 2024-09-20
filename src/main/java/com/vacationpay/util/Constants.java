package com.vacationpay.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.MonthDay;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class Constants {
    public static final BigDecimal AVERAGE_DAYS_IN_MONTH = BigDecimal.valueOf(29.3);
    public static final Set<MonthDay> RUSSIAN_NEW_YEAR_PUBLIC_HOLIDAYS = Stream.of(
            MonthDay.of(1, 1),
            MonthDay.of(1, 2),
            MonthDay.of(1, 3),
            MonthDay.of(1, 4),
            MonthDay.of(1, 5),
            MonthDay.of(1, 6),
            MonthDay.of(1, 7),
            MonthDay.of(1, 8)
    ).collect(Collectors.toSet());

    public static final Set<MonthDay> RUSSIAN_OTHER_PUBLIC_HOLIDAYS = Stream.of(
            MonthDay.of(2, 23),
            MonthDay.of(3, 8),
            MonthDay.of(5, 1),
            MonthDay.of(5, 9),
            MonthDay.of(6, 12),
            MonthDay.of(11, 4)
    ).collect(Collectors.toSet());
}
