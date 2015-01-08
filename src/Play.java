import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Play extends Thread {
	private volatile boolean mFinish = false;
	private Clip clip;
	private volatile String pwd = "data/"; 

	public void finish() {
		mFinish = true;
	}

	public void changeRadioStation(int key){
		String path;
		if(clip != null)
			clip.stop();
		switch (key) {
		case 89:
			path = new String(pwd + "t1.wav");
			break;
		case 92:
			path = new String(pwd + "t2.wav");
			break;
		case 98:
			path = new String(pwd + "t3.wav");
			break;
		case 104:
			path = new String(pwd + "t4.wav");
			break;
		case 106:
			path = new String(pwd + "t5.wav");
			break;
		default:
			path = new String(pwd + "t0.wav");
			break;
		}
		try {
			File file = new File(path);
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	@Override
	public void run() {
		do {
			if (!mFinish) {

			} else {
				clip.stop();
				return;
			}
		} while (true);
	}
}
