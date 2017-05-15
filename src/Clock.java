import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Timer;
import java.util.TimerTask;
import java.awt.Font;



public class Clock extends JPanel{
	double sx = 250, sy = 50, mx = 250, my = 50, hx = 250, hy = 50;
	double sAngle = 84, mAngle = 89.9, hAngle = 89.998333333;
	int sr = 200, mr = 140, hr = 120;
	public Clock(){
		super.setLayout(new BorderLayout());
		int speed = 1000;	
		ActionListener actionTask = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				double sTheta = (sAngle * (Math.PI / 180));
				double mTheta = (mAngle * (Math.PI / 180));
				double hTheta = (hAngle * (Math.PI / 180));
				
				sx = sr * Math.cos(sTheta) + 250;
				sy = 250 - (sr * Math.sin(sTheta));
				
				mx = mr * Math.cos(mTheta) + 250;
				my = 250 - (mr * Math.sin(mTheta));
				
				hx = hx * Math.cos(hTheta) + 250;
				hy = 250 - (hr * Math.sin(hTheta));
				
				//System.out.printf("h %d, theta %f, ox %f, ay %f angle %d sx %f sy %f\n", sr, sTheta, sy, sx, sAngle, sx, sy);
				sAngle -= 6;
				mAngle -= 0.1;
				if (sAngle == -270) sAngle = 90;
				
				
				
				repaint();
			}
		};
		
		Timer timer = new Timer(speed, actionTask);
		timer.start();
		
		
		
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setFont(new Font("Arial", 1, 25));
		g.drawArc(50, 50, sr * 2 , sr * 2, 0, 360);
		
		g.drawLine(250, 250, (int)sx, (int)sy);
		g.drawLine(250, 250, (int)mx, (int)my);
		g.drawLine(250, 250, (int)hx, (int)hy);
		g.drawString("12", 235, 70);
		
	}
	
	
}
