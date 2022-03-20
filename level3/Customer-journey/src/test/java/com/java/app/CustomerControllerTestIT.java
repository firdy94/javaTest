package com.java.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.app.controller.CustomerController;
import com.java.app.dao.Customer;
import com.java.app.dao.CustomerRepoService;
import com.java.app.dao.CustomerRepository;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {CustomerController.class, CustomerRepoService.class, CustomerRepository.class, Customer.class})
@WebMvcTest
class CustomerControllerTestIT {
    private final static String TEST_USER_ID = "user-id-123";

    @Autowired
    private MockMvc mockMvc;
    
    ObjectMapper objMapper =new ObjectMapper();

    @Test
    public void testGetCustomer() throws Exception {
    	
    	Customer customer = new Customer("1", "John", "Kennedy");
    	String jsonResp = objMapper.writeValueAsString(customer);
    	testGetCustomerEndpoint("1", jsonResp);
    	}
    @Test
    public void testAddCustomer() throws Exception {
    	Customer customer = new Customer("7", "Jonathan", "Hamsfield");
    	String customerString= objMapper.writeValueAsString(customer);
    	testAddCustomerEndpoint(customerString, customerString);    	
    	}
    @Test
    public void testUpdateCustomer() throws Exception {
    	Customer customer = new Customer("1", "Jonathan", "Hamsfield");
    	String customerString= objMapper.writeValueAsString(customer);
    	testUpdateCustomerEndpoint(customerString, customerString); 
    	}
    @Test
    public void testDeleteCustomer() throws Exception {
    	testDeleteCustomerEndpoint("1", "Customer record deleted");
    	}
    
    
    
    
    
    
    
    
    private void testGetCustomerEndpoint(String id, String customer) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/1/customer/%s".formatted(id))
                .with(user(TEST_USER_ID))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultCustomer = result.getResponse().getContentAsString();
        assertNotNull(resultCustomer);
        assertEquals(customer, resultCustomer);
    }

    private void testAddCustomerEndpoint(String jsonReq, String customerResp) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/1/customer/")
                .with(user(TEST_USER_ID))
                .content(jsonReq)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultCustomer = result.getResponse().getContentAsString();
        assertNotNull(resultCustomer);
        assertEquals(customerResp, resultCustomer);
    }

    private void testUpdateCustomerEndpoint(String jsonReq, String customerResp) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/1/customer/")
                .with(user(TEST_USER_ID))
                .content(jsonReq)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultCustomer = result.getResponse().getContentAsString();
        assertNotNull(resultCustomer);
        assertEquals(customerResp, resultCustomer);
    }
    
    private void testDeleteCustomerEndpoint(String id, String customer) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/1/customer/%s".formatted(id))
                .with(user(TEST_USER_ID))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultStatus = result.getResponse().getContentAsString();
        assertNotNull(resultStatus);
        assertEquals(customer, resultStatus);
    	
    }
    
    
    
    
}