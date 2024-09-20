package com.neostudy.neoflex.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EmployeeServiceUnitTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void givenValidAverageSalaryAndWorkingVacationDays_whenCalculatingVacationPay_thenReturnPositiveVacationPay() {
        BigDecimal averageSalary = BigDecimal.valueOf(50000.50);
        List<LocalDate> vacationDays = new ArrayList<>(List.of(
                LocalDate.of(2024, 2, 1),
                LocalDate.of(2024, 2, 2)));
        assertThat(employeeService.calculateVacationPay(averageSalary, vacationDays))
                .isEqualTo(BigDecimal.valueOf(3413).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void givenValidAverageSalaryAndVacationDaysDuringNewYearHolidays_whenCalculatingVacationPay_thenReturnZeroVacationPay() {
        BigDecimal averageSalary = BigDecimal.valueOf(50000.50);
        List<LocalDate> vacationDays = new ArrayList<>(List.of(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 2),
                LocalDate.of(2024, 1, 3),
                LocalDate.of(2024, 1, 4),
                LocalDate.of(2024, 1, 5),
                LocalDate.of(2024, 1, 6),
                LocalDate.of(2024, 1, 7),
                LocalDate.of(2024, 1, 8)));
        assertThat(employeeService.calculateVacationPay(averageSalary, vacationDays))
                .isEqualTo(BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void givenValidAverageSalaryAndVacationDaysDuringOtherPublicHolidays_whenCalculatingVacationPay_thenReturnZeroVacationPay() {
        BigDecimal averageSalary = BigDecimal.valueOf(50000.50);
        List<LocalDate> vacationDays = new ArrayList<>(List.of(
                LocalDate.of(2024, 2, 23),
                LocalDate.of(2024, 3, 8),
                LocalDate.of(2024, 5, 1),
                LocalDate.of(2024, 5, 9),
                LocalDate.of(2024, 6, 12),
                LocalDate.of(2024, 11, 4)));
        assertThat(employeeService.calculateVacationPay(averageSalary, vacationDays))
                .isEqualTo(BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void givenValidAverageSalaryAndVacationDaysDuringPostponedPublicHoliday_whenCalculatingVacationPay_thenReturnZeroVacationPay() {
        BigDecimal averageSalary = BigDecimal.valueOf(50000.50);
        List<LocalDate> vacationDays = new ArrayList<>(List.of(
                LocalDate.of(2025, 2, 24)));
        assertThat(employeeService.calculateVacationPay(averageSalary, vacationDays))
                .isEqualTo(BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_EVEN));
    }
}
