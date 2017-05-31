import javax.swing.JFrame;
import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import java.util.Date;
import java.sql.ResultSet;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//MUST run "startNetworkServer" at "/usr/lib/jvm/java-8-oracle/db/bin" to run this program


public class Main {

//	@SuppressWarnings("unused")
	public static void main(String[] args)throws SQLException {
		// TODO Auto-generated method stub
		Dimension dim = new Dimension(2200, 1600);
		JFrame frame = new JFrame("Alarm Clock");
		Date date = new Date();
		Alert alert = new Alert();
		
		
		
		
//		System.out.println(getClass().getResource("hello.wav").toString());
//	    try {
//
//	        AudioInputStream audioInputStream = 
//	        		AudioSystem.getAudioInputStream();
//	        Clip clip = AudioSystem.getClip();
//	        clip.open(audioInputStream);
//	        clip.start();
//	    } catch(Exception ex) {
//	        System.out.println("Error with playing sound.");
//	        ex.printStackTrace();
//	    }
		
		
		
		
		
		try{
			
			databaseConnection dbCon = new databaseConnection("dbAlarm", "uml", "alarmClock128");		
			dbCon.insert("OPL Summer class", "Meeting on every other sunday at 9am. go for it ", "blue.wav", date, "11:35");
//			dbCon.deleteAll();	
			alert.playSound();
			
		}catch (SQLException ex){
			ex.printStackTrace();
		}
		catch (ValueException ex){
			System.out.print(ex.getMessage());
		}
		
		
		
		
		Clock clock = new Clock();
		Cal cal = new Cal();
		Note note = new Note();
		Alarm alarm = new Alarm();
		
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();		
		JPanel tdContainer = new JPanel();
		
		container1.setLayout(new BoxLayout(container1, BoxLayout.X_AXIS));	
		container2.setLayout(new BoxLayout(container2, BoxLayout.X_AXIS));
		
		container1.add(clock);
		container1.add(cal);
		
//		container2.add(alarm);
//		container2.add(note);

		
		tdContainer.setLayout(new BoxLayout(tdContainer, BoxLayout.Y_AXIS));
		tdContainer.add(container1);
		tdContainer.add(alarm);
		
		
		frame.add(tdContainer);
		
		
		//System.out.printf("this is note parent %s\n", note.getParent().getComponents().toString());
		//frame.add(cal);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(dim);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}