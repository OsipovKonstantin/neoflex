package com.vacationpay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.List;

import static com.vacationpay.util.Constants.*;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public BigDecimal calculateVacationPay(BigDecimal averageSalary, List<LocalDate> vacationDays) {
        log.info("Рассчитываем величину отпускных по указанным средней зарплате {} и отпускным дням {}",
                averageSalary, vacationDays);
        BigDecimal vacationDayPay = calculateVacationDayPay(averageSalary);
        int paidVacationDays = countPaidVacationDays(vacationDays);
        BigDecimal vacationPay = vacationDayPay.multiply(BigDecimal.valueOf(paidVacationDays));
        log.info("Величина отпускных составила {} рублей. Оплачиваемых отпускных дней {} из {}",
                vacationPay, paidVacationDays, vacationDays.size());
        return vacationPay;
    }

    private BigDecimal calculateVacationDayPay(BigDecimal averageSalary) {
        return averageSalary.divide(AVERAGE_DAYS_IN_MONTH, 2, RoundingMode.HALF_EVEN);
    }

    private int countPaidVacationDays(List<LocalDate> vacationDays) {
        return (int) vacationDays.stream()
                .filter(day -> !isWeekend(day) && !isHoliday(day) && !isPostponedHoliday(day))
                .count();
    }

    private boolean isWeekend(LocalDate day) {
        return day.getDayOfWeek() == DayOfWeek.SATURDAY || day.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private boolean isHoliday(LocalDate day) {
        MonthDay monthDay = MonthDay.from(day);
        return RUSSIAN_NEW_YEAR_PUBLIC_HOLIDAYS.contains(monthDay) || RUSSIAN_OTHER_PUBLIC_HOLIDAYS.contains(monthDay);
    }

    private boolean isPostponedHoliday(LocalDate day) {
        return day.getDayOfWeek() == DayOfWeek.MONDAY
                && (isHoliday(day.minusDays(1))
                || isHoliday(day.minusDays(2)));
    }
}
