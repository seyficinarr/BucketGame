import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

	GameFrame() {
		
		try {
			Music.runMusic("music.wav");
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.add(new BucketPanel());
		this.setTitle("Bucket Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}

}
