import javax.swing.JFrame;
import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.util.Date;
import java.sql.ResultSet;

//MUST run "startNetworkServer" at "/usr/lib/jvm/java-8-oracle/db/bin" to run this program


public class Main {

//	@SuppressWarnings("unused")
	public static void main(String[] args)throws SQLException {
		// TODO Auto-generated method stub
		Dimension dim = new Dimension(2200, 1500);
		JFrame frame = new JFrame("Alarm Clock");
		Date date = new Date();
		String id[] = {"1081, 10119"};
		int rowNumber = 25;
		
		try{

			databaseConnection dbCon = new databaseConnection("dbAlarm", "uml", "alarmClock128");		
			dbCon.insert("OPL Summer class", "Meeting on every other sunday at 9am", "blue.wav", date, "7:35");
//			dbCon.deleteAll();
			if (dbCon.getSize() > 25) rowNumber = dbCon.getSize();		
//			System.out.printf("getSize %d", dbCon.getSize());
			
		}catch (SQLException ex){
			ex.printStackTrace();
		}
		catch (ValueException ex){
			System.out.print(ex.getMessage());
		}
		
		
		
		Clock clock = new Clock();
		Cal cal = new Cal();
		Note note = new Note(rowNumber);
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