package se.kth.iv1350.bikeRepairShop.controller;

import se.kth.iv1350.bikeRepairShop.model.businessLogic.RepairTasks;
import se.kth.iv1350.bikeRepairShop.model.dto.CustomerDetailsDTO;
import se.kth.iv1350.bikeRepairShop.model.dto.RepairOrderDTO;
import se.kth.iv1350.bikeRepairShop.integration.Printer;
import se.kth.iv1350.bikeRepairShop.integration.CustomerRegistry;
import se.kth.iv1350.bikeRepairShop.integration.RepairOrderRegistry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControllerTest{

    private Controller controller;

    @BeforeEach
    public void setUp(){
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        Printer printer = new Printer();

        controller = new Controller(repairOrderRegistry, customerRegistry, printer);
    }

    @Test
    public void searchForCustomerNotNull(){
        String phoneNumber = "0790781234";

        CustomerDetailsDTO storeInHere = controller.search(phoneNumber);

        assertNotNull(storeInHere, "search returned null for existing customer.");
    }

    @Test
    public void searchForCustomerNull(){
        String phoneNumber = "0123456789";

        CustomerDetailsDTO storeInHere = controller.search(phoneNumber);

        assertNull(storeInHere, "search did not return null for non-existing customer.");
    }

    @Test
    public void searchForCustomerWithEmptyString(){
        String phoneNumber = "";

        CustomerDetailsDTO storeInHere = controller.search(phoneNumber);

        assertNull(storeInHere, "search should return null for empty phone number.");
    }

    @Test
    public void reportProblemNotNull(){
        String description = "The brakes do not work.";
        String phoneNumber = "0790781234";

        RepairOrderDTO storeInHere = controller.reportProblem(description, phoneNumber);

        assertNotNull(storeInHere, "reportProblem returned null for existing customer");
    }

    @Test
    public void reportProblemWithEmptyDescriptionAndNumber(){
        String description = "";
        String phoneNumber = "";

        RepairOrderDTO storeInHere = controller.reportProblem(description, phoneNumber);

        assertNull(storeInHere, 
            "reportProblem should return null for empty description and phone number.");
    }

    @Test
    public void getRepairOrderReturnsNotNull(){
        String phoneNumber = "0790781234";
        String description = "The brakes do not work.";

        controller.reportProblem(description, phoneNumber);

        RepairOrderDTO storeInHere = controller.getRepairOrder(phoneNumber);

        assertNotNull(storeInHere, "getRepairOrder returned null for existing order.");
    }

    @Test
    public void getRepairOrderReturnsNull(){
        String phoneNumber = "";

        RepairOrderDTO storeInHere = controller.getRepairOrder(phoneNumber);

        assertNull(storeInHere, "getRepairOrder should return null when no order exists.");
    }

    @Test
    public void getRepairOrderReturnsCorrectDescription(){
        String phoneNumber = "0790781234";
        String description = "The brakes do not work.";

        controller.reportProblem(description, phoneNumber);

        RepairOrderDTO storeInHere = controller.getRepairOrder(phoneNumber);

        String expResult = description;
        String result = storeInHere.getDescription();

        assertEquals(expResult, result, "Wrong description returned from getRepairOrder.");
    }

    @Test
    public void rejectRepairOrderCheckState(){
        String phoneNumber = "0790781234";
        String description = "The brakes do not work";

        controller.reportProblem(description, phoneNumber);
        controller.rejectRepairOrder(phoneNumber);

        RepairOrderDTO storeInHere = controller.getRepairOrder(phoneNumber);

        String expResult = "REJECTED";
        String result = storeInHere.getState();

        assertEquals(expResult, result, "The order was not rejected.");
    }

    @Test
    public void repairAcceptedCheckState(){
        String phoneNumber = "0790781234";
        String description = "The brakes do not work";

        controller.reportProblem(description, phoneNumber);
        controller.repairAccepted(phoneNumber);

        RepairOrderDTO storeInHere = controller.getRepairOrder(phoneNumber);        

        String expResult = "ACCEPTED";
        String result = storeInHere.getState();

        assertEquals(expResult, result, "The order was not accepted.");
    }

    @Test
    public void updateOrderNotNull(){
        String phoneNumber = "0790781234";
        String description = "The brakes do not work.";
        String diagnostics = "Brake wires broken.";
        
        RepairTasks tasks = new RepairTasks("Brake repair");
        tasks.addTask("Add brake wires", 500);

        controller.reportProblem(description, phoneNumber);

        RepairOrderDTO storeInHere = controller.updateOrder(phoneNumber, diagnostics, tasks);

        assertNotNull(storeInHere, "updateOrder returned null for existing order.");
    }

    @Test
    public void updateOrderWithEmptyTasks(){
        String phoneNumber = "0790781234";
        String description = "The brakes do not work.";
        String diagnostics = "Brake wires broken.";
        
        RepairTasks tasks = new RepairTasks("");

        controller.reportProblem(description, phoneNumber);

        RepairOrderDTO storeInHere = controller.updateOrder(phoneNumber, diagnostics, tasks);

        assertNotNull(storeInHere, "updateOrder failed with empty tasks.");
    }
}
