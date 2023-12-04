package com.debbache.invoices.model;

import com.debbache.invoices.utils.PartnerParticular;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CustomerTypeTest {

    @ParameterizedTest
    @MethodSource("customer_type_source")
    public void should_return_customer_type_depending_on_customer(Customer customer, CustomerType expected) {
        //When
        CustomerType customerType = CustomerType.getCustomerType(customer);
        //Then
        Assertions.assertThat(customerType).isEqualTo(expected);
    }

    public static Stream<Arguments> customer_type_source() {
        return Stream.of(Arguments.of(new Professional("E-1", "S-1", "C-1", 1_000_001), CustomerType.BIG_COMPANY),
                Arguments.of(new Professional("E-2", "S-2", "C-2", 1_000_000), CustomerType.SMALL_COMPANY),
                Arguments.of(new Particular("E-3", "MRS.", "Jane", "Doe"), CustomerType.PARTICULAR),
                Arguments.of(new PartnerParticular("E-3"), CustomerType.OTHER)
        );
    }

}
