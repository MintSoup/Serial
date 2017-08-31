package me.mintsoup.serial;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ModifiableObservableListBase;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import jssc.SerialPort;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ConfigController {
    @FXML
    ChoiceBox<Integer> baud;
    @FXML
    ChoiceBox<Integer> data;
    @FXML
    ChoiceBox<Double> stop;
    @FXML
    ChoiceBox<String> parity;

    public void populateChoiceBoxes() {
        Class s = SerialPort.class;

        try {
            for (Field f : s.getFields()) {
                if (f.getName().startsWith("BAUDRATE")) {
                    baud.getItems().add(f.getInt(Handler.port));
                }
                if(f.getName().startsWith("DATABITS")){
                    data.getItems().addAll(f.getInt(Handler.port));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    public void backToMain() {
        Handler.stage.setScene(Handler.mainScene);
        Handler.stage.setMinHeight(640);
        Handler.stage.setHeight(640);
    }
}
