import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Font;

public class Alarm extends JPanel{
	public Alarm(){
		 super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	       
	       //labels to hold time/subject/Body
	       JLabel lblTime, lblSub, lblBody;
	       lblTime = new JLabel("Time:     ");
	       lblTime.setFont(new Font("Arial", 0, 25));
	       
	       lblSub = new JLabel("Subject: ");
	       lblSub.setFont(new Font("Arial", 0, 25));
	       
	       lblBody = new JLabel("Body:     ");
	       lblBody.setFont(new Font("Arial", 0, 25));
	       
	       //Text fields for setting time for alarm
	       JTextField hField, mField, subField;
	       hField = new JTextField();
	       mField = new JTextField();
	       subField = new JTextField();
	       
	       hField.setFont(new Font("Arial", 0, 25));
	       mField.setFont(new Font("Arial", 0, 25));
	       subField.setFont(new Font("Arial", 0, 25));
	       
	       hField.setSize(100, 500);
	       
	       
	       //JTextArea for writing message for purpose of alarm
	       JTextArea bodyTextArea;
	       bodyTextArea = new JTextArea();
	       bodyTextArea.setSize(100, 100);
	       
	       //Radio button for holding am/pm option
	       JRadioButton amButton, pmButton;
	       amButton = new JRadioButton("AM");
	       pmButton = new JRadioButton("PM");
	       
	       amButton.setFont(new Font("Arial", 0, 25));
	       pmButton.setFont(new Font("Arial", 0, 25));
	       
	       ButtonGroup group = new ButtonGroup();
	       group.add(amButton);
	       group.add(pmButton);
	       
	       //add and clear buttons
	       JButton addButton, clearButton;
	       addButton = new JButton("Add");
	       clearButton = new JButton("Clear");
	       
	       addButton.setFont(new Font("Arial", 0, 25));
	       clearButton.setFont(new Font("Arial", 0, 25));
	       
	       //time container that gets added to main 
	       JPanel container1 = new JPanel();
	       container1.setLayout(new BoxLayout(container1, BoxLayout.X_AXIS));
	       container1.add(lblTime);
	       container1.add(hField);
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
	       mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
	       
	       //add/clear container
	       JPanel buttonContainer = new JPanel();
	       buttonContainer.add(addButton);
	       buttonContainer.add(clearButton);
	       
	       mainContainer.add(container1);
	       mainContainer.add(container2);
	       mainContainer.add(container3);
	       mainContainer.add(buttonContainer);
	       
	       this.add(mainContainer);
	}
}