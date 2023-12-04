package com.debbache.invoices.calculator;

import com.debbache.invoices.model.Invoice;
import com.debbache.invoices.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class InvoiceCalculator {
    private final RateRepository rateRepository;

    public Invoice produceInvoice(InvoiceCalculatorQuery query) {
        return rateRepository
                .getEnergyRateByCustomer(query.consumption())
                .map(energy -> new Invoice(query.consumption(), energy))
                .orElseThrow(() -> new UnableToComputeInvoiceException(query.customer()));
    }
}
