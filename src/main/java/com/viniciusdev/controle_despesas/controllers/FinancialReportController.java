package com.viniciusdev.controle_despesas.controllers;


import com.viniciusdev.controle_despesas.model.FinancialReport;
import com.viniciusdev.controle_despesas.model.enumereds.ExpenseType;
import com.viniciusdev.controle_despesas.services.FinancialReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("report")
public class FinancialReportController {

    private final FinancialReportService service;

    @GetMapping
    public FinancialReport generateReport(@RequestParam("customer") @Valid UUID customer,
                                          @RequestParam(value = "ExpenseType", required = false) ExpenseType expenseType){
       return service.generateReport(customer, expenseType);


    }


}
