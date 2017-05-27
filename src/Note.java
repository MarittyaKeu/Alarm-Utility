import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
//import Main.CheckBoxModelListener;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.event.TableModelListener;


public class Note extends JPanel{ // 8-31-2015
    private static JTable table;
    private static DefaultTableModel model;
    private String[] columnsName = {"No", "Date", "Time", "Subject", "Body", "Sound", "Delete"};
    private static JScrollPane scrollPane;
    private static int count = 0;
    private boolean enableBtnDelete = false;
    private databaseConnection dbCon = new databaseConnection("dbAlarm", "uml", "alarmClock128");
    private ArrayList<String> id = new ArrayList<>();
    
    
    public Note() throws SQLException{
    	super.setLayout(new BorderLayout());
    	
  	   model = new DefaultTableModel(columnsName, 25);   
  	   table = new JTable(model){      	
             public boolean isCellEditable(int row, int col){
             	try{
             		if (row < dbCon.getSize() &&  col == 6) return true;               	 
             	}catch(SQLException ex){
             		ex.printStackTrace();
             	}
                 return false;          	
             }        
             
             public Class<?> getColumnClass(int column) {
                 switch (column) {
                 	case 0: return Integer.class;
                     case 1: return String.class;
                     case 2: return String.class;
                     case 3: return String.class;
                     case 4: return String.class;
                     case 5: return String.class;
                     default: return Boolean.class;
                 }
             }         
             
         };
         
         
         table.getColumnModel().getColumn(0).setPreferredWidth(35);
         table.getColumnModel().getColumn(1).setPreferredWidth(150);
         table.getColumnModel().getColumn(2).setPreferredWidth(120);
         table.getColumnModel().getColumn(3).setPreferredWidth(250);
         table.getColumnModel().getColumn(4).setPreferredWidth(450);
         table.getColumnModel().getColumn(5).setPreferredWidth(150);
         table.getColumnModel().getColumn(6).setPreferredWidth(50);
         
//         table.addMouseListener(new mouseAdapter());
         table.getModel().addTableModelListener(new tableModelListener());
         
        
         
         
         table.setRowHeight(35);
         //table.setLayout(new BorderLayout());
         table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 27));
       
         //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
          table.setFont(new Font("Arial", Font.PLAIN, 28));  
          
          scrollPane = new JScrollPane(table);
          scrollPane.setPreferredSize(new Dimension(1400, 600));
          loadTable();
         
                             
        add(scrollPane);            
        }//end constructor() 
    
    
   public  void loadTable(){
	   
	   try{  	 
		 model.setRowCount(25);
		 int row = dbCon.getSize();  
		 if (row > 25) model.setRowCount(row);
		 System.out.printf("row count %d", row);
      	 ResultSet rs = dbCon.getResultSet();
      	 int i = 0;
      	 while (rs.next()){
      		 table.setValueAt(rs.getString("ID"), i, 0);
      		 table.setValueAt(rs.getString("date"), i, 1);
      		 table.setValueAt(rs.getString("time"), i, 2);
      		 table.setValueAt(rs.getString("subject"), i, 3);
      		 table.setValueAt(rs.getString("body"), i, 4);
      		 table.setValueAt(rs.getString("sound"), i, 5);
      		 table.setValueAt(false, i, 6);	 
      		 i++;
      	 }
      	 
      	 for (int j = i; j < model.getRowCount(); j++){
      		table.setValueAt("", j, 0);
      		table.setValueAt("", j, 1);
      		table.setValueAt("", j, 2);
      		table.setValueAt("", j, 3);
      		table.setValueAt("", j, 4);
      		table.setValueAt("", j, 5);
      		table.setValueAt(false, j, 6);	 
      	 }
      	 count = 0;
      	 System.out.printf("getrowcount() %d,", model.getRowCount());
       }catch (SQLException ex){
      	 System.out.print(ex.getMessage());
       }
	
   }
   
   
   
   
//   public void deleteNote(){
//	   try{
//		   int row = dbCon.getSize();
//		   if (row > 25) model.setRowCount(row);
//		   for (int i = 0; i < model.getRowCount(); i++){
//			   table.setValueAt("0", i, 0);
//			   table.setValueAt("0", i, 1);
//			   table.setValueAt("0", i, 2);
//			   table.setValueAt("0", i, 3);
//			   table.setValueAt("0", i, 4);
//			   table.setValueAt("0", i, 5);	
//		   }
//		   		addNote();
//		   
//	   }catch (SQLException ex){
//		   ex.printStackTrace();
//	   }
//	   
//
//}
    
   public boolean getSelectRow(){
	   return count > 0;
   }
   
   public String[] getID(){
	   String[] ids = new String[id.size()];
	   for (int i = 0; i < id.size(); i++){
		   ids[i] = (String)id.get(i);
	   }
	  return ids;
   }
    
    //trigger when checkbox is checked in cell
    private class tableModelListener implements TableModelListener{    	
    	public void tableChanged(TableModelEvent event) {
            int row = event.getLastRow();
            int col = event.getColumn();
            if (col == 6) {
                TableModel model = (TableModel) event.getSource();
                enableBtnDelete = (Boolean) model.getValueAt(row, col);  
                if (enableBtnDelete){
                	count++; 
                	
                	id.add((String)model.getValueAt(row, 0));
                }else{
                	count--;
                	//do the deletion here
                }

            }
//            System.out.println(enableBtnDelete);
        }
    }//end event class
    
}//end class