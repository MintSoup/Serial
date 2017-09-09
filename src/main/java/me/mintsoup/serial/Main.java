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

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    //for testing purposes
    public static boolean test = false;

    @Override
    public void stop() throws Exception {
        Files.home.mkdirs();
        if (!test) FileHandler.saveQuicksends();
        if (!test) FileHandler.saveConfig();
        super.stop();
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader l = new FXMLLoader(getClass().getResource("/ui.fxml"));
        Parent root = l.load();
        FXMLLoader l2 = new FXMLLoader(getClass().getResource("/config.fxml"));
        Parent config = l2.load();
        Handler.configController = l2.getController();
        Handler.controller = l.getController();
        //      primaryStage.setTitle("MintSoup's serial communicator" +(!test?"":" [TEST MODE]"));
        primaryStage.setTitle("MintSoup's serial communicator [DEV]");
        primaryStage.setMinHeight(640);
        primaryStage.setMinWidth(720);
        Handler.mainScene = new Scene(root, 1000, 600);
        Handler.configScene = new Scene(config, 400, 620);
        primaryStage.setScene(Handler.mainScene);
        primaryStage.show();
        Files.home.mkdirs();
        Handler.config = new Configuration();
        if (Files.quicksends.exists()) FileHandler.loadQuicksends();
        if (Files.config.exists()) FileHandler.loadConfig();
        Handler.stage = primaryStage;
        if (Handler.updateTheme() != 0) {
            System.err.print("Could not find the stylesheet, using the default theme");
            Handler.config.theme = "~";
            Handler.updateTheme();
        }

        if (test) System.err.println("!!!!!!!!!!TEST MODE IS ON!!!!!!!!!!");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
