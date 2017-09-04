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

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;

public class CommandParser {
    public static final String[] help = {
            "*saveQS to force save quicksends",
            "*reloadQS to reload quicksends",
            "*reset to reset the entire program (quicksets, settings, etc) USE WITH CAUTION",
            "*clearQS to clear the text in all quicksend textfields",
            "*config to open configuration menu",
            "*newline to clear the newline string OR *newline <newline> to change it",
            "*getnl to get the newline string",
            "To send a string starting with '*', use '\\' before the string",};


    public static String parse(String text){
        if(text.equals("saveQS")) {
            FileHandler.saveQuicksends();
            return "[CommandParser] Saved Quicksends\n";

        }
        else if (text.equals("reloadQS")){
            FileHandler.loadQuicksends();
            return "[CommandParser] Quicksends Reloaded\n";
        }
        else if (text.equals("reset")) {
            try {
                FileUtils.deleteDirectory(Files.home);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Handler.controller.clearQS();
            return "[CommandParser] Reset\n";
        }
        else if (text.equals("clearQS")) {
            Handler.controller.clearQS();
            return "[CommandParser] Cleared Quicksends\n";
        }
        else if (text.equals("help")){
            String g = "[CommandParser]\n";
            for (String line: help) {
                g+=line+"\n";
            }
            return g;
        }
        else if (text.equals("listPorts")){
            String g = "[CommandParser]\n";
            for(String i:SerialPortList.getPortNames()){
                g+=i+"\n";
            }
            if(g.endsWith("]\n"))
                g="[CommandParser] No ports found";
            return g;
        }
        else if (text.startsWith("newline")){
            if(text.equals("newline")) {
                Handler.config.newLine = "";
                return "[CommandParser] Cleared newline\n";
            }
            else if (text.split(" ").length == 2){
                Handler.config.newLine = text.split(" ")[1];
                return "[CommandParser] Set newline to " + Handler.config.newLine + "\n";
            }
            else {
                return "[CommandParser] Wrong syntax, use *help\n";
            }
        }
        else if (text.equals("getnl")){
            return "[CommandParser] Newline character is " + Handler.config.newLine+"\n";
        }
        else if (text.equals("open")){
            if (Handler.port.isOpened()) return "[CommandParser] Port already opened. Use *close to close it\n";
            try {
                Handler.port = new SerialPort(Handler.config.port);
                Handler.port.openPort();
                Handler.port.setParams(Handler.config.baud,Handler.config.data,Handler.config.stop,Handler.config.parity);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
            return "[CommandParser] Port Successfully opened";
        }
        else if (text.equals("config")){
            Handler.stage.setScene(Handler.configScene);
            Handler.stage.setMinHeight(340);
            Handler.stage.setMinWidth(400);
            Handler.stage.setHeight(340);
            Handler.stage.setWidth(400);
            Handler.configController.populateChoiceBoxes();
            return null;
        }
        else return "[CommandParser] Invalid Command\n";
    }
}
