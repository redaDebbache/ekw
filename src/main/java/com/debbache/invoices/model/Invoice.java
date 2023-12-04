package com.debbache.invoices.model;

import java.math.BigDecimal;

public record Invoice(Consumption consumption, EnergyRate energyRate) {

    public double bill(){
        return BigDecimal.valueOf(consumption.consumed()).multiply(BigDecimal.valueOf(energyRate().rate())).setScale(2).doubleValue();
    }

}
