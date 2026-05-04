package se.kth.iv1350.bikeRepairShop.integration;

import se.kth.iv1350.bikeRepairShop.model.businessLogic.CustomerDetails;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerRegistryTest {
    private CustomerRegistry customerRegistry;

    @BeforeEach
    public void setUp(){
        customerRegistry = new CustomerRegistry();
    }

    @Test
    public void getCustomerDetailsNotNull(){
        String phoneNumber = "0790781234";

        CustomerDetails storeInHere = customerRegistry.getCustomerDetails(phoneNumber);

        assertNotNull(storeInHere, "Customer should exist.");
    }

    @Test
    public void getCustomerDetailsNull(){
        String phoneNumber = "0000000000";

        CustomerDetails storeInHere = customerRegistry.getCustomerDetails(phoneNumber);

        assertNull(storeInHere, "Customer should not exist.");
    }
}
