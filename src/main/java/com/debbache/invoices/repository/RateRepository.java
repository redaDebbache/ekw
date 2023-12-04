package com.debbache.invoices.repository;

import com.debbache.invoices.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RateRepository {
    private final static List<EnergyRate> ENERGY_RATES = List.of(
            new EnergyRate(CustomerType.BIG_COMPANY, EnergyType.GAZ, 0.123),
            new EnergyRate(CustomerType.BIG_COMPANY, EnergyType.ELECTRICITY, 0.110),
            new EnergyRate(CustomerType.SMALL_COMPANY, EnergyType.GAZ, 0.117),
            new EnergyRate(CustomerType.SMALL_COMPANY, EnergyType.ELECTRICITY, 0.112),
            new EnergyRate(CustomerType.PARTICULAR, EnergyType.GAZ, 0.108),
            new EnergyRate(CustomerType.PARTICULAR, EnergyType.ELECTRICITY, 0.133));

    public Optional<EnergyRate> getEnergyRateByCustomer(Consumption consumption) {
        return ENERGY_RATES.stream()
                .filter(e -> e.customerType() == CustomerType.getCustomerType(consumption.customer())
                        && e.energyType() == consumption.energyType())
                .findFirst();
    }
}
