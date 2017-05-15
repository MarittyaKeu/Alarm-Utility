import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;

public class setAlarm extends JPanel{ // 8-31-2015
    private JTable table;
    private DefaultTableModel model;
    private String[] columnsName = {"Time", "Subject", "Body", "Sound", "Edit", "Delete"};
    private JScrollPane pane ;
    
    public setAlarm(int row){
        model = new DefaultTableModel(columnsName, row);
        table = new JTable(model){
            public boolean isCellEditable(int arg0, int arg1){
                return false;
            }           
        };
          pane = new JScrollPane(table);
          table.setValueAt("test", 0, 0);      
          add(pane);           
        }                
    }//end constructor() 