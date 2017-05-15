import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

public class alarm extends JPanel{
	public alarm(){
		 super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	       
	       //labels to hold time/subject/Body
	       JLabel lblTime, lblSub, lblBody;
	       lblTime = new JLabel("Time: ");
	       lblSub = new JLabel("Subject: ");
	       lblBody = new JLabel("Body: ");
	       
	       //Text fields for setting time for alarm
	       JTextField hField, mField, colonField, subField;
	       hField = new JTextField("00");
	       colonField = new JTextField(":");
	       mField = new JTextField("00");
	       subField = new JTextField("Subject");
	       
	       //JTextArea for writing message for purpose of alarm
	       JTextArea bodyTextArea;
	       bodyTextArea = new JTextArea("Body: ");
	       bodyTextArea.setSize(100, 100);
	       
	       //Radio button for holding am/pm option
	       JRadioButton amButton, pmButton;
	       amButton = new JRadioButton("AM");
	       pmButton = new JRadioButton("PM");
	       
	       ButtonGroup group = new ButtonGroup();
	       group.add(amButton);
	       group.add(pmButton);
	       
	       //time container that gets added to main 
	       JPanel container1 = new JPanel();
	       container1.setLayout(new BoxLayout(container1, BoxLayout.X_AXIS));
	       container1.add(lblTime);
	       container1.add(hField);
	       container1.add(colonField);
	       container1.add(mField);
	       container1.add(amButton);
	       container1.add(pmButton);
	       
	       //subject container that gets added to main
	       JPanel container2 = new JPanel();
	       container2.setLayout(new BoxLayout(container2, BoxLayout.X_AXIS));
	       container2.add(lblSub);
	       container2.add(subField);
	       
	       //body container that gets added to main
	       JPanel container3 = new JPanel();
	       container3.setLayout(new BoxLayout(container3, BoxLayout.X_AXIS));
	       container3.add(lblBody);
	       container3.add(bodyTextArea);
	       
	       //main container
	       JPanel mainContainer = new JPanel();
	       mainContainer.add(container1);
	       mainContainer.add(container2);
	       mainContainer.add(container3);
	       
	       this.add(mainContainer);
	}
}
