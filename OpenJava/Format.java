import java.io.*;


/******************************************************************************/
public class Format extends Object
{

/******************************************************************************/


/******************************************************************************/
public Format ( )
{
}  /* constructor Format */


/******************************************************************************/
public void intWidth ( int i, int width )
{
  int spaces = spacesNeeded ( i, width );

  while ( spaces > 0 )
  {
    System.out.print ( " " );
    spaces--;
  }  // while

  System.out.print ( i );
}  // method intWidth


/******************************************************************************/
public void intWidth ( PrintStream data, int i, int width )
{
  int spaces = spacesNeeded ( i, width );

  while ( spaces > 0 )
  {
    data.print ( " " );
    spaces--;
  }  // while

  data.print ( i );
}  // method intWidth


/******************************************************************************/
private int spacesNeeded ( int i, int width )
{
  int wide = 1;

  int temp_i = i;
  while ( temp_i > 9 )
  {
    temp_i /= 10;
    wide++;
  }  // while

  return ( width - wide );
}  // method spacesNeeded


/******************************************************************************/
public void precision ( float f, int places )
{
  System.out.print ( ( (int) f ) + "." + decimal ( f, places ) );
}  // method precision


/******************************************************************************/
private int decimal ( float f, int places )
{
  float dec = f - (float) ( (int) f );

  int temp_places = places;
  while ( temp_places > 0 )
  {
    temp_places--;
    dec *= 10;
  }  // while

  return ( (int) (dec + 0.5) );
}  // method decimal


/******************************************************************************/
public void precision ( PrintStream data, float f, int places )
{
  data.print ( ( (int) f ) + "." + decimal ( f, places ) );
}  // method precision


/******************************************************************************/


/******************************************************************************/


/******************************************************************************/


/******************************************************************************/


/******************************************************************************/
public static void main ( String argv [] )
{
  Format format = new Format ();

  System.out.print ( "int with width 4 '" );
  format.intWidth ( 12, 4 );
  System.out.println ( "'" );

  System.out.print ( "float with 2, 3, & 4 places after the decimal point: '" );
  format.precision ( (float) 3.14159, 2 );
  System.out.print ( "', '" );
  format.precision ( (float) 3.14159, 3 );
  System.out.print ( "', '" );
  format.precision ( (float) 3.14159, 4 );
  System.out.println ( "'" );
}  // method main

}  /* class Format */
