module fri.uv.dn04 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens fri.uv.dn04 to javafx.fxml;
    exports fri.uv.dn04;
}