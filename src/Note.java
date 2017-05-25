import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

//import Main.CheckBoxModelListener;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.event.TableModelListener;


public class Note extends JPanel{ // 8-31-2015
    private JTable table;
   private DefaultTableModel model;
    private String[] columnsName = {"No", "Date", "Time", "Subject", "Body", "Sound", "Delete"};
    private JScrollPane scrollPane;
    private static int count = 0;
    private boolean enableBtnDelete = false;
    databaseConnection dbCon = new databaseConnection("dbAlarm", "uml", "alarmClock128");
    
    public Note(int row) throws SQLException{
    	super.setLayout(new BorderLayout());
        model = new DefaultTableModel(columnsName, row);      
        
        
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
        
//        table.addMouseListener(new mouseAdapter());
        table.getModel().addTableModelListener(new tableModelListener());
        
       
  
        
        table.setRowHeight(35);
        //table.setLayout(new BorderLayout());
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 27));
      
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         table.setFont(new Font("Arial", Font.PLAIN, 28));      
        
         scrollPane = new JScrollPane(table);
         scrollPane.setPreferredSize(new Dimension(1400, 600));
         
         
         try{
        	 
        	 ResultSet rs = dbCon.getResultSet();
        	 int i = 0;
        	 while (rs.next()){
        		 table.setValueAt(rs.getString("ID"), i, 0);
        		 table.setValueAt(rs.getString("date"), i, 1);
        		 table.setValueAt(rs.getString("time"), i, 2);
        		 table.setValueAt(rs.getString("subject"), i, 3);
        		 table.setValueAt(rs.getString("body"), i, 4);
        		 table.setValueAt(rs.getString("sound"), i, 5);
//        		 table.setValueAt(false, 0, 6);
        		 
        		 i++;
        	 }
        	 
         }catch (SQLException ex){
        	 System.out.print(ex.getMessage());
         }     
          add(scrollPane);           
        }//end constructor() 
   public static boolean getEnable(){
	   return count > 0;
   }
    
    //trigger when checkbox is checked in cell
    private class tableModelListener implements TableModelListener{    	
    	public void tableChanged(TableModelEvent event) {
            int row = event.getLastRow();
            int col = event.getColumn();
            if (col == 6) {
                TableModel model = (TableModel) event.getSource();
                enableBtnDelete = (Boolean) model.getValueAt(row, col);  
                if (enableBtnDelete) count++; else count--;

            }
//            System.out.println(enableBtnDelete);
        }
    }//end event class
    
}//end class