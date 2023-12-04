package com.debbache.invoices.model;


public record EnergyRate(CustomerType customerType, EnergyType energyType, double rate) {
}
