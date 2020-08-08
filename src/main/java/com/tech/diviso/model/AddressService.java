package com.tech.diviso.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressService {
    @Autowired
    AddressRepo adr;
    public void  save(AddressDTO address_dto) {
        System.out.println("saved..........");
        adr.save(address_dto);

   }
}
