import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import javax.swing.Timer;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



public class Clock extends JPanel{
	private double sx = 250, sy = 50, mx = 250, my = 50, hx = 250, hy = 50;
	private double sAngle = 84, mAngle = 89.9, hAngle = 89.9983333333333;
	private final double sAnglePerSecond = 6, mAnglePerSecond = 0.1, hAnglePerSecond = 0.008333333333;
	private final int sr = 200, mr = 150, hr = 100;
	private static ArrayList<DateTime<Date, Time, String, String, Integer>> arr;
	
	public Clock()throws SQLException{
		super.setLayout(new BorderLayout());
		int speed = 1000;	
		Date date = new Date();
		
		
//		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
//		System.out.print(timeStamp);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);  
		int hours = cal.get(Calendar.HOUR);
		int minutes = cal.get(Calendar.MINUTE);
		int seconds = cal.get(Calendar.SECOND);
		sAngle -= (seconds * sAnglePerSecond);
		mAngle -= ((minutes * 6) + (seconds * mAnglePerSecond)); // (minutes * 60 * 0.1) 
//		hAngle -= (hours * 3600 * hAnglePerSecond) + ((minutes * 6) + (seconds * hAnglePerSecond)); 
		hAngle -= (hours * 3600 + minutes * 60 + seconds) * hAnglePerSecond;
		
		setArrDate();
		ticking();
		ActionListener actionTask = new ActionListener(){
			public void actionPerformed(ActionEvent event){				
				ticking();			
				Calendar cal = Calendar.getInstance();
				Alert alert = new Alert();
				int nowYear = cal.get(Calendar.YEAR);
				int nowMonth = cal.get(Calendar.MONTH);
				int nowDate = cal.get(Calendar.DATE);
				int nowHour = cal.get(Calendar.HOUR_OF_DAY);
				int nowMinute = cal.get(Calendar.MINUTE);
				int nowSecond = cal.get(Calendar.SECOND);
				long time = ((nowHour * 60) + nowMinute) * 60000 + 18000000;
				//one minute is 60000, 18000000 is the base number from java;
				 
				if (nowSecond == 0){
					Calendar dbCal = Calendar.getInstance();
					
					for (int n = 0; n < arr.size(); n++){
						dbCal.setTime(arr.get(n).getDate());
						int dbYear = dbCal.get(Calendar.YEAR);
						int dbMonth = dbCal.get(Calendar.MONTH);
						int dbDate = dbCal.get(Calendar.DATE);
						
						if (nowYear == dbYear && nowMonth == dbMonth && nowDate == dbDate){
							if (time == arr.get(n).getTime().getTime()){
								System.out.println("you got it");
								alert.playSound();
								JOptionPane.showMessageDialog(null, arr.get(n).getMessage(),
										arr.get(n).getSubject(), JOptionPane.INFORMATION_MESSAGE);
							}
							System.out.printf("hour %s, m %s date %s\n", nowHour, nowMinute, arr.get(n).getTime().getTime());
							System.out.printf("this is outside time %s dbtime %s dbFulltime %s\n", time, arr.get(n).getTime().getTime(), arr.get(n).getTime());

						}
						
					}
					System.out.println("-----");
				}
				
				
				
				
				repaint();
			}
		};
		
		Timer timer = new Timer(speed, actionTask);
		timer.start();
	}
	
	
	public static void setArrDate()throws SQLException{
		 arr = databaseConnection.getResetSetArr();
		
	}
	
	
	
	private void ticking(){
		double sTheta = (sAngle * (Math.PI / 180));
		double mTheta = (mAngle * (Math.PI / 180));
		double hTheta = (hAngle * (Math.PI / 180));
		
		sx = sr * Math.cos(sTheta) + 250;
		sy = 250 - (sr * Math.sin(sTheta));
		
		mx = mr * Math.cos(mTheta) + 250;
		my = 250 - (mr * Math.sin(mTheta));
		
		hx = hr * Math.cos(hTheta) + 250;
		hy = 250 - (hr * Math.sin(hTheta));
		
		//System.out.printf("h %d, theta %f, ox %f, ay %f angle %d sx %f sy %f\n", sr, sTheta, sy, sx, sAngle, sx, sy);
		sAngle -= sAnglePerSecond; 
		mAngle -= mAnglePerSecond; //360 / (60 * 12) = 0.5
		hAngle -= hAnglePerSecond; //60 * 60 * 12 there are 43200seconds for 12 hours
		if (sAngle == -270) sAngle = 90;
		
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setFont(new Font("Arial", 1, 25));
		g.drawArc(50, 50, sr * 2 , sr * 2, 0, 360);
		
		g.drawLine(250, 250, (int)sx, (int)sy);
		g.drawLine(250, 250, (int)mx, (int)my);
		g.setColor(Color.RED);
		g.drawLine(250, 250, (int)hx, (int)hy);
		g.setColor(Color.BLACK);
		g.drawString("12", 233, 75);
		g.drawString("1", 330, 105);
		g.drawString("2", 400, 170);
		g.drawString("3", 425, 260);
		g.drawString("4", 400, 350);
		g.drawString("5", 330, 413);
		g.drawString("6", 242, 440);
		g.drawString("7", 154, 413);
		g.drawString("8", 84, 350);
		g.drawString("9", 59, 260);
		g.drawString("10", 84, 170);
		g.drawString("11", 150, 105);
		
		
		
	}
	
	
}
