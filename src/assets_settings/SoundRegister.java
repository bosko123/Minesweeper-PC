package assets_settings;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundRegister {
	
	public static void playSound(String path, float volume) {
		
		try {
			
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(SoundRegister.class.getResource(path)));
			clip.setMicrosecondPosition(2000000);
			FloatControl floatControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			floatControl.setValue(volume);
			clip.start();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
}
