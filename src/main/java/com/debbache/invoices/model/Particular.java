package com.debbache.invoices.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class Particular extends Customer {
    private String civility;
    private String firstName;
    private String lastName;
@Builder
    public Particular(String clientReference, String civility, String firstName, String lastName) {
        super(clientReference);
        this.civility = civility;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
