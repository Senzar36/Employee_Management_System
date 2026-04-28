package Employee_Management_System;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class Main extends Application {
    private Employee_Management manager = new Employee_Management();

    @Override
    public void start(Stage stage) {
        WebView webView = new WebView();
        WebEngine engine = webView.getEngine();

        String url = getClass().getResource("/index.html").toExternalForm();
        engine.load(url);

        engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) engine.executeScript("window");
                window.setMember("javaConnector", new JSBridge(manager));
            }
        });

        stage.setScene(new Scene(webView, 1000, 700));
        stage.setTitle("Employee Management System");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}