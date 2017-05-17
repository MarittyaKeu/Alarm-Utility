import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class Note extends JPanel{ // 8-31-2015
    private JTable table;
   private DefaultTableModel model;
    private String[] columnsName = {"No", "Date", "Time", "Subject", "Body", "Sound", "Delete"};
    private JScrollPane scrollPane;
    
    public Note(int row){
    	super.setLayout(new BorderLayout());
        model = new DefaultTableModel(columnsName, row);
        
        table = new JTable(model){      	
            public boolean isCellEditable(int row, int col){
                if (col == 5) return true; 
                	else return false;            	
            }          
            
//            DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();
//            DefaultTableCellRenderer renderLeft = new DefaultTableCellRenderer();
//
//            { // initializer block
//                renderRight.setHorizontalAlignment(SwingConstants.RIGHT);
//                renderLeft.setHorizontalAlignment(SwingConstants.CENTER);
//            }
//            
//            @Override
//            public TableCellRenderer getCellRenderer (int row, int col) {
//            	if (col == 0) return renderRight;
//            		else return null;
//            }
                     
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
        
        //System.out.print(table.getRowCount());
       
  
        
        table.setRowHeight(35);
        //table.setLayout(new BorderLayout());
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 27));
      
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         table.setFont(new Font("Arial", Font.PLAIN, 28));      
        
         scrollPane = new JScrollPane(table);
         scrollPane.setPreferredSize(new Dimension(1400, 600));
         
         table.setValueAt(999, 0, 0); 
         table.setValueAt("05/14/2017", 0, 1); 
          table.setValueAt("12:30 PM", 0, 2); 
          table.setValueAt("Go to School", 0, 3);
          table.setValueAt("There is a quiz on Friday", 0, 4);
          table.setValueAt("sky.wav", 0, 5);
          table.setValueAt(true, 0, 6);
          
          add(scrollPane);           
        }          
    
    
    
    
    
    
    
    }//end constructor() 