package com.debbache.invoices.integration;

import com.debbache.invoices.calculator.InvoiceCalculator;
import com.debbache.invoices.calculator.InvoiceCalculatorQuery;
import com.debbache.invoices.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.stream.Stream;

@SpringBootTest
public class InvoiceCalculatingIntegrationTest {
    @Autowired
    InvoiceCalculator invoiceCalculator;


    @ParameterizedTest
    @MethodSource("consumption_source")
    public void should_compute_invoices_for_customer_consumptions(Customer customer, EnergyType energyType, double energyConsumed, double expectedBill) {
        //Given
        Consumption consumption = new Consumption(customer, energyType, energyConsumed);
        InvoiceCalculatorQuery query = new InvoiceCalculatorQuery(consumption);
        //When
        Invoice invoice = invoiceCalculator.produceInvoice(query);
        //Then
        Assertions.assertThat(invoice).isNotNull();
        Assertions.assertThat(invoice.bill()).isEqualTo(expectedBill);
    }

    public static Stream<Arguments> consumption_source() {
        return Stream.of(
                Arguments.of(new Professional("E-1", "S-1", "C-1", 1_000_001), EnergyType.GAZ, 100, 12.3),
                Arguments.of(new Professional("E-2", "S-2", "C-2", 1_000_001), EnergyType.ELECTRICITY, 100, 11),
                Arguments.of(new Professional("E-3", "S-3", "C-3", 1_000_000), EnergyType.GAZ, 100, 11.7),
                Arguments.of(new Professional("E-4", "S-4", "C-4", 1_000_000), EnergyType.ELECTRICITY, 100, 11.2),
                Arguments.of(new Particular("E-5", "MR.", "John", "Doe"), EnergyType.GAZ,  100, 10.8),
                Arguments.of(new Particular("E-6", "MRS.", "Jane", "Doe"), EnergyType.ELECTRICITY,  150, 19.95)
        );
    }
}
