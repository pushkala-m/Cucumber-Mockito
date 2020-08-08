package com.tech.diviso.model;

import org.springframework.stereotype.Repository;

@Repository
public class AddressRepo {
    public void save(AddressDTO address_dto) {
        System.out.println("............... repo saved");
    }
}
