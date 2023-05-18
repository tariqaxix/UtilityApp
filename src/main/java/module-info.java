module com.example.electricitybill {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.UtilityBill to javafx.fxml;
    exports com.example.UtilityBill;
}