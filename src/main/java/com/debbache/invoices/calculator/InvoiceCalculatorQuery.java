package com.debbache.invoices.calculator;

import com.debbache.invoices.model.Consumption;
import com.debbache.invoices.model.Customer;

import javax.validation.constraints.NotNull;

public record InvoiceCalculatorQuery(@NotNull Consumption consumption) {
    public Customer customer() {
        return consumption.customer();
    }
}
