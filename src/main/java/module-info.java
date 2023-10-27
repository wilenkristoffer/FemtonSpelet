module uppgift3.femtonspelet {
    requires javafx.controls;
    requires javafx.fxml;


    opens uppgift3.femtonspelet to javafx.fxml;
    exports uppgift3.femtonspelet;
}