import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JMenuItem;

import dorkbox.systemTray.MenuItem;
import dorkbox.systemTray.SystemTray;

public class MediaControl {
	
	public static void main(String[] args) {
		SystemTray systemTray = SystemTray.get();
		MenuItem playItem = new MenuItem("Play/Pause");
		playItem.setCallback(e -> {
			try {
				runCmd("playerctl", "play-pause");
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});
		MenuItem previousItem = new MenuItem("Previous Song");
		previousItem.setCallback(e -> {
			try {
				runCmd("playerctl", "previous");
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});
		MenuItem nextItem = new MenuItem("Next Song");
		nextItem.setCallback(e -> {
			try {
				runCmd("playerctl", "next");
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});
		systemTray.getMenu().add(playItem);
		systemTray.getMenu().add(previousItem);
		systemTray.getMenu().add(nextItem);
		new Thread(() -> {
			while(true) {
				try {
					BufferedImage play = getImage("play.png");
					BufferedImage pause = getImage("pause.png");
					systemTray.setImage((runCmd("playerctl", "status").equals("Playing") ? play : pause));
					systemTray.setStatus("Current Song: " + runCmd("playerctl", "metadata", "xesam:artist")
						+ " - " + runCmd("playerctl", "metadata", "xesam:title"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private static BufferedImage getImage(String filename) {
		try {
			return ImageIO.read(new File(Paths.get(".").toAbsolutePath().normalize().toString() + File.separator + filename));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static String runCmd(String...cmd) throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec(cmd);

		BufferedReader stdInput = new BufferedReader(new 
		     InputStreamReader(proc.getInputStream()));

		String s = null;
		StringBuilder stringBuilder = new StringBuilder();
		while ((s = stdInput.readLine()) != null) stringBuilder.append(s + "\n");
		return stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
	}

}
