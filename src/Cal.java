import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JPanel;

public class Cal extends JPanel{
	private int month, year, date, day, lastDate, npMonth; //nextPreviousMonth
	private dayName dName; 
	
	
	public Cal(){
		super.setLayout(new BorderLayout());
		Calendar now = Calendar.getInstance();
		npMonth = month = now.get(Calendar.MONTH); //return month
		year = now.get(Calendar.YEAR); //return year
		date = now.get(Calendar.DATE); //return date
		lastDate = now.getActualMaximum(Calendar.DAY_OF_MONTH); //return the last day of month
		now.set(year, month, 1);
		day = now.get(Calendar.DAY_OF_WEEK); //return day (Sunday = 1 to Saturday = 7)
		
		System.out.printf("Year %d Month %d Date %d Day of week %d", year, month, date, day);
	}
	
	
	private  ArrayList<FullMonth<Integer, String>> getFullMonth(int day, int lastMonth){
		ArrayList<FullMonth<Integer, String>> fMonth = new ArrayList<FullMonth<Integer, String>>();
		int i = 1;
		
		do {
			switch (day){
				case 0: dName = dayName.SUN; break;
				case 1: dName = dayName.MON; break;
				case 2: dName = dayName.TUE; break;
				case 3: dName = dayName.WED; break;
				case 4: dName = dayName.THU; break;
				case 5: dName = dayName.FRI; break;
				default: dName = dayName.SAT;
			}
			day = (day + 1) % 7;
			fMonth.add(new FullMonth<Integer, String>(i, dName.getDayName()));
			i++;
		}while(i <= lastMonth); 
		return fMonth;	
	}
	

	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ArrayList<FullMonth<Integer, String>> mon = getFullMonth(day - 1, lastDate);
		int arrX[] = {50, 150, 250, 350, 450, 550, 650};
		int y = 120, j = 0;
		
		g.setFont(new Font("arial", Font.PLAIN, 22));
		g.drawString(getMonthName(month).getMonthName(), 325, 70);
		g.drawString("SUN", arrX[0], y);
		g.drawString("MON", arrX[1], y);
		g.drawString("TUE", arrX[2], y);
		g.drawString("WED", arrX[3], y);
		g.drawString("THU", arrX[4], y);
		g.drawString("FRI", arrX[5], y);
		g.drawString("SAT", arrX[6], y);
				
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
			
			if ((month == npMonth) && (date == mon.get(i).getDay())){
				g.setColor(Color.RED);
				g.setFont(new Font("arial", Font.BOLD, 25));
			}else{
				g.setColor(Color.BLACK);
				g.setFont(new Font("arial", Font.PLAIN, 22));
			}
			
			g.drawString(mon.get(i).getDay().toString(), arrX[j], y + 60);
		}
	}
	
	private int getNextMonth(){
		return npMonth++;
	}
	
	private int getPreviousMonth(){
		return npMonth--;
	}
	
	
	
	
	public enum dayName{
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
	
	 
	
	public enum monthName{
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
	
}
