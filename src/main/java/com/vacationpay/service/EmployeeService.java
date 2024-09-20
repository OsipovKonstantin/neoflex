package com.vacationpay.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    BigDecimal calculateVacationPay(BigDecimal averageSalary, List<LocalDate> vacationDays);
}
