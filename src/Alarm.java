import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import java.awt.Dimension;


public class Alarm extends JPanel {
	databaseConnection dbCon = new databaseConnection("dbAlarm", "uml", "alarmClock128");
//	Note note = new Note(25);
//	Note note2;
	public Alarm() throws SQLException{
		
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
	       
	      // hField.setSize(100, 500);
	       
	       
	       //JTextArea for writing message for purpose of alarm
	       JTextArea bodyTextArea;
	       bodyTextArea = new JTextArea();
	       bodyTextArea.setSize(100, 100);
	       bodyTextArea.setFont(new Font("Arial", 0, 25));
	       bodyTextArea.setLineWrap(true);
	       bodyTextArea.setWrapStyleWord(true);
	      // bodyTextArea.setColumns(5);
	       //bodyTextArea.setRows(4);
	       
	       
	       //Radio button for holding am/pm option
	       JRadioButton amButton, pmButton;
	       amButton = new JRadioButton("AM");
	       pmButton = new JRadioButton("PM");
	       
	       
	       amButton.setFont(new Font("Arial", 0, 25));
	       pmButton.setFont(new Font("Arial", 0, 25));
	       amButton.setSelected(true);
	       
	       ButtonGroup group = new ButtonGroup();
	       group.add(amButton);
	       group.add(pmButton);
	       
	       //add and clear buttons
	       JButton addButton, clearButton, btnDelete, btnEdit;
	       addButton = new JButton("Add");
	       clearButton = new JButton("Clear");
	       btnDelete = new JButton("Delete");
	       btnEdit = new JButton("Edit");
	       
	       
	       addButton.setFont(new Font("Arial", 0, 25));
	       clearButton.setFont(new Font("Arial", 0, 25));
	       btnDelete.setFont(new Font("Arial", 0, 25));
	       btnEdit.setFont(new Font("Arial", 0, 25));
	       
	       Date date = new Date();
	       Note note = new Note();
	       
	       //delete ActionListener to button
	       btnDelete.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent event){

	            	if (!note.getSelectRow()){   	
	            		JOptionPane.showMessageDialog(note, "Please select any note to delete");
	            	}else{	            		
	            		try{
	            			dbCon.deleteSQL(note.getID());
	            			note.loadTable();
	            		}catch (SQLException ex){
	            			System.out.print(ex.getMessage());
	            		}	
	            	}
	            }
	        });
	       
	       //add button action listener
	       addButton.addActionListener(new ActionListener(){
	    	   public void actionPerformed(ActionEvent event){
	    		   FormatCheck checker = new FormatCheck();
    			   if(checker.checkSubLength(subField.getText()) == false ||
    					   checker.checkTime(hField.getText(), mField.getText()) == false){
    				   JOptionPane.showMessageDialog(note, "Error: Make sure fields has valid inputs.");
	    			   }else {
			    		   try{
			    			   int adjustedTime = 0;
			    			   if(pmButton.isSelected()) {
			    				   adjustedTime = 12;
			    			   }
			    			    int newHField = Integer.parseInt(hField.getText()) + adjustedTime; 
			    			    String finalHField = Integer.toString(newHField);
			            		dbCon.insert(subField.getText(), bodyTextArea.getText(), "test.wav", date, finalHField + ":" + mField.getText());
			            		
			            	}catch (ValueException ex){
			            		System.out.print(ex.getMessage());
			            	}catch (SQLException ex){
			            		System.out.print(ex.getMessage());
			            	}
			            	note.loadTable();
			             	hField.setText("");
			    	        mField.setText("");
			    			subField.setText("");
			    			bodyTextArea.setText("");
	    			   }
	    	   }
	    	   
	       });
	       
	       //clear button action listener
	       clearButton.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent event) {
	    		   if(event.getSource() == clearButton) {
	    			   hField.setText("");
		    	        mField.setText("");
		    			subField.setText("");
		    			bodyTextArea.setText("");
	    		   }
	    	   }
	       });
	       
	       

	       btnEdit.addActionListener(new ActionListener(){
	    	   public void actionPerformed(ActionEvent event){
	    		   try{
	    			   String edit = note.getEdit();
	    			   ResultSet rs = dbCon.getResultSetEdit(note.getEdit());
	    			   rss.next();
	    			   System.out.print(rs.getString("subject"));
	    		   }catch (SQLException ex){
	    			   ex.printStackTrace();
	    		   }
	    		  
	    	   }
	    		   
	       });
	       
	       
	       
	       
	       //time container that gets added to main 
	       JPanel container1 = new JPanel();
	       container1.setLayout(new BoxLayout(container1, BoxLayout.X_AXIS));
	       container1.setMaximumSize(new Dimension(500, 100));
	       container1.add(lblTime);
	       container1.add(hField);
	       container1.add(mField);
	       container1.add(amButton);
	       container1.add(pmButton);
	       
	       
	       //subject container that gets added to main
	       JPanel container2 = new JPanel();
	       container2.setLayout(new BoxLayout(container2, BoxLayout.X_AXIS));
	       container2.setMaximumSize(new Dimension(1000, 500));
	       container2.add(lblSub);
	       container2.add(subField);
	       
	       
	       //body container that gets added to main
	       JPanel container3 = new JPanel();
	       container3.setLayout(new BoxLayout(container3, BoxLayout.X_AXIS));
	       container3.setMaximumSize(new Dimension(1000, 5000));
	       container3.add(lblBody);
	       container3.add(bodyTextArea);
	       
	       //main container
	       JPanel mainContainer = new JPanel();
	       mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
	       
	       //add/clear container
	       JPanel buttonContainer = new JPanel();
	       buttonContainer.add(addButton);
	       buttonContainer.add(clearButton);
	       buttonContainer.add(btnDelete);
	       buttonContainer.add(btnEdit);
	       
	       mainContainer.add(container1);
	       mainContainer.add(container2);
	       mainContainer.add(container3);
	       mainContainer.add(buttonContainer);
	       
	       
//	       JPanel combineContainer = new JPanel();
//	       combineContainer.setLayout(new BoxLayout(combineContainer, BoxLayout.X_AXIS));
	       this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	       this.add(mainContainer);
	       this.add(note);
	}
}







