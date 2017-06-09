import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JPanel;
import java.sql.ResultSet;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Cal extends JPanel{
	private int month, year, date, day, lastDate, currentMonth, currentYear;//nextPreviousMonth
	private dayName dName; 
	private boolean previousBtn = false;
	private boolean nextBtn = false;
	private Calendar now;
	private ArrayList<Date> arrNoteDate = new ArrayList<>();
	private ArrayList<Date> arr; 
	
	public Cal(){
		super.setLayout(new BorderLayout());
		now = Calendar.getInstance();
		currentMonth = month = now.get(Calendar.MONTH); //return current month, start from 0 to 11 => 5
		currentYear = year = now.get(Calendar.YEAR); //return current year => 2017
		date = now.get(Calendar.DATE); //return current date => 20
		setDate();
//		System.out.printf("this is current month and year %d , %d\n", currentMonth, currentYear);
		try{
			databaseConnection dbCon = new databaseConnection("dbAlarm", "uml", "alarmClock128");
			ResultSet rs = dbCon.getResultSetOfMonth(currentMonth , currentYear);		
			 while (rs.next()){      		 
				 if (!arrNoteDate.contains(rs.getDate("date")))					
					 arrNoteDate.add(rs.getDate("date"));
			 }	 
			
		}catch (SQLException ex){
			ex.printStackTrace();
			
		}
		getArr();
	    addMouseListener(new mouseAdapter());
	    addMouseMotionListener(new mouseMotion());
//		System.out.printf("Year %d Month %d Date %d Day of week %d", year, month, date, day);
	}
	
	private void setDate(){
	
//		System.out.println(month);
		now.set(year, month, 1);
		lastDate = now.getActualMaximum(Calendar.DAY_OF_MONTH); //return the last date of month => 30
		day = now.get(Calendar.DAY_OF_WEEK); //return day (Sunday = 1 to Saturday = 7)
	}
	
	
	private  ArrayList<FullMonth<Integer, String>> getFullMonth(int dayOfFirstMonth, int lastDateOfMonth){
		ArrayList<FullMonth<Integer, String>> fMonth = new ArrayList<FullMonth<Integer, String>>();
		int i = 1;
		
		do {
			switch (dayOfFirstMonth){
				case 0: dName = dayName.SUN; break;
				case 1: dName = dayName.MON; break;
				case 2: dName = dayName.TUE; break;
				case 3: dName = dayName.WED; break;
				case 4: dName = dayName.THU; break;
				case 5: dName = dayName.FRI; break;
				default: dName = dayName.SAT;
			}
			dayOfFirstMonth = (dayOfFirstMonth + 1) % 7;
			fMonth.add(new FullMonth<Integer, String>(i, dName.getDayName()));
			i++;
		}while(i <= lastDateOfMonth); 
		return fMonth;	
	}
	

	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ArrayList<FullMonth<Integer, String>> mon = getFullMonth(day - 1, lastDate);
		int arrX[] = {50, 150, 250, 350, 450, 550, 650};
		int y = 120, j = 0;
		
		g.setFont(new Font("arial", Font.BOLD, 30));
		g.drawString(getMonthName(month).getMonthName(), 260, 70);
		g.drawString(getYear(), 505, 70);
		g.setFont(new Font("arial", Font.BOLD, 22));
		g.drawString("SUN", arrX[0], y);
		g.drawString("MON", arrX[1], y);
		g.drawString("TUE", arrX[2], y);
		g.drawString("WED", arrX[3], y);
		g.drawString("THU", arrX[4], y);
		g.drawString("FRI", arrX[5], y);
		g.drawString("SAT", arrX[6], y);
		int xNextButton[] = {660, 690, 660};
		int yNextButton[] = {50, 60, 70};
		int xPreviousButton[] = {50, 80, 80};
		int yPreviousButton[] = {60, 70, 50};
		Polygon btnNext = new Polygon(xNextButton, yNextButton, 3);
		Polygon btnPreview = new Polygon(xPreviousButton, yPreviousButton, 3);
		
		if (previousBtn) g.setColor(Color.RED); else g.setColor(Color.BLACK);
		g.fillPolygon(btnPreview);
		if (nextBtn) g.setColor(Color.RED);  else g.setColor(Color.BLACK);
		g.fillPolygon(btnNext);
				
		for (int i = 0; i < mon.size(); i++){			
			switch (mon.get(i).getDayName()){				
				case "MON" : j = 1; break;
				case "TUE" : j = 2; break;
				case "WED" : j = 3; break;
				case "THU" : j = 4; break;
				case "FRI" : j = 5; break;
				case "SAT" : j = 6; break;
				default : j = 0; if (i > 0) y += 60;			
			}
			
			Calendar cal = Calendar.getInstance();
			
			for (int n = 0; n < arr.size(); n++){
				cal.setTime(arr.get(n));
				int dbYear = cal.get(Calendar.YEAR);
				int dbMonth = cal.get(Calendar.MONTH);
				int dbDate = cal.get(Calendar.DATE);
				
				if (month == dbMonth && year == dbYear && mon.get(i).getDay() == dbDate){
					g.setColor(Color.RED);
					g.drawOval(arrX[j] - 15, y + 30, 50, 50);
//					arr.remove(n);
				}
			}

			
			if ((month == currentMonth) && (mon.get(i).getDay() == date) && (year == currentYear)){
				g.setColor(Color.RED);
				g.setFont(new Font("arial", Font.BOLD, 30));
			}else{
				g.setColor(Color.BLACK);
				g.setFont(new Font("arial", Font.PLAIN, 22));
			}
			
			g.drawString(mon.get(i).getDay().toString(), arrX[j], y + 60);
		}
	}
	
	private void getArr(){
		try{
//			String sqlDistinctDate = "SELECT DISTINCT date, time, subject, body, id from tblAlarm";
			 arr = databaseConnection.getDistinctDate();			
			 System.out.println(arr.size());
			 
		}catch (SQLException ex){
			ex.printStackTrace();
		}
		
	}
	
	
	
	private int getNextMonth(){
		return currentMonth++;
	}
	
	private int getPreviousMonth(){
		return currentMonth--;
	}
	
	private String getYear(){
		return String.format("%d", year);
	}
	
	public static String getSelectedDate(){
		return "2017/05/25";
	}
	
	private enum dayName{
		SUN(0, "SUN"), MON(1, "MON"), TUE(2, "TUE"), WED(3, "WED"), THU(4, "THU"), FRI(5, "FRI"), SAT(6, "SAT");
		
		int weekIndex; String weekName;
		
		private dayName(int index, String name){
			weekIndex = index;
			weekName = name;
		}
		
		public String getDayName(){
			return weekName;
		}
		
		public int getWeekIndex(){
			return weekIndex;
		}
	}
	
	 
	
	private enum monthName{
		JANUARY(0, "JANUARY"), FEBRUARY(1, "FEBRUARY"), MARCH(2, "MARCH"), APRIL(3, "APRIL"), 
		MAY(4, "MAY"), JUNE(5, "JUNE"), JULY(6, "JULY"), AUGUST(8, "AUGUST"), SEPTEMBER(9, "SEPTEMBER"), 
		OCTOBER(10, "OCTOBER"), NOVEMBER(11, "NOVEMBER"), DECEMBER(12, "DECEMBER");
		
		int monthIndex; String monthName;
		
		private monthName(int index, String name){
			monthIndex = index;
			monthName = name;
		}
		
		public String getMonthName(){
			return monthName;
		}
		
		public int getMonthIndex(){
			return monthIndex;
		}
		
	}
	
	private monthName getMonthName(int monthIndex){
		monthName mName;
		switch (monthIndex){
		case 0 : mName = monthName.JANUARY; break;
		case 1 : mName = monthName.FEBRUARY; break;
		case 2 : mName = monthName.MARCH; break;
		case 3 : mName = monthName.APRIL; break;
		case 4 : mName = monthName.MAY; break;
		case 5 : mName = monthName.JUNE; break;
		case 6 : mName = monthName.JULY; break;
		case 7 : mName = monthName.AUGUST; break;
		case 8 : mName = monthName.SEPTEMBER; break;
		case 9 : mName = monthName.OCTOBER; break;
		case 10 : mName = monthName.NOVEMBER; break;				
		default : mName = monthName.DECEMBER;
	}
		return mName;
	}
	
	
	private class mouseAdapter extends MouseAdapter{
	    // add the mouse release event
	    public void mouseReleased(MouseEvent event){ 
	    	 if ((event.getX() >= 50 && event.getX() <= 80) && (event.getY() >= 50 && event.getY() <= 70)){
		    	  	if (month == 0){
		    	  		month = 11;
		    	  		year--;
		    	  	}else{
		    	  		month--;
		    	  	}
		    	  	setDate();
		    	  	repaint();
		      }else if ((event.getX() >= 660 && event.getX() <= 690) && (event.getY() >= 50 && event.getY() <= 70)){
		    	  if (month == 11){
		    	  		month = 0;
		    	  		year++;
		    	  	}else{
		    	  		month++;
		    	  	}
		    	  setDate();
		    	  repaint();
		      }  
		      
	    }
	}

	
	private class mouseMotion extends MouseMotionAdapter{ 
		// add mouse move/hover motion listener
	    public void mouseMoved(MouseEvent event){
	      if ((event.getX() >= 50 && event.getX() <= 80) && (event.getY() >= 50 && event.getY() <= 70)){
	    	  	previousBtn = true; nextBtn = false;    
	    	  	
	      }else if ((event.getX() >= 660 && event.getX() <= 690) && (event.getY() >= 50 && event.getY() <= 70)){
	    	  previousBtn = false; nextBtn = true;  
	      }else{
	    	  previousBtn = false; nextBtn = false;  
	      }      
	      repaint();
	      
	    }
	    
	   
	}
	
}



