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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class FileHandler {
    public static Controller controller;

    public static void saveQuicksends() {
        try {

            Class controllerClass = Class.forName("me.mintsoup.serial.Controller");
            ArrayList<String> qs = new ArrayList<String>();
            for (int i = 0; i < 17; i++) {
                Field f = controllerClass.getDeclaredField("qs" + i);
                f.setAccessible(true);
                TextField tf = (TextField) f.get(controller);
                qs.add(tf.getText());
            }
            Gson g = new Gson();
            FileWriter quicksends = new FileWriter(Files.quicksends);
            JsonWriter jsonWriter = new JsonWriter(quicksends);
            g.toJson(qs, new TypeToken<ArrayList<String>>() {
            }.getType(), jsonWriter);
            jsonWriter.close();
            quicksends.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void loadQuicksends() {
        try {

            Gson g = new Gson();
            FileReader quicksends = new FileReader(Files.quicksends);
            ArrayList<String> qs = g.fromJson(quicksends, new TypeToken<ArrayList<String>>() {
            }.getType());
            quicksends.close();
            Class controllerClass = Class.forName("me.mintsoup.serial.Controller");

            for (int i = 0; i < 17; i++) {
                Field f = controllerClass.getDeclaredField("qs" + i);
                f.setAccessible(true);
                TextField tf = (TextField) f.get(controller);
                tf.setText(qs.get(i));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
