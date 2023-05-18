package com.example.UtilityBill;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtilityBillController {
    @FXML
    private Label electricityBillLabel;

    @FXML
    private Label waterBillLabel;

    @FXML
    private Label gasBillLabel;

    @FXML
    private Label grandTotalLabel;

    @FXML
    private TextField electricityRateField;

    @FXML
    private TextField electricityUnitsField;

    @FXML
    private TextField waterRateField;

    @FXML
    private TextField waterUnitsField;

    @FXML
    private TextField gasRateField;

    @FXML
    private TextField gasUnitsField;

    @FXML
    private Button calculateButton;

    @FXML
    public void initialize() {
        calculateButton.setOnAction(event -> {
            // Calculate electricity bill
            double electricityUnits = Double.parseDouble(electricityUnitsField.getText());
            double electricityRate = Double.parseDouble(electricityRateField.getText());
            double electricityBill = electricityUnits * electricityRate;
            electricityBillLabel.setText(String.format("Electricity bill: $%.2f", electricityBill));

            // Calculate water bill
            double waterUnits = Double.parseDouble(waterUnitsField.getText());
            double waterRate = Double.parseDouble(waterRateField.getText());
            double waterBill = waterUnits * waterRate;
            waterBillLabel.setText(String.format("Water bill: $%.2f", waterBill));

            // Calculate gas bill
            double gasUnits = Double.parseDouble(gasUnitsField.getText());
            double gasRate = Double.parseDouble(gasRateField.getText());
            double gasBill = gasUnits * gasRate;
            gasBillLabel.setText(String.format("Gas bill: $%.2f", gasBill));

            // Calculate and display grand total
            double grandTotal = electricityBill + waterBill + gasBill;
            grandTotalLabel.setText(String.format("Grand total: $%.2f", grandTotal));

            // Save data in the database
            saveDataToDatabase(electricityUnits, electricityRate, electricityBill,
                    waterUnits, waterRate, waterBill, gasUnits, gasRate, gasBill, grandTotal);
        });
    }

    private void saveDataToDatabase(double electricityUnits, double electricityRate, double electricityBill,
                                    double waterUnits, double waterRate, double waterBill,
                                    double gasUnits, double gasRate, double gasBill, double grandTotal) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Establish the database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/utility", "root", "");

            // Prepare the SQL statement
            String query = "INSERT INTO utility_bills (electricity_units, electricity_rate, electricity_bill, " +
                    "water_units, water_rate, water_bill, gas_units, gas_rate, gas_bill, grand_total) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);

            // Set the parameter values
            statement.setDouble(1, electricityUnits);
            statement.setDouble(2, electricityRate);
            statement.setDouble(3, electricityBill);
            statement.setDouble(4, waterUnits);
            statement.setDouble(5, waterRate);
            statement.setDouble(6, waterBill);
            statement.setDouble(7, gasUnits);
            statement.setDouble(8, gasRate);
            statement.setDouble(9, gasBill);
            statement.setDouble(9, gasBill);
            statement.setDouble(10, grandTotal);

            // Execute the query
            statement.executeUpdate();

            // Close the statement and connection
            statement.close();
            connection.close();

            System.out.println("Data saved successfully to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to save data to the database.");
        }
    }
}
