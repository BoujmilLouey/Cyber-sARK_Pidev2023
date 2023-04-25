module com.example.pimomo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    //requires org.apache.commons.io;

    opens com.example.pimomo to javafx.fxml;
    exports com.example.pimomo;



}