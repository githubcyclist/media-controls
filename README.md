# media-controls
A simple frontend for playerctl written in Java

## Installation
Installation is simple.

First, install playerctl (on Ubuntu, simply run `sudo apt install playerctl`).

Then, clone the repo to your computer, then 
copy "media-control", "pause.png", and "play.png" into /usr/bin.
To do that, run this command from the root folder of the repo:

`cd app && sudo cp ./media-control /usr/bin && sudo cp pause.png /usr/bin && sudo cp play.png /usr/bin`

Then run it with `media-control`.

If you don't like to copy files to /usr/bin, just run it from any folder with `./media-control`. Just make sure the play.png and pause.png are in the same folder as the binary.

## Tinkering
Replace play.png and pause.png with your own icons to make the program match your style.

Or if you want to add features to it, follow these steps to set up the program in Eclipse:
1. Clone the repo to your computer, if you haven't already.
2. Rename the repo folder to "MediaControl" to prevent errors in Eclipse.
3. In Eclipse, click File, then "Import..."
4. Open the "Gradle" folder, then select "Existing Gradle Project".
5. Click "Next" if the "How to experience the best Gradle integration" wizard opens.
6. Click "Browse..." next to "Project root directory", then press "Finish".
7. You're done!

## Credits
Thanks to Dorkbox for making his awesome System Tray (https://github.com/dorkbox/systemtray), which I used in this project for the system tray icon.
