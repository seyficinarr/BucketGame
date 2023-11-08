import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BucketPanel extends JPanel implements ActionListener {

	BufferedImage dropImage;
	BufferedImage bucketImage;

	private ArrayList<Drop> Drops = new ArrayList<Drop>();
	private int bucketX = SCREEN_WIDTH / 2;
	private final int bucketY = 0;
	static final int SCREEN_WIDTH = 1200;
	static final int SCREEN_HEIGHT = 750;
	private int score;
	private double timeSpent;
	Random random;
	static final int DELAY = 50;
	Timer timer = new Timer(DELAY, this);
	private int bucketDirX = 30;
	private int dropDirY = 10;
	private int count = 0;

	class Drop {
		private int x;
		private int y;

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public Drop(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public BucketPanel() {
		
		

		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.GREEN);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());

		try {
			bucketImage = ImageIO.read(new FileImageInputStream(new File("bucket.png")));
			dropImage = ImageIO.read(new FileImageInputStream(new File("drop.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setBackground(Color.GRAY);

		timer.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timeSpent += 50;
		for (Drop drop : Drops) {
			drop.setY(drop.getY() + dropDirY + drop.getY() / 35);
		}

		

		count++;
		repaint();

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		draw(g);

	}


	private void draw(Graphics g) {

		for (Drop drop : Drops) {

			/*
			 * if (new Rectangle(drop.getX(), drop.getY(), dropImage.getWidth(),
			 * dropImage.getHeight()) .intersects(new Rectangle(bucketX, bucketY,
			 * bucketImage.getWidth(), bucketImage.getHeight()))) { score++;
			 * 
			 * }
			 */

			if (drop.getX() > bucketX && drop.getX() < bucketX + bucketImage.getWidth() && drop.getY() < SCREEN_HEIGHT
					&& drop.getY() > SCREEN_HEIGHT - bucketImage.getHeight()) {
				try {
					Music.runMusic("drop.wav");
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				score++;
				Drops.remove(drop);

			}

		}

		if (count % 20 == 0) {

			

			int dropX = random.nextInt(SCREEN_WIDTH);
			Drops.add(new Drop(dropX, 0));
		}
		
		if(count % 500 == 0) {
			try {
				Music.runMusic("music.wav");
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

		for (Drop drop : Drops) {
			g.drawImage(dropImage, drop.getX(), drop.getY(), dropImage.getWidth() / 2, dropImage.getHeight() / 2, this);

		}

		g.drawImage(bucketImage, bucketX, SCREEN_HEIGHT - bucketImage.getHeight(), bucketImage.getWidth(),
				bucketImage.getHeight(), this);

		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 30));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Score: " + score, (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, 25);
		g.drawString("Time Spent: " + timeSpent / 1000 + " seconds", 0, 25);

	}

	public class MyKeyAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {

			case KeyEvent.VK_LEFT:
				if (bucketX > 0) {
					bucketX -= bucketDirX;
				}
				break;

			case KeyEvent.VK_RIGHT:
				if (bucketX < SCREEN_WIDTH - bucketImage.getWidth()) {
					bucketX += bucketDirX;
				}
				break;

			}

		}

	}
	
	

}
