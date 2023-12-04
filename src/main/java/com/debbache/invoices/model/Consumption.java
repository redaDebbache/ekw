package com.debbache.invoices.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record Consumption(@NotNull Customer customer, @NotNull EnergyType energyType, double consumed ) {
}
