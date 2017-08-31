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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jssc.SerialPort;

import java.lang.reflect.Field;

public class Controller {
    @FXML
    TextArea area;
    @FXML
    TextField input;
    @FXML
    TextField qs0;
    @FXML
    TextField qs1;
    @FXML
    TextField qs2;
    @FXML
    TextField qs3;
    @FXML
    TextField qs4;
    @FXML
    TextField qs5;
    @FXML
    TextField qs6;
    @FXML
    TextField qs7;
    @FXML
    TextField qs8;
    @FXML
    TextField qs9;
    @FXML
    TextField qs10;
    @FXML
    TextField qs11;
    @FXML
    TextField qs12;
    @FXML
    TextField qs13;
    @FXML
    TextField qs14;
    @FXML
    TextField qs15;
    @FXML
    TextField qs16;

    public void execute(String text){
        if(text.isEmpty()) return;
        if(text.startsWith("*")){
            if(!text.equals("*config"))area.appendText(CommandParser.parse(text.substring(1,text.length())));
            else CommandParser.parse(text.substring(1,text.length()));
            return;
        }
        if(text.startsWith("\\")){
            area.appendText(text.substring(1)+'\n');
            return;
        }
        area.appendText(text + "\n");

    }
    public void clearQS(){
        Class s = getClass();
        for(int i=0;i<17;i++){
            try {
                Field f = s.getDeclaredField("qs"+i);
                TextField tf = (TextField) f.get(this);
                tf.clear();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

        }
    }

    public void sendMain() {
        execute(input.getText());
        input.clear();

    }
    public void appendText(String text){
        area.appendText(text);
    }

    public void s0 () {
        execute(qs0.getText());
    }
    public void s1() {
        execute(qs1.getText());
    }
    public void s2() {
        execute(qs2.getText());
    }
    public void s3() {
        execute(qs3.getText());
    }
    public void s4() {
        execute(qs4.getText());
    }
    public void s5() {
        execute(qs5.getText());
    }
    public void s6() {
        execute(qs6.getText());
    }
    public void s7() {
        execute(qs7.getText());
    }
    public void s8() {
        execute(qs8.getText());
    }
    public void s9() {
        execute(qs9.getText());
    }
    public void s10() {
        execute(qs10.getText());
    }
    public void s11() {
        execute(qs11.getText());
    }
    public void s12() {
        execute(qs12.getText());
    }
    public void s13() {
        execute(qs13.getText());
    }
    public void s14() {
        execute(qs14.getText());
    }
    public void s15() {
        execute(qs15.getText());
    }
    public void s16(){
        execute(qs16.getText());
    }

}
