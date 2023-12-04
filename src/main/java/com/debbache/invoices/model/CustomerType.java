package com.debbache.invoices.model;

import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;
import java.util.stream.Stream;

@RequiredArgsConstructor
public enum CustomerType {
    BIG_COMPANY(c -> c instanceof Professional p && p.getTurnover() > 1_000_000),
    SMALL_COMPANY(c -> c instanceof Professional p && p.getTurnover() <= 1_000_000),
    PARTICULAR(c -> c instanceof Particular),
    OTHER(c -> !(c instanceof Particular || c instanceof Professional));

    private final Predicate<Customer> discriminator;

    public static CustomerType getCustomerType(Customer customer){
        return Stream.of(values())
                .filter(v -> v.discriminator.test(customer))
                .findFirst()
                .orElse(OTHER);
    }

}
