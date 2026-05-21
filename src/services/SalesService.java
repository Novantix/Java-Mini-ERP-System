package services;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import models.Customer;
import models.Sale;

public class SalesService {

    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Sale> sales = new ArrayList<>();

    // Constructor
    public SalesService() {

        loadCustomers();
        loadSales();
    }

    public void addCustomer(Scanner sc) {

        System.out.println("Enter Customer ID:");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Customer Name:");
        String name = sc.nextLine();

        System.out.println("Enter Phone Number:");
        String phone = sc.nextLine();

        Customer customer = new Customer(id, name, phone);

        customers.add(customer);

        saveCustomer(customer);

        System.out.println("Customer Added Successfully");
    }

    public void viewCustomers() {

        if (customers.isEmpty()) {

            System.out.println("No Customers Found");
            return;
        }

        for (Customer customer : customers) {

            System.out.println(customer);
        }
    }

    public void addSale(Scanner sc) {

        System.out.println("Enter Sale ID:");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Product Name:");
        String productName = sc.nextLine();

        System.out.println("Enter Amount:");
        double amount = sc.nextDouble();
        sc.nextLine();

        Sale sale = new Sale(id, productName, amount);

        sales.add(sale);

        saveSale(sale);

        System.out.println("Sale Added Successfully");
    }

    public void viewSales() {

        if (sales.isEmpty()) {

            System.out.println("No Sales Found");
            return;
        }

        for (Sale sale : sales) {

            System.out.println(sale);
        }
    }

    public void generateInvoice() {

        if (sales.isEmpty()) {

            System.out.println("No Sales Available");
            return;
        }

        for (Sale sale : sales) {

            System.out.println("\n------------ INVOICE ------------");

            System.out.println("Sale ID       : " + sale.getSaleId());

            System.out.println("Product Name  : " + sale.getProductName());

            System.out.println("Amount        : " + sale.getAmount());

            System.out.println("GST 18%       : " + sale.getGst());

            System.out.println("Final Amount  : " + sale.getFinalAmount());

            System.out.println("---------------------------------");
        }
    }

    // SAVE CUSTOMER
    private void saveCustomer(Customer customer) {

        try {

            FileWriter fw = new FileWriter("data/customers.txt", true);

            fw.write(customer.toString() + "\n");

            fw.close();

        } catch (IOException e) {

            System.out.println("Error Saving Customer");
        }
    }

    // SAVE SALE
    private void saveSale(Sale sale) {

        try {

            FileWriter fw = new FileWriter("data/sales.txt", true);

            fw.write(sale.toString() + "\n");

            fw.close();

        } catch (IOException e) {

            System.out.println("Error Saving Sale");
        }
    }

    // LOAD CUSTOMERS FROM FILE
    private void loadCustomers() {

        try {

            File file = new File("data/customers.txt");

            if (!file.exists()) {
                return;
            }

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String phone = data[2];

                customers.add(new Customer(id, name, phone));
            }

            fileScanner.close();

        } catch (Exception e) {

            System.out.println("Error Loading Customers");
        }
    }

    // LOAD SALES FROM FILE
    private void loadSales() {

        try {

            File file = new File("data/sales.txt");

            if (!file.exists()) {
                return;
            }

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String productName = data[1];
                double amount = Double.parseDouble(data[2]);

                sales.add(new Sale(id, productName, amount));
            }

            fileScanner.close();

        } catch (Exception e) {

            System.out.println("Error Loading Sales");
        }
    }
}
