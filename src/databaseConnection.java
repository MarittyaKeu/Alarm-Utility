import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.ZonedDateTime;
//import java.util.Calendar;
//import java.util.Date;
//import java.sql.ResultSetMetaData;
import java.sql.DatabaseMetaData;
import java.sql.Date;

public class databaseConnection {//8-25-15

	 private final String projectPath = System.getProperty("user.dir");
	 private final String userName;
	 private final String passWord; 
//	 private final String sqlDropTable = "DROP TABLE tblAlarm";
	 private final String sqlSelect = "SELECT * FROM tblAlarm";
//	 private final String sqlInsert = "INSERT INTO TBLALARM (subject, body, sound, Date, Time) VALUES ('Assignment 1', 'get it done by Saturday', 'bluesky.wav', '2017-05-10', '12:30:00')";
	 private final String sqlCreateTable = "CREATE TABLE tblAlarm ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY, subject varchar(255) NOT NULL, body varchar(255), sound varchar(100) NOT NULL, Date date,Time time, primary key (id))";
	 private Statement stmt;
	 private PreparedStatement sqlPrepare = null;
	 private final Connection dbCon;
	 private final String host;
	 
    public databaseConnection(String databaseName, String username, String password)throws SQLException {
    	userName = username;
    	passWord = password;
        host = "jdbc:derby://localhost:1527/" + projectPath + "/" + databaseName +
    		   ";create=true;user=" + username + ";/password=" + password;          
        
       dbCon = DriverManager.getConnection(host, userName, password); 
       try{	    
    	   stmt = dbCon.createStatement();	
    	   if (tableExist(dbCon) == false) stmt.executeUpdate(sqlCreateTable);
    	   
//    	   sqlPrepare = "INSERT INTO tblAlarm (subject, body, sound, Date, Time) VALUES "
//    			   + subject + "," + body + "," + sound + "," + date.toString() + ")";
            
       }catch(SQLException ex){
            ex.printStackTrace();
       }
    }
    
    
public int insertResultSet(String subject, String body, String sound, Date date, Time time)throws SQLException, ValueException{
//       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");   
//      Date date = new Date();  
//      Date sqlDate = new java.sql.Date(date.getTime());
     try{
    	 if (subject == null || sound == null || date == null || time == null) throw new ValueException();
    	   sqlPrepare = dbCon.prepareStatement("INSERT INTO tblAlarm (subject, body, sound, Date, Time) VALUES (?, ?, ?, ?, ?)");
    	   sqlPrepare.setString(1, subject);
    	   sqlPrepare.setString(2, body);
    	   sqlPrepare.setString(3, sound);
    	   sqlPrepare.setDate(4, date); //java.util.data is not the same as java.sql.date
    	   sqlPrepare.setTime(5, time);
    	   
     }catch(SQLException ex){
    	 ex.printStackTrace();
     }catch(ValueException ex){
    	 ex.getMessage();
     }
       
  	 return sqlPrepare.executeUpdate();
}
    
    
    
    
    public ResultSet getResultSet()throws SQLException{
    	ResultSet rs = null;
    	try{
    		 rs = stmt.executeQuery(sqlSelect);
    	}catch(SQLException ex){
    		ex.printStackTrace();
    	}
    	
    	return rs;
    	
    	
//    	ResultSetMetaData metaData = rs.getMetaData();
//        int columnCount = metaData.getColumnCount();
//        System.out.println(columnCount);
//        System.out.println(metaData.getTableName(1));
        
        
//        while (rs.next()){
//            
//        	System.out.printf("subject %s body %s date %s%n", rs.getString("subject"), rs.getString("body"), rs.getString("date"));
//        } 
    	
    }
    
    
    
    
    public String getDatabaseUserName(){
    	return userName;
    }
    
    public String getDatabasePassword(){
    	return passWord;
    }
    
    
    
    
    private boolean tableExist(Connection connection)throws SQLException{
    	boolean tblExist = false;
    	try{
    		 DatabaseMetaData dbMeta = connection.getMetaData();
    	 	   ResultSet rs = dbMeta.getTables(null, null, "%", null);
    	 	   while (rs.next()) {   	 		   	 	  	  
    	     	 if (rs.getString(3) == "TBLALARM") tblExist = true;   	 		   
    	 	   }
    	}catch(SQLException ex){
    		ex.printStackTrace();
    	}
 	  
    	return tblExist;
    }
    
}
















