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

import java.text.MessageFormat;

public class Configuration {
    public int baud = 9600;
    public int data = 8;
    public int stop = 3;
    public int parity = 0;
    public String port = "COM1";
    public String newLine = "";
    public boolean isNewline = false;


    @Override
    public String toString() {
        return MessageFormat.format("{0} {1} {2} {3} {4}", baud, data, stop, parity);
    }
}
