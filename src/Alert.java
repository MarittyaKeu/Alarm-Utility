import java.applet.AudioClip;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;






public class Alert {
	
	String path = "C:\\Users\\sokth\\Desktop\\UML\\workspace\\AlarmClock\\audios\\morning.wav";
	AudioInputStream audioInputStream;
	Clip clip;
	public Alert(){
		
		
	}
	
	public void playSound() {
		
		
	    try {
	        
	    	audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	
	public void stopSound(){
		
		if (clip.isRunning() == true) 
		System.out.println("this is stop sound is running");
		
		clip.close();
		clip.stop();
	}
	
	
}
