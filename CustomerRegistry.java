package se.kth.iv1350.bikeRepairShop.integration;

import se.kth.iv1350.bikeRepairShop.model.businessLogic.CustomerDetails;
import se.kth.iv1350.bikeRepairShop.model.businessLogic.Bike;
import java.util.ArrayList;

/**
 * Stores and provides access to customer details.
 */
public class CustomerRegistry {
    private ArrayList<CustomerDetails> storedCustomers = new ArrayList<>();

    /**
     * Creates a new registry and initializes it with fake data.
     */
    public CustomerRegistry(){
        addFakeData();
    }

    /**
     * Retrieves customer details using a phone number.
     * @param phoneNumber the customer's phone number
     * @return the matching customer details, or <code>null</code> if no match is found
     */
    public CustomerDetails getCustomerDetails(String phoneNumber){
        for (int i = 0; i < storedCustomers.size(); i++){
            CustomerDetails currentCustomer = storedCustomers.get(i);

            if (currentCustomer.getPhoneNumber().equals(phoneNumber))
                return currentCustomer;
        }
        return null;
    }

    /**
     * Adds fake data to the registry.
     */
    private void addFakeData() {
        Bike fakeBike = new Bike("BMX", "Crazy", "ABC123");
        
        storedCustomers.add(new CustomerDetails("Morgan Falk", "0790781234",
        "MF@gmail.com", fakeBike));
    }
}
