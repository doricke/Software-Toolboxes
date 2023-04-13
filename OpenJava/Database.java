import java.io.*;
import java.sql.*;
import java.util.*;			// Properties class
import oracle.*;

/*******************************************************************************/
public class Database
{

  private static String database_name = "LJ";		// Database name
  // private static String database_name = "LJDEV";		// Database name

  private static String computer_name = "petunia";	// Computer name

  private static String socket_port = "1521";		// Database socket port

/*******************************************************************************/

private Connection  connection;		// JDBC database connection

/*******************************************************************************/
public Database ()
{
  clear ();
}  // constructor Database


/*******************************************************************************/
public Database ( String comp_name, String db_name )
{
  computer_name = comp_name;

  database_name = db_name;

  clear ();
}  // constructor Database


/*******************************************************************************/
// This method clears the parameters.
public void clear ()
{
  connection = null;
}  // method clear


/*******************************************************************************/
public Connection getConnection ()
{
  return connection;
}  // method getConnection


/*******************************************************************************/
public void setDriver ( String driver )
{
  System.out.println ( "JDBC driver: " + driver );

  // Initialize and load the JDBC driver.
  try
  {
    Class.forName ( driver );
  }  // try
  catch ( ClassNotFoundException e1 )
  {
    System.err.println ( "Database.setDriver: ClassNotFoundExcpeption " + e1 );
  }  // catch
}  // setDriver


/*******************************************************************************/
public void connectDB ( String database, Properties info )
{
  System.out.println ( "Connecting to database: " + database );

  // Make the connection object.
  try
  {
    connection = DriverManager.getConnection ( database, info );
  }  // try
  catch ( Exception e2 )
  {
    System.err.println ( "Database.connectDB Exception " + e2 );
    System.err.println ( "Database.connectDB " + e2.getMessage() );
    System.exit ( 0 );
  }  // catch
}  // connectDB


/*******************************************************************************/
public void connectDB_oracle ()
{
  // Set up the database username and password.
  Properties info = new Properties ();
  info.put ( "user", "biosphere" );
  info.put ( "password", "l0adup" );

  try
  {
    DriverManager.registerDriver ( new oracle.jdbc.driver.OracleDriver () );
  }
  catch ( SQLException e1 )
  {
    System.err.println ( "Database: SQLException " + e1 );
  }  // catch

  connectDB ( "jdbc:oracle:thin:@" + computer_name + ":" + socket_port + ":" +
      database_name, info );
}  // method connectDB


/*******************************************************************************/
public void connectDB ()
{
  connectDB_oracle ();
  if ( true ) return;

  setDriver ( "rst.sql.Driver" );

  // Set up the database username and password.
  Properties info = new Properties ();
  info.put ( "user", "ricke" );
  info.put ( "password", "" );

  connectDB ( "jdbc:rst://pansy.nadi.uslj:5432/seq;debug=2", info );
}  // method connectDB


/*******************************************************************************/
public void disconnectDB ()
{
  try
  {
    connection.close ();
  }
  catch ( Exception e )
  {
    System.err.println ( "Database.disconnectDB Exception " + e );
    System.err.println ( "Database.disconnectDB " + e.getMessage () );
  }  // catch
}  // method disconnectDB


/*******************************************************************************/
static public void main ( String argv [] )
{
  Database test = new Database ();

  test.connectDB ();
  test.disconnectDB ();
}  // method main

}  // class Database

/*******************************************************************************/

