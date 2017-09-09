# Serial
A nice little serial communicator written in java
<p>
So probably you've seen many programs used for serial communication and they're great and all, but all (or most of them) have these common flaws:
<br>Ugly UIs: All of the serial communication programs I know have REALLY ugly looking UIs
<br>Windows only: Not cross platform, so if you're used to it/used it on a friends computer or something and you like it, or just aren't running windows, this can be frustrating
<br>Sometimes hard to use: Some serial communicator programs just aren't really beginner friendly
<br> and maybe some more I didn't mention
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
