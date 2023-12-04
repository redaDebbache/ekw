package com.debbache.invoices.calculator;

import com.debbache.invoices.model.Customer;

public class UnableToComputeInvoiceException extends RuntimeException{
    public UnableToComputeInvoiceException(Customer customer) {
        super(String.format("Unable to compute the invoice for the customer having id %s", customer.getClientReference()));
    }
}
