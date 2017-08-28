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

public class CommandParser {
    public static final String[] help = {
            "*saveQS to force save quicksends",
            "*reloadQS to reload quicksends",
            "*reset to reset the entire program (quicksets, settings, etc) USE WITH CAUTION",
            "*clearQS to clear the text in all quicksend textfields"};


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
            Files.home.delete();
            FileHandler.controller.clearQS();
            return "[CommandParser] Reset\n";
        }
        else if (text.equals("clearQS")) {
            FileHandler.controller.clearQS();
            return "[CommandParser] Cleared Quicksends\n";
        }
        else if (text.equals("help")){
            String g = "[CommandParser]\n";
            for (String line: help) {
                g+=line+"\n";
            }
            return g;
        }
        else return "[CommandParser] Invalid Command\n";
    }
}
