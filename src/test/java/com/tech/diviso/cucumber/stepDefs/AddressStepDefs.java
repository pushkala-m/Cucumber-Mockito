package com.tech.diviso.cucumber.stepDefs;

import com.tech.diviso.model.AddressDTO;
import com.tech.diviso.model.AddressRepo;
import com.tech.diviso.model.AddressService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Assert;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;


import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RunWith(Cucumber.class)
@CucumberOptions(glue = {"com.tech.diviso.cucumber"})
public class AddressStepDefs {

    private AddressDTO address_dto;

    private AddressDTO default_address;

    private MockMvc restAddressMockMvc;

    @Mock
    private AddressRepo addressRepo;

    @Inject
    private AddressService addressService;

    List<AddressDTO>addresslist=new ArrayList<AddressDTO>();

//    AddressService addressService1= mock( AddressService.class);

    public void setup() {
        MockitoAnnotations.initMocks(this);

    }


    @Given("store creation with address")
    public void store_creation_with_address(io.cucumber.datatable.DataTable dataTable) {
        setup();
        System.out.println("first method");
        List<Map<String, String>> coloums = dataTable.asMaps();
        address_dto = new AddressDTO();
        System.out.println(coloums.get(0).get("addressType"));
        address_dto.setAddressType(coloums.get(0).get("addressType"));
        address_dto.setHouseNoOrBuildingName(coloums.get(0).get("houseNoOrBuildingName"));
        address_dto.setLandmark(coloums.get(0).get("landmark"));
        address_dto.setPincode(coloums.get(0).get("pincode"));
        address_dto.setRoadNameAreaOrStreet(coloums.get(0).get("roadNameAreaOrStreet"));
        address_dto.setState(coloums.get(0).get("state"));
        address_dto.setCity(coloums.get(0).get("city"));
        System.out.println(coloums.get(0).get("city"));
        addresslist.add(address_dto);
        addressService.save(address_dto);

    }


    @When("if the selected addressType is {string}")
    public void if_the_selected_addressType_is(String address) {
//        default_address=new AddressDTO();
//        default_address.setAddressType(address);
        addresslist.forEach( x-> {
            if(address.equals(x.getAddressType())) {
                default_address=x; } });
        Assert.assertTrue(address.equals(default_address.getAddressType()));
        System.out.println(" selected adress: " +default_address.getAddressType());

    }

    @Then("the default addressType should  {string}")
    public void the_default_addressType_should(String address) {
        System.out.println("default adress: " + address);
        Assert.assertTrue(default_address.getAddressType().equals(address));

    }
}
