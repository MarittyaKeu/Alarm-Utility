
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import java.awt.Dimension;

public class Alarm extends JPanel {
	databaseConnection dbCon = new databaseConnection("dbAlarm", "uml", "alarmClock128");
//	Note note = new Note(25);package src;
	
	Alert alert = new Alert();
	Date date = new Date();
    Calendar d = Calendar.getInstance();
	public Alarm() throws SQLException{
		
		 super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		alert.playSound();
		 
		 Font font = new Font("Arial", 0, 25);
	       //labels to hold time/subject/Body
	       JLabel lblTime, lblSub, lblBody, lblSound, lblYear, lblMonth, lblDate;
	       lblTime = new JLabel("Time:     ");
	       lblTime.setFont(font);
	       
	       lblSub = new JLabel("Subject: ");
	       lblSub.setFont(font);
	       
	       lblBody = new JLabel("Body:     ");
	       lblBody.setFont(font);
	       
	       lblSound = new JLabel("Sound:   ");
	       lblSound.setFont(font);
	       
	       lblYear = new JLabel("Year:");
	       lblYear.setFont(font);

	       lblMonth = new JLabel("Month:");
	       lblMonth.setFont(font);
	       
	       lblDate = new JLabel("Date:");
	       lblDate.setFont(font);
	       
	       //Text fields for setting time for alarm
	       JTextField hField, mField, subField, yearField, monthField, dateField;
	       hField = new JTextField();
	       mField = new JTextField();
	       subField = new JTextField();
	       yearField = new JTextField();
	       monthField = new JTextField();
	       dateField = new JTextField();
	    		   
	       
	       hField.setFont(font);
	       mField.setFont(font);
	       subField.setFont(font);
	       yearField.setFont(font);
	       monthField.setFont(font);
	       dateField.setFont(font);
	       
	      // hField.setSize(100, 500);
	       
	       
	       //JTextArea for writing message for purpose of alarm
	       JTextArea bodyTextArea;
	       bodyTextArea = new JTextArea();
	       bodyTextArea.setSize(100, 100);
	       bodyTextArea.setFont(new Font("Arial", 0, 25));
	       bodyTextArea.setLineWrap(true);
	       bodyTextArea.setWrapStyleWord(true);
	       
	       JScrollPane scroll = new JScrollPane(bodyTextArea);
	       
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
	       JButton addButton, clearButton, deleteButton, editButton, updateButton;
	       addButton = new JButton("Add");
	       clearButton = new JButton("Clear");
	       deleteButton = new JButton("Delete");
	       editButton = new JButton("Edit");
	       updateButton = new JButton("Update");
	       updateButton.setEnabled(false);
	       
	       //set fonts of button
	       addButton.setFont(new Font("Arial", 0, 25));
	       clearButton.setFont(new Font("Arial", 0, 25));
	       deleteButton.setFont(new Font("Arial", 0, 25));
	       editButton.setFont(new Font("Arial", 0, 25));
	       updateButton.setFont(new Font("Arial", 0, 25));
	       
	       
	 
	       
	       Note note = new Note();
	       
	       String[] soundList = {"update.wav", "test.wav", "blue.wav"};
	       JComboBox soundMenu = new JComboBox(soundList);
	       soundMenu.setSelectedIndex(2);
	       soundMenu.setFont(new Font("Arial", 0, 25));
	       soundMenu.setMaximumSize(new Dimension(300,300));
	       
	       //delete ActionListener to button
	       deleteButton.addActionListener(new ActionListener(){
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
			    			   if(pmButton.isSelected() && Integer.parseInt(hField.getText()) < 12) {
			    				   adjustedTime = 12;
			    			   }
			    			    int newHField = Integer.parseInt(hField.getText()) + adjustedTime; 
			    			    String finalHField = Integer.toString(newHField);
//			    			    
			    			      d.set(Integer.parseInt(yearField.getText()), 
			    			    		   Integer.parseInt(monthField.getText()) - 1, 
			    			    		   Integer.parseInt(dateField.getText()));
			    			       date = d.getTime();
			    			    
			    			    
			            		dbCon.insert(subField.getText(), bodyTextArea.getText(), (String)soundMenu.getSelectedItem(), date, 
			            				finalHField + ":" + mField.getText());
			            		Clock.setArrDate(); //reset the array from the database after adding new note
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
			    			yearField.setText("");
			    			monthField.setText("");
			    			dateField.setText("");
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
		    			yearField.setText("");
		    			monthField.setText("");
		    			dateField.setText("");
	    		      }
	    	    }
	       });
	       
	     
	       
	       updateButton.addActionListener(new ActionListener(){
	    	   public void actionPerformed(ActionEvent event){
	    		   FormatCheck checker = new FormatCheck();
    			   if(checker.checkSubLength(subField.getText()) == false ||
    					   checker.checkTime(hField.getText(), mField.getText()) == false){
    				   JOptionPane.showMessageDialog(note, "Error: Make sure fields has valid inputs.");
    			   }else{
		    		   try {
		    			   int adjustedTime = 0;
		    			   if(pmButton.isSelected() && Integer.parseInt(hField.getText()) < 12) {
		    				   adjustedTime = 12;
		    			   }
		    			    int newHField = Integer.parseInt(hField.getText()) + adjustedTime; 
		    			    String finalHField = Integer.toString(newHField);
		    			  
		    			      d.set(Integer.parseInt(yearField.getText()), 
		    			    		   Integer.parseInt(monthField.getText()) - 1, 
		    			    		   Integer.parseInt(dateField.getText()));
		    			       date = d.getTime();
		    			    
		    			    dbCon.updateNote(subField.getText(), bodyTextArea.getText(), (String)soundMenu.getSelectedItem(), date, finalHField
		    					   + ":" + mField.getText(), note.getEdit());
		    		   }catch (SQLException ex) {
		    			   	ex.printStackTrace();
		    	   		}
	    		   }
	    		note.loadTable();
	    		hField.setText("");
	    	    mField.setText("");
	    		subField.setText("");
	    		bodyTextArea.setText("");
	    		updateButton.setEnabled(false);
	    	   }
	       });

	       editButton.addActionListener(new ActionListener(){
	    	   public void actionPerformed(ActionEvent event){

	    		   try{	    			   
	    			   

	    			   String edit = note.getEdit();
	    			   ResultSet rs = dbCon.getResultSetEdit(note.getEdit());
	    			   String hour, minute;
	    			   
	    			   rs.next();
	    			   yearField.setText((String) rs.getString("date").subSequence(0, 4));
	    			   monthField.setText((String) rs.getString("date").subSequence(5, 7));
	    			   dateField.setText((String) rs.getString("date").subSequence(8, 10));
	    			   mField.setText((String) rs.getString("time").subSequence(3, 5));
	    			   hField.setText((String) rs.getString("time").subSequence(0, 2));
	    			   subField.setText(rs.getString("subject"));
	    			   bodyTextArea.setText(rs.getString("body"));	    			   
	    			   updateButton.setEnabled(true);

	    		   }catch (SQLException ex){
	    			   ex.printStackTrace();
	    		   }  
	    	   }
	    		   
	       });
	       
	       
	       
	       JPanel container0 = new JPanel();
	       container0.setLayout(new BoxLayout(container0, BoxLayout.X_AXIS));
	       container0.setMaximumSize(new Dimension(800, 100));
	       container0.add(lblYear);
	       container0.add(yearField);
	       container0.add(lblMonth);
	       container0.add(monthField);
	       container0.add(lblDate);
	       container0.add(dateField);
	       
	       
	       //time container that gets added to main 
	       JPanel container1 = new JPanel();
	       container1.setLayout(new BoxLayout(container1, BoxLayout.X_AXIS));
	       container1.setMaximumSize(new Dimension(800, 100));	       
	       container1.add(lblTime);
	       container1.add(hField);
	       container1.add(mField);
	       container1.add(amButton);
	       container1.add(pmButton);
	       
	       
	       //subject container that gets added to main
	       JPanel container2 = new JPanel();
	       container2.setLayout(new BoxLayout(container2, BoxLayout.X_AXIS));
	       container2.setMaximumSize(new Dimension(800, 500));
	       container2.add(lblSub);
	       container2.add(subField);
	       
	       JPanel soundContainer = new JPanel();
	       soundContainer.setLayout(new BoxLayout(soundContainer, BoxLayout.X_AXIS));
	       soundContainer.setMaximumSize(new Dimension(300, 1000));
	       soundContainer.setAlignmentX(RIGHT_ALIGNMENT);
	       soundContainer.add(lblSound);
	       soundContainer.add(soundMenu);
	       
	       
	       //body container that gets added to main
	       JPanel container3 = new JPanel();
	       container3.setLayout(new BoxLayout(container3, BoxLayout.X_AXIS));
	       container3.setMaximumSize(new Dimension(800, 5000));
	       container3.add(lblBody);
	       container3.add(scroll);
	       
	       //main container
	       JPanel mainContainer = new JPanel();
	       mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
	       
	       //add/clear container
	       JPanel buttonContainer = new JPanel();
	       buttonContainer.add(addButton);
	       buttonContainer.add(clearButton);
	       buttonContainer.add(deleteButton);
	       buttonContainer.add(editButton);
	       buttonContainer.add(updateButton);
	       
	       mainContainer.setAlignmentX(RIGHT_ALIGNMENT);
	       mainContainer.add(container0);
	       mainContainer.add(container1);
	       mainContainer.add(container2);
	       mainContainer.add(soundContainer);
	       mainContainer.add(container3);
	       mainContainer.add(buttonContainer);
	       
	       
	       
//	       JPanel combineContainer = new JPanel();
//	       combineContainer.setLayout(new BoxLayout(combineContainer, BoxLayout.X_AXIS));
	       this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	       this.add(mainContainer);
	       this.add(note);
	}
}








