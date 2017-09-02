/**
 * Copyright (C) 2017 MintSoup
 * This file is part of MintSoup's Serial Communicator.
 *
 *     MintSoup's Serial Communicator is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     MintSoup's Serial Communicator is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with MintSoup's Serial Communicator.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.mintsoup.serial;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ModifiableObservableListBase;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import jssc.SerialPort;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ConfigController {
    @FXML
    ComboBox<Integer> baud;
    @FXML
    ComboBox<Integer> data;
    @FXML
    ComboBox<Double> stop;
    @FXML
    ComboBox<String> parity;

    public void populateChoiceBoxes() {
        if(Files.config.exists()) FileHandler.loadConfig();
        Class s = SerialPort.class;
        baud.getItems().clear();
        data.getItems().clear();
        stop.getItems().clear();
        parity.getItems().clear();
        baud.getSelectionModel().clearSelection();
        data.getSelectionModel().clearSelection();
        stop.getSelectionModel().clearSelection();
        parity.getSelectionModel().clearSelection();

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
        stop.getItems().addAll(1d,2d,1.5);
        parity.getItems().addAll("None","Odd","Even","Mark","Space");
        baud.getSelectionModel().select(new Integer(Handler.config.baud));
        data.getSelectionModel().select(new Integer(Handler.config.data));
        stop.getSelectionModel().select(Handler.config.stop-1);
        parity.getSelectionModel().select(Handler.config.parity);
        System.out.println(Handler.config);


    }

    public void backToMain() {
        Handler.stage.setScene(Handler.mainScene);
        Handler.stage.setMinHeight(640);
        Handler.stage.setHeight(640);
        Handler.stage.setMinWidth(1000);
        Handler.config.baud = baud.getSelectionModel().getSelectedItem();
        Handler.config.data = data.getSelectionModel().getSelectedItem();
        Handler.config.stop = stop.getSelectionModel().getSelectedIndex()+1;
        Handler.config.parity = parity.getSelectionModel().getSelectedIndex();
        FileHandler.saveConfig();
    }
}
