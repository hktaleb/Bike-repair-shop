package se.kth.iv1350.bikeRepairShop.startup;

import se.kth.iv1350.bikeRepairShop.controller.Controller;
import se.kth.iv1350.bikeRepairShop.integration.CustomerRegistry;
import se.kth.iv1350.bikeRepairShop.integration.RepairOrderRegistry;
import se.kth.iv1350.bikeRepairShop.integration.Printer;
import se.kth.iv1350.bikeRepairShop.view.View;

/**
 * Starts the program.
 */
public class Main {

    /**
     * The main method of the class.
     * @param args not used
     */
    public static void main(String[] args) {

        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        Printer printer = new Printer();

        Controller controller = new Controller(
            repairOrderRegistry,
            customerRegistry,
            printer
        );

        View view = new View(controller);

        view.startFlow();
    }
}
