/**
 * Copyright (C) 2017 MintSoup
 * This file is part of MintSoup's Serial Communicator.
 * <p>
 * MintSoup's Serial Communicator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * MintSoup's Serial Communicator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with MintSoup's Serial Communicator.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.mintsoup.serial;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;

import java.io.File;

public class Handler {
    public static SerialPort port;
    public static Configuration config;
    public static Stage stage;
    public static Parent root;
    public static Scene mainScene;
    public static Scene configScene;
    public static Controller controller;
    public static ConfigController configController;

    public static int updateTheme() {

        if (config.theme.equals("dark")) {
            clearThemes();
            mainScene.getStylesheets().add(CommandParser.class.getResource("/default.css").toExternalForm());
            configScene.getStylesheets().add(CommandParser.class.getResource("/default.css").toExternalForm());
        } else if(config.theme.equals("light")) {
            clearThemes();
            mainScene.getStylesheets().add(CommandParser.class.getResource("/light.css").toExternalForm());
            configScene.getStylesheets().add(CommandParser.class.getResource("/light.css").toExternalForm());
        }else if (config.theme.equals("MODENA")) {
            clearThemes();
            mainScene.getStylesheets().add("MODENA");
            configScene.getStylesheets().add("MODENA");
        } else {
            File css = new File(Files.home, config.theme + ".css");
            if (!css.exists() || css.isDirectory()) return 1;
            clearThemes();

            mainScene.getStylesheets().add(css.toURI().toString());
            configScene.getStylesheets().add(css.toURI().toString());
        }
        return 0;
    }

    private static void clearThemes() {
        mainScene.getStylesheets().clear();
        configScene.getStylesheets().clear();
    }
}
