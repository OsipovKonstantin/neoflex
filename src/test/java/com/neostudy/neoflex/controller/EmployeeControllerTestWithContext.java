package com.neostudy.neoflex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neostudy.neoflex.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTestWithContext {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    EmployeeService employeeService;

    @Test
    void givenValidAverageSalaryAndVacationDays_whenCalculateVacationPay_thenReturnOk() throws Exception {
        mvc.perform(get("/calculacte")
                        .param("averageSalary", "50000.50")
                        .param("vacationDays", "2024-02-01", "2024-02-02"))
                .andExpect(status().isOk());
    }

    @Test
    void givenNoAverageSalaryAndVacationDays_whenCalculateVacationPay_thenReturnBadRequest() throws Exception {
        mvc.perform(get("/calculacte")
                        .param("vacationDays", "2024-02-01", "2024-02-02"))
                .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").value("Необходимо указать среднюю зарплату"));
    }

    @Test
    void givenNegativeAverageSalaryAndVacationDays_whenCalculateVacationPay_thenReturnBadRequest() throws Exception {
        mvc.perform(get("/calculacte")
                        .param("averageSalary", "-50000.50")
                        .param("vacationDays", "2024-02-01", "2024-02-02"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Средняя зарплата не может быть отрицательной"));
    }

    @Test
    void givenValidAverageSalaryAndNoVacationDays_whenCalculateVacationPay_thenReturnBadRequest() throws Exception {
        mvc.perform(get("/calculacte")
                        .param("averageSalary", "50000.50"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Необходим список отпускных дней"));
    }

    @Test
    void givenValidAverageSalaryAndZeroVacationDays_whenCalculateVacationPay_thenReturnBadRequest() throws Exception {
        mvc.perform(get("/calculacte")
                        .param("averageSalary", "50000.50")
                        .param("vacationDays", ""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Должен быть указан хотя бы один день отпуска"));
    }
}
