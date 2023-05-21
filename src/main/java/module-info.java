module pl.polsl.dbtester {
    exports pl.polsl.dbtester.entity;

    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires opencsv;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires java.persistence;


    opens pl.polsl.dbtester to javafx.fxml, org.hibernate.orm.core;
    opens pl.polsl.dbtester.entity to org.hibernate.orm.core;
    exports pl.polsl.dbtester;
}