/**
 * Copyright (C) 2017 Areg Hovhannisyan
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

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import jssc.SerialPort;
import jssc.SerialPortList;

import java.lang.reflect.Field;

public class ConfigController {
    @FXML
    ComboBox<Integer> baud;
    @FXML
    ComboBox<Integer> data;
    @FXML
    ComboBox<Double> stop;
    @FXML
    ComboBox<String> parity;
    @FXML
    ComboBox<String> port;

    public void populateChoiceBoxes() {
        if(Files.config.exists()) FileHandler.loadConfig();
        Class s = SerialPort.class;
        baud.getItems().clear();
        data.getItems().clear();
        stop.getItems().clear();
        parity.getItems().clear();
        port.getItems().clear();
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
        port.getItems().addAll(SerialPortList.getPortNames());

        baud.getSelectionModel().select(new Integer(Handler.config.baud));
        data.getSelectionModel().select(new Integer(Handler.config.data));
        stop.getSelectionModel().select(Handler.config.stop-1);
        parity.getSelectionModel().select(Handler.config.parity);
        for(String i: SerialPortList.getPortNames()) {
            if (i.equals(Handler.config.port)) {
                port.getSelectionModel().select(Handler.config.port);
            }
        }


    }

    public void backToMain() {
        Handler.stage.setScene(Handler.mainScene);
        Handler.stage.setMinHeight(640);
        Handler.stage.setHeight(640);
        Handler.stage.setMinWidth(720);
        Handler.stage.setWidth(1000);
        Handler.config.baud = baud.getSelectionModel().getSelectedItem();
        Handler.config.data = data.getSelectionModel().getSelectedItem();
        Handler.config.stop = stop.getSelectionModel().getSelectedIndex()+1;
        Handler.config.parity = parity.getSelectionModel().getSelectedIndex();
        Handler.config.port = port.getSelectionModel().getSelectedItem();
        if(Main.save)FileHandler.saveConfig();
    }
}
