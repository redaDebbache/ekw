package com.debbache.invoices.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class Professional extends Customer{
    private String siret;
    private String companyName;
    private long turnover;

    @Builder
    public Professional(String clientReference, String siret, String companyName, long turnover) {
        super(clientReference);
        this.siret = siret;
        this.companyName = companyName;
        this.turnover = turnover;
    }
}
