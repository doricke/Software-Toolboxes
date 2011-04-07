
/*******************************************************************************/

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;		// Properties class
import java.math.*;
import oracle.*;

/*******************************************************************************/
class JdbcOracle extends Object
{

/*******************************************************************************/

Connection connection;
ResultSet  resultSet;


/*******************************************************************************/
public JdbcOracle ()
{
}  // constructor JdbcOracle


/*******************************************************************************/
public void connectDB ()
{
  // Set up the database username and password.
  Properties info = new Properties ();
  info.put ( "user", "ricke" );
  info.put ( "password", "ricke" );

  
  // Initialize and load the JDBC driver.
  try
  {
    DriverManager.registerDriver ( new oracle.jdbc.driver.OracleDriver () );
  }  // try
  catch ( SQLException e1 )
  {
    System.err.println ( "JdbcOracle.connectDB: DriverManager " + e1 );
  }  // catch

  // Make the connection object.
  try
  {
    connection = DriverManager.getConnection ( "jdbc:oracle:thin:@gerbera.nadi.uslj:1521:DR", info );
  }  // try
  catch ( Exception e2 )
  {
    System.err.println ( "JdbcOracle.connectDB Exception " + e2 );
  }  // catch

}  // method connectDB


/*******************************************************************************/
public Connection getConnection ()
{
  return connection;
}  // getConnection


/*******************************************************************************/
public void doSQL ( String command )
{
  // Create a simple Statement object.
  try
  {
    Statement statement = connection.createStatement ();

    // Pass the SQL command string to the DBMS and execute the SQL statement.
    int lines = statement.executeUpdate ( command );
  }  // try
  catch ( SQLException e1 )
  {
    System.err.println ( "JdbcOracle.doSQL SQLException " + e1 );
  }  // catch
}  // method doSQL


/*******************************************************************************/
public void selectSQL ( String command )
{
  // Create a simple Statement object.
  try
  {
    Statement statement = connection.createStatement ();

    // Pass the SQL command string to the DBMS and execute the SQL statement.
    resultSet = statement.executeQuery ( command );
  }  // try
  catch ( SQLException e1 )
  {
    System.err.println ( "JdbcOracle.selectSQL SQLException " + e1 );
  }  // catch
}  // method selectSQL


/*******************************************************************************/
public ResultSet getResultSet ( )
{
  return resultSet;
}  // method getResultSet


/*******************************************************************************/
private void listColumns ()
{
  String column1, column2, column3;


  System.out.println ( "Column headers" );

  try
  {
    while ( resultSet.next () )
    {
      // Get the column values into Java variables.
      column1 = resultSet.getString ( 1 );
      column2 = resultSet.getString ( 2 );
      System.out.print ( column1 + "\t" );
      System.out.print ( column2 + "\t" );
      System.out.println ( );
    }  // while
  }  // try
  catch ( SQLException e1 )
  {
    System.err.println ( "JdbcOracle.listColumns SQLException " + e1 );
  }  // catch

}  // method listColumns


/*******************************************************************************/
private void ListAll ()
{
  connectDB ();

  selectSQL ( "SELECT * FROM ProjectType" );

  listColumns ();

}  // method List All


/*******************************************************************************/
static public void main ( String argv [] )
{
  System.out.println ( "JdbcOracle started" );

  JdbcOracle test = new JdbcOracle ();
  test.ListAll ();
}  // method main

}  // class JdbcOracle
