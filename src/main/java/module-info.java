module pl.polsl.dbtester {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens pl.polsl.dbtester to javafx.fxml, org.hibernate.orm.core;
    opens pl.polsl.dbtester.entity to org.hibernate.orm.core;
    exports pl.polsl.dbtester;
}