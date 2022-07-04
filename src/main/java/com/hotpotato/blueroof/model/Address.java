package com.hotpotato.blueroof.model;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
