module com.cyt.ctqos {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires log4j;
    requires com.jfoenix;
    requires hutool.all;
    requires commons.io;


    opens com.cyt.os to javafx.fxml;
    exports com.cyt.os;
    exports com.cyt.os.kernel.process.data;
    exports com.cyt.os.kernel.process;
    exports com.cyt.os.kernel.memory.data;
    exports com.cyt.os.controller;
    opens com.cyt.os.controller to javafx.fxml;
}