package com.debbache.invoices.model;


import javax.validation.constraints.NotNull;

public record EnergyRate(@NotNull CustomerType customerType, @NotNull EnergyType energyType, double rate) {
}
