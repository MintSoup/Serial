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

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;

public class CommandParser {
    public static final char cmd = '-';
    public static final String[] help = {
            "open to open connection and -close to close it",
            "saveQS to force save quicksends",
            "reloadQS to reload quicksends",
            "reset to reset the entire program (quicksets, settings, etc) USE WITH CAUTION",
            "clearQS to clear the text in all quicksend textfields",
            "config to open configuration menu",
            "suffix to clear the suffix string OR " + cmd + "suffix <ascii0> <ascii1>,etc to change it. Use" + cmd + "suffix -1 to change the suffix to \\n",
            "gets to get the suffix string",
            "outfile to choose an output file, all the input will be written in to this file.",
            "theme <themeFile> to choose a theme. Theme files are located in ~/.mintsoup/serial/. All theme files should have a css format, example included\nUse " + cmd + "theme ~ to use the default theme\nFor more information, visit the project's github repo (http://github.com/MintSoup/Serial)",
            "To send a string starting with " + cmd + ", use '|' before the string",};


    public static String parse(String text) {
        if (text.equals("saveQS")) {
            FileHandler.saveQuicksends();
            return "[CommandParser] Saved Quicksends\n";

        } else if (text.equals("reloadQS")) {
            FileHandler.loadQuicksends();
            return "[CommandParser] Quicksends Reloaded\n";
        } else if (text.equals("reset")) {
            try {
                FileUtils.deleteDirectory(Files.home);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Handler.controller.clearQS();
            return "[CommandParser] Reset\n";
        } else if (text.equals("clearQS")) {
            Handler.controller.clearQS();
            return "[CommandParser] Cleared Quicksends\n";
        } else if (text.equals("help")) {
            String g = "[CommandParser]\n";
            for (String line : help) {
                g += cmd + line + "\n";
            }
            return g;
        } else if (text.equals("listPorts")) {
            String g = "[CommandParser]\n";
            for (String i : SerialPortList.getPortNames()) {
                g += i + "\n";
            }
            if (g.endsWith("]\n"))
                g = "[CommandParser] No ports found";
            return g;
        } else if (text.startsWith("suffix")) {
            if (text.equals("suffix")) {
                Handler.config.newLine = "";
                Handler.config.isNewline = false;
                return "[CommandParser] Cleared the suffix\n";
            } else if (text.split(" ").length > 1) {
                if (text.split(" ")[1].equals("-1")) {
                    Handler.config.newLine = "\n";
                    Handler.config.isNewline = true;
                    return "[CommandParser] Changed the suffix to the newline character\n";
                }
                try {
                    Handler.config.newLine = "";
                    for (int i = 1; i < text.split(" ").length; i++) {
                        Handler.config.newLine += (char) Integer.parseInt(text.split(" ")[i]);
                    }
                    Handler.config.isNewline = false;

                } catch (NumberFormatException e) {
                    return "[CommandParser] Wrong syntax, use" + cmd + "help\n";
                }
                return "[CommandParser] Set the suffix to " + Handler.config.newLine + "\n";
            } else {
                return "[CommandParser] Wrong syntax, use" + cmd + "help\n";
            }
        } else if (text.equals("gets")) {
            return "[CommandParser] The suffix is " + Handler.config.newLine + "\n";
        } else if (text.equals("open")) {
            if (Handler.port != null) return "[CommandParser] Port already opened. Use " + cmd + "close to close it\n";
            try {
                Handler.port = new SerialPort(Handler.config.port);
                Handler.port.openPort();
                Handler.port.setParams(Handler.config.baud, Handler.config.data, Handler.config.stop, Handler.config.parity);
                Handler.port.addEventListener(Handler.controller);
            } catch (SerialPortException e) {
                Handler.port = null;
                return "[CommandParser] Couldn't open the port\n";
            }
            return "[CommandParser] Port Successfully opened on " + Handler.port.getPortName() + "\n";
        } else if (text.equals("config")) {
            Handler.stage.setScene(Handler.configScene);
            Handler.stage.setMinHeight(340);
            Handler.stage.setMinWidth(400);
            Handler.stage.setHeight(340);
            Handler.stage.setWidth(400);
            Handler.configController.populateChoiceBoxes();
            return null;
        } else if (text.equals("close")) {
            try {
                Handler.port.closePort();
                Handler.port = null;
                return "[CommandParser] OK";
            } catch (SerialPortException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                return "[CommandParser] Port not opened yet\n";
            }
            return "Could not close the port, probably busy :(\n";

        } else if (text.equals("outfile")) {
            FileChooser fc = new FileChooser();
            fc.setTitle("Choose file");
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All files", "*.*"));
            Files.output = fc.showSaveDialog(new Stage());//Dont kill me
            FileWriter pw = null;
            if (Files.output.exists()) {

                try {
                    pw = new FileWriter(Files.output);
                    pw.close();
                } catch (IOException e) {
                    System.err.println("Could not write to file, output in stderr. Submit to dev (github.com/MintSoup/Serial) if you think this is a bug");

                }
            }
            return "";
        } else if (text.startsWith("theme")) {
            String[] data = text.split(" ");
            if (data.length == 1) return "[CommandParser] Invalid Syntax. Use " + cmd + "help\n";
            Handler.config.theme = data[1];
            if (Handler.updateTheme() != 0) return "[CommandParser] Could not find that theme\n";
            return "[CommandParser] Set the theme to " + data[1] + '\n';
        } else return "[CommandParser] Invalid Command\n";
    }
}
