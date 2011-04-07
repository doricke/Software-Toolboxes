import java.io.*;
import Format.*;


/******************************************************************************/
public class Histogram extends Object
{

/******************************************************************************/

protected int goodMinimum;		// Minimum good value

protected float insertSize;		// Estimated size of insert

protected int maxRange;			// Upper limit on the size range

protected String name;			// Histogram name

protected int num_good_values;		// Number of good values

protected int num_values;		// Number of values in histogram

protected int range;			// Range size for each bin

protected int rangeLength;		// Length of the range bins

protected float total;			// Sum of values added to histogram

protected int total_good;		// Sum of good values

protected int values [];		// Histogram values


/******************************************************************************/
public Histogram ( )
{
  Init ();
}  /* constructor Histogram */


/******************************************************************************/
public void reset ( String histogram_name, int bin, int maximum )
{
  Init ();
  name = histogram_name;
  range = bin;
  maxRange = maximum;
  rangeLength = (maxRange / range) + 2;	// room for zero & maximum

  // Check for final partial range.
  if ( maxRange > (rangeLength - 2) * range )  rangeLength++;

  values = new int [ rangeLength ];

  // Initialize the histogram range values.
  for ( int i = 0; i < rangeLength; i++ )
    values [ i ] = 0;
}  /* method Setup */


/******************************************************************************/
public int getNumberOfRanges ( )
{
  return rangeLength;
}  /* method getNumberOfRanges */


/******************************************************************************/
public Histogram ( String histogram_name, int bin, int maximum )
{
  reset ( histogram_name, bin, maximum );
}  /* constructor Histogram */


/******************************************************************************/
public Histogram ( String histogram_name, int bin, int maximum, 
    int good_minimum )
{
  reset ( histogram_name, bin, maximum );

  goodMinimum = good_minimum;
}  /* constructor Histogram */


/******************************************************************************/
public Histogram ( String histogram_name, int bin, int maximum, 
    int good_minimum, int insert_size )
{
  reset ( histogram_name, bin, maximum );

  goodMinimum = good_minimum;
  insertSize  = (float) insert_size;
}  /* constructor Histogram */


/******************************************************************************/
private void Init ( )
{
  goodMinimum = 0;
  insertSize  = 0;
  maxRange    = 0;
  name        = "";
  num_good_values = 0;
  num_values  = 0;
  range       = 0;
  rangeLength = 0;
  total       = 0;
  total_good  = 0;
}  /* method Init */


/******************************************************************************/
/* This constructor sets up the ParseName Object from a Phred/Phrap sequence name. */
public void addValue ( int value )
{
  num_values++;

  /* Check if value is below the histogram range. */
  if ( value <= 0 )
  {
    values [ 0 ]++;
    return;
  }  /* if */

  total += value;		// Sum all values > zero

  // Check for value greater than maximum for histogram range.
  if ( value > maxRange )
  {
    values [ rangeLength - 1 ]++;
    return;
  }  /* if */
  else
    // Value in histogram range.
    values [ ( (value - 1) / range ) + 1 ]++;

  // Check if value is above the good minimum.
  if ( value >= goodMinimum )
  {
    num_good_values++;
    total_good += value;
  }  /* if */
}  /* method addValue */


/******************************************************************************/
public String [] getRangeNames ( )
{
  String [ ] names = new String [ rangeLength ];

  for ( int i = 0; i <= rangeLength - 1; i++ )
  {
    if ( i == 0 )
      names [ i ] = names [ i ].valueOf (i * range);
    else
      names [ i ] = names [ i ].valueOf ( ( (i * range) + ((i-1) * range) ) / 2 );
  }  /* for */

  return names;
}  /* method getRangeNames */


/******************************************************************************/
public int [] getRangeValues ( )
{
  return values;
}  /* method getRangeValues */


/******************************************************************************/
/* This method prints out the histogram data. */
public void printHistogram ( )
{
  Format format = new Format ();

  System.out.println ( );
  System.out.println ( "Histogram " + name + " clear read lengths" );
  System.out.println ( );

  System.out.println ( "    <=    0: \t" );
  format.intWidth ( values [ 0 ], 4 );
  System.out.println ( );

  for ( int i = 1; i < rangeLength - 1; i++ )
  {
    format.intWidth ( (1 + (i - 1) * range), 4 );
    System.out.print ( "-" );
    format.intWidth ( (i * range), 4 );
    System.out.print ( ": \t" );
    format.intWidth ( values [ i ], 4 );
    System.out.println ( );
  }  /* for */

  System.out.print ( "    >=" );
  format.intWidth ( maxRange, 4 );
  System.out.print ( ": \t" );
  format.intWidth ( values [ rangeLength - 1 ], 4 );
  System.out.println ( );

  System.out.println ( );
  System.out.println ( "Total: \t\t" + (int) total );  
  System.out.println ( "Number: \t" + num_values );

  if ( num_values > 0 )
    System.out.println ( "Average: \t" + (int) (total / num_values) );

  // Check if a good minimum was specified. 
  if ( goodMinimum > 0 )
  {
    System.out.println ( );
    System.out.println ( "Minimum good value: \t" + goodMinimum );
    System.out.println ( "Good Total: \t\t" + total_good );  
    System.out.println ( "Good Number: \t\t" + num_good_values );

    if ( num_good_values > 0 )
      System.out.println ( "Good Average: \t\t" + (int) (total_good / num_good_values) );
  }  /* if */

  // Check if an insert size was specified.
  if ( insertSize > 0 )
  {
    System.out.println ( );
    System.out.println ( "Insert size: \t\t" + insertSize );

    System.out.print ( "Total coverage: \t" );
    format.precision ( (total / insertSize), 1 );
    System.out.println ( );

    System.out.print ( "Good  coverage: \t" + (total_good / insertSize) );
    format.precision ( (total_good / insertSize), 1 );
    System.out.println ( );

    System.out.print ( "Total seqs/kb: \t\t" );
    format.precision ( (num_values/(insertSize/1000)), 1 );
    System.out.println ( );

    System.out.print ( "Good  seqs/kb: \t\t" );
    format.precision ( (num_good_values/(insertSize/1000)), 1 );
    System.out.println ( );
  }  /* if */
}  /* method printHistogram */


/******************************************************************************/
/* This method prints out the histogram data. */
public void printHistogram ( PrintStream html_data )
{
  double cummulative = num_values;
  double percent = 100;
  Format format = new Format ();

  if ( num_values <= 0 )  return;

  html_data.println ( );
  html_data.println ( "<H1>Histogram " + name + " clear read lengths</H1>" );
  html_data.println ( );
  html_data.println ( "   Range\tNumber\tPercent\tCummulative" );

  html_data.print ( "   <=   0: \t" );
  format.intWidth ( html_data, values [ 0 ], 4 );
  html_data.print ( "\t" );
  percent = (values [ 0 ] * 100) / num_values;
  format.intWidth ( html_data, (int) percent, 3 );
  html_data.print ( "%\t" );
  percent = (cummulative * 100) / num_values;
  cummulative -= values [ 0 ];
  format.intWidth ( html_data, (int) percent, 3 );
  html_data.println ( "%" );

  for ( int i = 1; i < rangeLength - 1; i++ )
  {
    format.intWidth ( html_data, (1 + (i - 1) * range), 4 );
    html_data.print ( "-" );
    format.intWidth ( html_data, (i * range), 4 );
    html_data.print ( ": \t" );
    format.intWidth ( html_data, values [ i ], 4 );
    html_data.print ( "\t" );
    percent = (values [ i ] * 100) / num_values;
    format.intWidth ( html_data, (int) percent, 3 );
    html_data.print ( "%\t" );
    percent = (cummulative * 100) / num_values;
    cummulative -= values [ i ];
    format.intWidth ( html_data, (int) percent, 3 );
    html_data.println ( "%" );
  }  /* for */

  html_data.print ( "    >" );
  format.intWidth ( html_data, maxRange, 4 );
  html_data.print ( ": \t" );
  format.intWidth ( html_data, values [ rangeLength - 1 ], 4 );
  html_data.print ( "\t" );
  percent = (values [ rangeLength - 1 ] * 100) / num_values;
  format.intWidth ( html_data, (int) percent, 3 );
  html_data.print ( "%\t" );
  percent = (cummulative * 100) / num_values;
  cummulative -= values [ rangeLength - 1 ];
  format.intWidth ( html_data, (int) percent, 3 );
  html_data.println ( "%" );

  html_data.println ( );
  html_data.print ( "Total: \t\t" );
  format.intWidth ( html_data, (int) total, 8 ); 
  html_data.println ( );
  html_data.print ( "Number: \t" );
  format.intWidth ( html_data, num_values, 8 );
  html_data.println ( );

  if ( num_values > 0 )
  {
    html_data.print ( "Average: \t" );
    format.intWidth ( html_data, (int) (total / num_values), 8 );
    html_data.println ( );
  }  // if 

  // Check if a good minimum was specified. 
  if ( goodMinimum > 0 )
  {
    html_data.println ( );
    html_data.print ( "Minimum good value: \t" );
    format.intWidth ( html_data, goodMinimum, 8 );
    html_data.println ( );

    html_data.print ( "Good Total: \t\t" );
    format.intWidth ( html_data, total_good, 8 );
    html_data.println ( );

    html_data.print ( "Good Number: \t\t" );
    format.intWidth ( html_data, num_good_values, 8 );
    html_data.println ( );

    if ( num_good_values > 0 )
    {
      html_data.print ( "Good Average: \t\t" );
      format.intWidth ( html_data, (int) (total_good / num_good_values), 8 );
      html_data.println ( );
    }  // if 
  }  /* if */

  // Check if an insert size was specified.
  if ( insertSize > 0 )
  {
    html_data.println ( );
    html_data.println ( "Insert size: \t\t" + (int) insertSize );

    html_data.print ( "Total coverage: \t" );
    format.precision ( html_data, (total / insertSize), 1 );
    html_data.println ( );

    html_data.print ( "Good  coverage: \t" );
    format.precision ( html_data, (total_good / insertSize), 1 );
    html_data.println ( );

    html_data.print ( "Total seqs/kb: \t\t" );
    format.precision ( html_data, (num_values/(insertSize/1000)), 1 );
    html_data.println ( );

    html_data.print ( "Good  seqs/kb: \t\t" );
    format.precision ( html_data, (num_good_values/(insertSize/1000)), 1 );
    html_data.println ( );
  }  /* if */
}  /* method printHistogram */


/******************************************************************************/
/* This method returns the number of values added to the histogram. */
public int numberOfValues ( )
{
  return num_values;
}  /* method numberOfValues */


/******************************************************************************/
public void shortSummary ( )
{
  System.out.print ( "0: " + values [ 0 ] );

  for ( int i = 1; i <= 3; i++ )
  {
    System.out.print ( " \t" + (1 + (i - 1) * range) + "-" + (i * range) + ": " +
      values [ i ] );
  }  /* for */

  System.out.print ( "\tAverage: " );

  if ( num_values == 0 )
    System.out.print ( 0 );
  else
    System.out.print (  ((int) (total / num_values)) );

  System.out.println ( "\tCount: " + num_values );
}  /* method shortSummary */


}  /* class Histogram */
