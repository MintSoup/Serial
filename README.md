# MintSoup's Serial program thingy
A nice little serial communicator written in java
<p>
So probably you've seen many programs used for serial communication and they're great and all, but all (or most of them) have these common flaws:
<br>Ugly UIs: All of the serial communication programs I know have REALLY ugly looking UIs
<br>Windows only: Not cross platform, so if you're used to it/used it on a friends computer or something and you like it, or just aren't running windows, this can be frustrating
<br>Sometimes hard to use: Some serial communicator programs just aren't really beginner friendly.
<br>And maybe some other downfalls that can cause annoyance.
</p>
<p>So, in order to adress all of these issues, I created my own serial program! It's written in java so it's cross-platform, it comes with an AWESOME default dark theme, but you can change themes or even create your own!
and, it's so easy to use my grandma could use it!!!!!(not really)<br>

# Building/Running
Clone the repo, and then open a termial/shell/command prompt/call it whatever you want in the root directory of the project. Next, type "gradlew run" if you're on windows, or "./gradlew run" if you're using powershell or on a unix system.
<br> If you want a jar version, use gradlew shadowJar (or ./gradlew shadowJar) and wait for the build to complete. Next, go to build/libs and you should have a jar file containing everything you need to run this program (Except JRE of course)<br>
<p>

# Using
<p>
Ok, so lets have a look at the main screen<br>
<img src=https://github.com/MintSoup/Serial/blob/master/main.PNG?raw=true><br>
As you can see, we have 3 main areas: Quicksends area, Output area and input area.
<br><p>
<h3> Quicksends area</h3>
This is very simple: As you can see we have 17 (I think too lazy to count) text fields and buttons next to each one of them. Typing
anything in these text fields and pressing the send button next to it will have the same effect has copying the text in the textfield to the input area's textfield and then pressing the send button next to it. So essentially if there are some commands/strings that you send often you can keep them in these textfields for quick access. Note that these text fields are auto-saved when quitting the program so the next time you open it you'll have the same quicksends as the last time you used the program
</p>
<p>
<h3> Output area</h3>
Really not much to it here. This is just a big old text area where all the input you get will be displayed.
</p>
<p>
<h3>Input area</h3>
Anything you type in here will be sent over the serial port when you press the send button or enter key. Simple.
</p>
So now you kinda now how to use this program, but not really. I mean you can't even open a connection. Which brings us to 

# Commands
<p> Commands are special strings that you type into the input field and send. Commands are used to configure the program or open/close ports, etc. Try typing <code>-help</code> in the input field and send it. You should be greeted by a text in your output field which contains a lot of useful information about commands. Most commands are self explanatory, but I will explain a few of them, just in case. (Protip: Put common commands (<code>-config</code>, <code>-open</code>, <code>-close,</code>etc) in the quicksends so you don't have to type them out every time you want to use them.)</p>
 
 <h4>-config</h4>
 If you type <code>-config</code> in the input field and press enter, you will be greeted by the configuration screen, where you can select all your serial settings (baudrate, data bits, etc). Once you're done configuring these, click the HUGE OK button. If you can't find it you need to consult a doctor.
 
 <h4>-outfile</h4>
 Running this command will open up a file chooser menu. Once you select your file, all the input from the serial port will be displayed on the output text area as well as be written to this file.
 
<h4>-suffix</h4>
This is used to configure a string which will be sent after you text every time you send something. use <code>-suffix</code> to clear the suffix, or use <code>-suffix -1</code> to set it to newline. Or to change the suffix to whatever you want, use <code>-suffix</code> followed by the ASCII codes of your characters, seperated by spaces. For example <code>-suffix 79 75</code> will change the suffix to OK.

<h4>-theme</h4>
Use this to change the theme. Running <code>-theme ~</code> will change the theme to the default super-duper awesome dark theme and running <code>-theme MODENA</code> whill change the theme to JavaFX's default MODENA theme which is OK but not great. Or if you want to use your custom themes type <code>-theme <ThemeName></code>. All the theme files are located in ~/.mintsoup/serial/ and have a .css format. If you have run the program at least once, you will even see the CSS of the default theme in a file called example-theme.css in the home folder of this program (~/.mintsoup/serial). You can use this to create your own theme by changing some values. Also, since this is standard JavaFX css, you can use other javafx properties. The css classes are named in a way so it will be easy to identify them (<code>.button</code>, .<code>textarea</code>, <code>.textfield</code>, etc). <br>Once you're done creating your theme, save it under a different filename or save it under the default filename and then rename it. NEVER use the default example-theme.css filename because this file is overwritten every time the program starts, so every time you use the program this file will get reset, which means if you had a theme in there it would be gone. Don't want that.
<p>
All the other commands' help can be found in the the program itself and can be viewed by using the <code>-help</code> command.
 </p>
Other than that the program is pretty easy to use. And by the way, if you really liked the program, please consider<a href="http://paypal.me/mintsoup">donating through paypal</a>
