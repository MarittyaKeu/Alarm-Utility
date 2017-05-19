import javax.swing.JFrame;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.BoxLayout;


public class Main {

	public static void main(String[] args)throws SQLException {
		// TODO Auto-generated method stub
		Dimension dim = new Dimension(1500, 1200);
		JFrame frame = new JFrame("Alarm Clock");
		databaseConnection dbCon = new databaseConnection("dbAlarm", "uml", "alarmClock128");
		
		
		
		Clock clock = new Clock();
		Cal cal = new Cal();
		Note note = new Note(50);
		Alarm alarm = new Alarm();
		
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();		
		JPanel tdContainer = new JPanel();
		
		container1.setLayout(new BoxLayout(container1, BoxLayout.X_AXIS));	
		container2.setLayout(new BoxLayout(container2, BoxLayout.X_AXIS));
		
		container1.add(clock);
		container1.add(cal);
		
		container1.add(alarm);
		container2.add(note);

		
		tdContainer.setLayout(new BoxLayout(tdContainer, BoxLayout.Y_AXIS));
		tdContainer.add(container1);
		tdContainer.add(container2);
		
		
		frame.add(tdContainer);
		
		//frame.add(cal);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(dim);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}