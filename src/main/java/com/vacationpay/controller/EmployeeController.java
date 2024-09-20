package com.vacationpay.controller;

import com.vacationpay.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/calculacte")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal calculateVacationPay(@RequestParam(required = false)
                                           @NotNull(message = "Необходимо указать среднюю зарплату")
                                           @PositiveOrZero(message = "Средняя зарплата не может быть отрицательной")
                                           BigDecimal averageSalary,
                                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                           @NotNull(message = "Необходим список отпускных дней")
                                           @Size(min = 1, message = "Должен быть указан хотя бы один день отпуска")
                                           List<LocalDate> vacationDays) {
        return employeeService.calculateVacationPay(averageSalary, vacationDays);
    }
}