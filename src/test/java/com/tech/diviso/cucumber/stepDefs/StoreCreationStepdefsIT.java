package com.tech.diviso.cucumber.stepDefs;

import com.tech.diviso.domain.Store;
import com.tech.diviso.repository.StoreRepository;
import com.tech.diviso.service.StoreService;
import com.tech.diviso.service.dto.StoreDTO;
import com.tech.diviso.service.impl.StoreServiceImpl;
import com.tech.diviso.service.mapper.StoreMapper;
import com.tech.diviso.web.rest.StoreResource;
import com.tech.diviso.web.rest.TestUtil;
import com.tech.diviso.web.rest.errors.ExceptionTranslator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.util.List;
import java.util.Map;

import static com.tech.diviso.web.rest.TestUtil.createFormattingConversionService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(Cucumber.class)
@CucumberOptions(glue = {"com.tech.diviso.cucumber"})
public class StoreCreationStepdefsIT {


    @Autowired
//    @Mock
    StoreRepository storeRepository;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
//  @Inject
    StoreService storeService;

    @Autowired
    private Validator validator;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restStoreMockMvc;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private Store store;

    @Autowired
    StoreResource storeResource;


    public void setup() {

        this.restStoreMockMvc = MockMvcBuilders.standaloneSetup(storeResource).build();
    }


    @Given("configure Store controll")
    public void configure_Store_controll() {
        setup();
        System.out.println("configuration method");
    }

    @When("we given store to create")
    public void we_given_store_to_create(io.cucumber.datatable.DataTable dataTable) {
        System.out.println(" 2nd method");

        List<Map<String,String>> coloums=dataTable.asMaps();
        store=new Store();
        store.setName(coloums.get(0).get("name"));
        store.setDescription(coloums.get(0).get("description"));
        store.setPhoneNumber(coloums.get(0).get("phone"));


        try {
            restStoreMockMvc.perform(post("/api/stores")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(storeMapper.toDto(store))))
                .andExpect(status().isCreated());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Then("find all and check for")
    public void find_all_and_check_for(io.cucumber.datatable.DataTable dataTable) {
        System.out.println(" 3d method");
        List<StoreDTO> stores= storeService.findAll();
        Assert.assertTrue(stores.get(0).getName().equals(store.getName()));
    }
}
