package DBManager;

import sample.ToDoItem;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Thomas on 27/02/2016.
 */
public class DBManager {


    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./Database/todoapp";


    public static void addToDo(ToDoItem toDoItem){
        if(toDoItem!=null)
            executeStmt("INSERT INTO TODOAPP (DESCRIPTION) VALUES(?);",toDoItem.getDescription().getValue());
    }

    public static void deleteToDo(ToDoItem toDoItem){
        if(toDoItem!=null)
            executeStmt("DELETE FROM TODOAPP WHERE \"DESCRIPTION\"=?;",toDoItem.getDescription().getValue());
    }

    public static void updateToDo(ToDoItem toDoItem,String newDescription){
        if(toDoItem!=null)
            executeStmt("UPDATE TODOAPP SET \"DESCRIPTION\"=? WHERE \"DESCRIPTION\"=?;",newDescription,toDoItem.getDescription().getValue());
    }

    public static ArrayList<ToDoItem> selectAllToDo(){
        Connection conn = null;
        Statement stmt = null;
        ArrayList<ToDoItem> toDoItems = new ArrayList<>();
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, "", "");

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM TODOAPP;");
            while(rs.next()){
                String description = rs.getString("DESCRIPTION");
                toDoItems.add(new ToDoItem(description));
            }
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        return toDoItems;

    }



    public static void executeStmt(String sql,String... descriptions){
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, "", "");

            //Prepare statement
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //Set first question mark in prepared statement
            preparedStatement.setString(1,descriptions[0]);
            if(descriptions.length==2)
                preparedStatement.setString(2,descriptions[1]);

            //STEP 4: Execute a query
            preparedStatement.executeUpdate();
            conn.commit();

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

    }
}
