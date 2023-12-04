package com.debbache.invoices.calculator;


import com.debbache.invoices.model.*;
import com.debbache.invoices.repository.RateRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceCalculatorTest {
    @InjectMocks
    InvoiceCalculator calculator;
    @Mock
    private RateRepository rateRepository;

    @Test
    public void should_throw_exception_when_no_energy_rate_is_found_for_a_given_customer() {
        //Given
        Consumption consumption = new Consumption(new Particular("EKW -- 1", "MR.", "John", "Doe"), EnergyType.GAZ, 1000);
        InvoiceCalculatorQuery query = new InvoiceCalculatorQuery(consumption);
        when(rateRepository.getEnergyRateByCustomer(consumption)).thenReturn(Optional.empty());
        //When
        Exception exception = Assertions.catchException(() -> calculator.produceInvoice(query));
        //Then
        Assertions.assertThat(exception).isInstanceOf(UnableToComputeInvoiceException.class)
                .hasMessage("Unable to compute the invoice for the customer having id EKW -- 1");
    }

    @Test
    public void should_return_invoice_when_energy_rate_is_found() {
        //Given
        Consumption consumption = new Consumption(new Particular("EKW -- 1", "MR.", "John", "Doe"), EnergyType.GAZ, 1000);
        InvoiceCalculatorQuery query = new InvoiceCalculatorQuery(consumption);
        EnergyRate enrgyRate = new EnergyRate(CustomerType.PARTICULAR, EnergyType.ELECTRICITY, 0.133);
        when(rateRepository.getEnergyRateByCustomer(consumption)).thenReturn(Optional.of(enrgyRate));
        //When
        Invoice invoice = calculator.produceInvoice(query);
        //Then
        Assertions.assertThat(invoice).isNotNull();
        Assertions.assertThat(invoice.consumption()).usingRecursiveComparison().isEqualTo(consumption);
        Assertions.assertThat(invoice.energyRate()).usingRecursiveComparison().isEqualTo(enrgyRate);
        Assertions.assertThat(invoice.bill()).isEqualTo(133.0);
    }

}
