import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {

	public static void runMusic(String s) throws LineUnavailableException {
		
		try {
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(s));
			Clip clip = AudioSystem.getClip();
			clip.open(inputStream);
			clip.loop(0);
			
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
