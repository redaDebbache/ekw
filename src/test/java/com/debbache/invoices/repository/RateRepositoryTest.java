package com.debbache.invoices.repository;


import com.debbache.invoices.model.*;
import com.debbache.invoices.utils.PartnerParticular;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

class RateRepositoryTest {
    private final RateRepository rateRepository = new RateRepository();


    @Test
    public void should_return_empty_optional_when_energy_rate_not_found() {
        //Given
        Consumption consumption = new Consumption(new PartnerParticular("E-1"), EnergyType.GAZ, 1);
        //When
        Optional<EnergyRate> energyRateByCustomer = rateRepository.getEnergyRateByCustomer(consumption);
        //Then
        Assertions.assertThat(energyRateByCustomer).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("consumption_source")
    public void should_return_energy_rate_depending_on_consumption(Customer customer, EnergyType energyType, EnergyRate expected) {
        //Given
        Consumption consumption = new Consumption(customer, energyType, 1);
        //When
        Optional<EnergyRate> energyRateByCustomer = rateRepository.getEnergyRateByCustomer(consumption);
        //Then
        Assertions.assertThat(energyRateByCustomer).isNotEmpty().contains(expected);
    }

    public static Stream<Arguments> consumption_source() {
        return Stream.of(
                Arguments.of(new Professional("E-1", "S-1", "C-1", 1_000_001), EnergyType.GAZ, new EnergyRate(CustomerType.BIG_COMPANY, EnergyType.GAZ, 0.123)),
                Arguments.of(new Professional("E-2", "S-2", "C-2", 1_000_001), EnergyType.ELECTRICITY, new EnergyRate(CustomerType.BIG_COMPANY, EnergyType.ELECTRICITY, 0.110)),
                Arguments.of(new Professional("E-3", "S-3", "C-3", 1_000_000), EnergyType.GAZ, new EnergyRate(CustomerType.SMALL_COMPANY, EnergyType.GAZ, 0.117)),
                Arguments.of(new Professional("E-4", "S-4", "C-4", 1_000_000), EnergyType.ELECTRICITY, new EnergyRate(CustomerType.SMALL_COMPANY, EnergyType.ELECTRICITY, 0.112)),
                Arguments.of(new Particular("E-5", "MR.", "John", "Doe"), EnergyType.GAZ,  new EnergyRate(CustomerType.PARTICULAR, EnergyType.GAZ, 0.108)),
                Arguments.of(new Particular("E-6", "MRS.", "Jane", "Doe"), EnergyType.ELECTRICITY,  new EnergyRate(CustomerType.PARTICULAR, EnergyType.ELECTRICITY, 0.133))
                );
    }
}