//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.sql.Time;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.ZonedDateTime;
//import java.util.Calendar;
//import java.util.Date;
//import java.sql.ResultSetMetaData;
//import java.sql.DatabaseMetaData;
//
//public class databaseConnection {//8-25-15
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws SQLException {
//       final String projectPath = System.getProperty("user.dir");
//       final String userName = "uml";
//       final String password = "alarmClock128";
//       final String host = "jdbc:derby://localhost:1527/" + projectPath + 
//    		   "/dbAlarm;create=true;user=" + userName + ";/password=" + password;
//       String subject = "300 for kim okay", body = "get for her by this weekend", sound = "fun.wav";
//       
//       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//       Date date = new Date();  
//       java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//      
//       SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
////       System.out.println(time.format(date));
//       
//       String sqlDropTable = "DROP TABLE tblAlarm";
//       String sqlSelect = "SELECT * FROM tblAlarm";
//       PreparedStatement sqlPrepare = null;
//       String sqlInsert = "INSERT INTO TBLALARM (subject, body, sound, Date, Time) VALUES ('Assignment 1', 'get it done by Saturday', 'bluesky.wav', '2017-05-10', '12:30:00')";
//       String sqlCreateTable = "CREATE TABLE tblAlarm ( id INT NOT NULL GENERATED ALWAYS AS IDENTITY, subject varchar(255) NOT NULL, body varchar(255), sound varchar(100) NOT NULL, Date date,Time time, primary key (id))";
//         
//       try (   
//            Connection dbCon = DriverManager.getConnection(host, userName, password);
//            Statement stmt = dbCon.createStatement();		
//            )
//            
//       {
//    	   boolean tblExist = false;
//    	   DatabaseMetaData dbMeta = dbCon.getMetaData();
//    	   ResultSet rs = dbMeta.getTables(null, null, "%", null);
//    	   String tblName = "";
//    	   while (rs.next()) {
//    		   
//    		   tblName = rs.getString(3).toString();
//    		   System.out.println(tblName.getClass());
//        	   String ok = "TBLALARM";
//        	   if ((String)tblName == "TBLALARM"){ System.out.printf("TBLALARM == %s", tblName );} //tblExist = true;
//    		   
//    	   }
//    	
//    		   stmt.executeUpdate(sqlDropTable);
//    	   if (tblExist == false) System.out.print(tblExist); //stmt.executeUpdate(sqlCreateTable);
//    	   
////    	   sqlPrepare = "INSERT INTO tblAlarm (subject, body, sound, Date, Time) VALUES "
////    			   + subject + "," + body + "," + sound + "," + date.toString() + ")";
//    	   
//    	   sqlPrepare = dbCon.prepareStatement("INSERT INTO tblAlarm (subject, body, sound, Date, Time) VALUES (?, ?, ?, ?, ?)");
//    	   sqlPrepare.setString(1, subject);
//    	   sqlPrepare.setString(2, body);
//    	   sqlPrepare.setString(3, sound);
//    	   sqlPrepare.setDate(4, sqlDate); //java.util.data is not the same as java.sql.date
//    	   sqlPrepare.setTime(5, null);
////    	   System.out.printf("sqlPrepare %d\n",  sqlPrepare.executeUpdate());
//    	   stmt.executeUpdate(sqlInsert);
//    	   
//    	   rs = stmt.executeQuery(sqlSelect);  
//    	   
//           ResultSetMetaData metaData = rs.getMetaData();
//           int columnCount = metaData.getColumnCount();
//           System.out.println(columnCount);
//           System.out.println(metaData.getTableName(1));
//           
//           
//           while (rs.next()){
//               System.out.printf("subject %s body %s date %s%n", rs.getString("subject"), rs.getString("body"), rs.getString("date"));
//           } 
//       
//            
//       }catch(SQLException ex){
//            ex.printStackTrace();
//       }
//    }
//    
//}
//
