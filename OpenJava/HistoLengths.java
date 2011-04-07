import java.io.*;
import java.awt.*;
import java.util.*;
import ParseName;
import Histogram.*;


/******************************************************************************/
public class HistoLengths extends Object
{

/******************************************************************************/

static protected String cloneName;		// Name of current clone

static protected int insertSize;		// Size of clone insert

static protected int minCount = 19;		// Minimum values to count

static protected int minGood;			// Minimum good quality

// Histograms of clear lengths.
static private Histogram histo_all;
static private Histogram histo_clone;
static private Histogram histo_ne_dt;
static private Histogram histo_dp;
static private Histogram histo_ne_x;
static private Histogram histo_ne_y;
static private Histogram histo_r;
static private Histogram histo_s;
static private Histogram histo_sa;
static private Histogram histo_rc;
static private Histogram histo_bla;
static private Histogram histo_pr;


/******************************************************************************/
public HistoLengths ()
{
  init ();
  init_histograms ();
}  /* constructor HistoLengths */


/******************************************************************************/
public HistoLengths ( String clone_name, int minimum_good )
{
  init ();
  cloneName = clone_name;
  minGood   = minimum_good;
  init_histograms ();
}  /* constructor HistoLengths */


/******************************************************************************/
public HistoLengths ( String clone_name, int minimum_good, int insert_size )
{
  init ();
  cloneName = clone_name;
  minGood   = minimum_good;
  insertSize = insert_size;
  init_histograms ();
}  /* constructor HistoLengths */


/******************************************************************************/
private void init ( )
{
  cloneName = "";
  insertSize = 0;
  minGood = 51;
}  /* method init */


/******************************************************************************/
private void init_histograms ( )
{
  int binSize = 100;
  int maxLength = 900;

  histo_clone = new Histogram ( "Clone " + cloneName, binSize, maxLength, minGood, insertSize );

  histo_all   = new Histogram ( "All Sequences",     binSize, maxLength, minGood, insertSize );
  histo_ne_dt = new Histogram ( "ne dt Sequences",   binSize, maxLength, minGood, insertSize );
  histo_dp    = new Histogram ( "dp Sequences",      binSize, maxLength, minGood, insertSize );
  histo_ne_x  = new Histogram ( "ne .x Sequences",   binSize, maxLength, minGood, insertSize );
  histo_ne_y  = new Histogram ( "ne .y Sequences",   binSize, maxLength, minGood, insertSize );
  histo_r     = new Histogram ( "r Sequences",   binSize, maxLength, minGood );
  histo_s     = new Histogram ( "s Sequences",   binSize, maxLength, minGood );
  histo_sa    = new Histogram ( "sa Sequences",  binSize, maxLength, minGood );
  histo_rc    = new Histogram ( "rc Sequences",  binSize, maxLength, minGood );
  histo_bla   = new Histogram ( "bla Sequences", binSize, maxLength, minGood );
  histo_pr    = new Histogram ( "pr Sequences",  binSize, maxLength, minGood );
}  /* method init_histograms */


/******************************************************************************/
private int countHistograms ( int minimum_values )
{
  int data_sets = 0;

  if ( histo_all.numberOfValues () > minimum_values )
    data_sets++;

  if ( histo_clone.numberOfValues () > minimum_values )
    data_sets++;

  if ( histo_ne_dt.numberOfValues () > minimum_values )
    data_sets++;

  if ( histo_dp.numberOfValues () > minimum_values )
    data_sets++;

  if ( histo_ne_x.numberOfValues () > minimum_values )
    data_sets++;

  if ( histo_ne_y.numberOfValues () > minimum_values )
    data_sets++;

  if ( histo_r.numberOfValues () > minimum_values )
    data_sets++;

  if ( histo_s.numberOfValues () > minimum_values )
    data_sets++;

  if ( histo_sa.numberOfValues () > minimum_values )
    data_sets++;

  if ( histo_rc.numberOfValues () > minimum_values )
    data_sets++;

  if ( histo_bla.numberOfValues () > minimum_values )
    data_sets++;

  if ( histo_pr.numberOfValues () > minimum_values )
    data_sets++;

  return data_sets;
}  /* method countHistograms */


/******************************************************************************/
private void write_html_parameter ( PrintStream html_data, String name,
    String value )
{
  html_data.println ( "<PARAM NAME=\"" + name + "\" VALUE=\"" + value + "\">" );
}  /* method write_html_parameter */


/******************************************************************************/
private void write_html_parameter ( PrintStream html_data, String name,
    int value )
{
  html_data.println ( "<PARAM NAME=\"" + name + "\" VALUE=\"" + value + "\">" );
}  /* method write_html_parameter */


/******************************************************************************/
private void write_html_y_data ( PrintStream html_data, String set_name, 
    Histogram histogram )
{
  // Get the y coordinate values.
  int [] y_values = histogram.getRangeValues ( );

  // Write out the histogram y coordinate values.
  for ( int i = 0; i < histogram.getNumberOfRanges (); i++ )

    write_html_parameter ( html_data, set_name + "_y" + i, y_values [ i ] );

}  /* method write_html_y_data */


/******************************************************************************/
private void write_html_y_values ( PrintStream html_data )
{
  int set_index = 1;


  if ( histo_all.numberOfValues () > minCount )
  {
    write_html_parameter ( html_data, "name_set" + set_index, "All" );
    write_html_y_data ( html_data, "set" + set_index, histo_all );
    set_index++;
  }  /* if */

  if ( histo_clone.numberOfValues () > minCount )
  {
    write_html_parameter ( html_data, "name_set" + set_index, cloneName );
    write_html_y_data ( html_data, "set" + set_index, histo_clone );
    set_index++;
  }  /* if */

  if ( histo_ne_dt.numberOfValues () > minCount )
  {
    write_html_parameter ( html_data, "name_set" + set_index, "ne dt" );
    write_html_y_data ( html_data, "set" + set_index, histo_ne_dt );
    set_index++;
  }  /* if */

  if ( histo_dp.numberOfValues () > minCount )
  {
    write_html_parameter ( html_data, "name_set" + set_index, "dp" );
    write_html_y_data ( html_data, "set" + set_index, histo_dp );
    set_index++;
  }  /* if */

  if ( histo_ne_x.numberOfValues () > minCount )
  {
    write_html_parameter ( html_data, "name_set" + set_index, "ne x" );
    write_html_y_data ( html_data, "set" + set_index, histo_ne_x );
    set_index++;
  }  /* if */

  if ( histo_ne_y.numberOfValues () > minCount )
  {
    write_html_parameter ( html_data, "name_set" + set_index, "ne y" );
    write_html_y_data ( html_data, "set" + set_index, histo_ne_y );
    set_index++;
  }  /* if */

  if ( histo_r.numberOfValues () > minCount )
  {
    write_html_parameter ( html_data, "name_set" + set_index, "r" );
    write_html_y_data ( html_data, "set" + set_index, histo_r );
    set_index++;
  }  /* if */

  if ( histo_s.numberOfValues () > minCount )
  {
    write_html_parameter ( html_data, "name_set" + set_index, "s" );
    write_html_y_data ( html_data, "set" + set_index, histo_s );
    set_index++;
  }  /* if */

  if ( histo_sa.numberOfValues () > minCount )
  {
    write_html_parameter ( html_data, "name_set" + set_index, "SaSe" );
    write_html_y_data ( html_data, "set" + set_index, histo_sa );
    set_index++;
  }  /* if */

  if ( histo_rc.numberOfValues () > minCount )
  {
    write_html_parameter ( html_data, "name_set" + set_index, "rc" );
    write_html_y_data ( html_data, "set" + set_index, histo_rc );
    set_index++;
  }  /* if */

  if ( histo_bla.numberOfValues () > minCount )
  {
    write_html_parameter ( html_data, "name_set" + set_index, "bla" );
    write_html_y_data ( html_data, "set" + set_index, histo_bla );
    set_index++;
  }  /* if */

  if ( histo_pr.numberOfValues () > minCount )
  {
    write_html_parameter ( html_data, "name_set" + set_index, "pr" );
    write_html_y_data ( html_data, "set" + set_index, histo_pr );
    set_index++;
  }  /* if */
}  /* method write_html_y_values */


/******************************************************************************/
public void makeHTML ()
{
  int i;					// index


  String htmlFileName = new String ( cloneName + ".html" );

  System.out.println ( "Writing HTML file \t" + htmlFileName );
  try 
  {
    FileOutputStream html_file = new FileOutputStream ( htmlFileName );
    PrintStream html_data = new PrintStream ( html_file );

    html_data.println ( "<HTML>" );
    html_data.println ( "<TITLE>Read lengths for clone" + cloneName + "</TITLE>" );
    html_data.println ( "<BODY>");

    html_data.println ( "<APPLET CODE=\"Graph.class\" WIDTH=400 HEIGHT=300>" );
    write_html_parameter ( html_data, "title", "Clear read lengths for clone " + cloneName );
    write_html_parameter ( html_data, "values", histo_all.getNumberOfRanges () );
    write_html_parameter ( html_data, "sets", countHistograms ( minCount ) );

    // Get the X coordinate values and names.
    String [] x_names = histo_all.getRangeNames ( );

    // Write out the histogram x coordinate names.
    for ( i = 0; i < histo_all.getNumberOfRanges (); i++ )

      write_html_parameter ( html_data, "name_x" + i, x_names [ i ] );

    // Write out the histogram x coordinate values.
    for ( i = 0; i < histo_all.getNumberOfRanges (); i++ )

      write_html_parameter ( html_data, "point_x" + i, x_names [ i ] );

    // Write out the y values and names for all non-zero histograms.
    write_html_y_values ( html_data );


    html_data.println ( "</APPLET>" );

    html_data.println ( "<PRE>" );
    html_data.println ( "<BR>" );

    // Print out the histogram data.
    printHistograms ( html_data );

    html_data.println ( "</PRE>" );
    html_data.println ( "</BODY>" );
    html_data.println ( "</HTML>" );

    html_data.flush ();
    html_data.close ();
  }
  catch ( IOException e1 )
  {
    System.out.println ( "HistoLengths.makeHTML htmlFile IOException: " + e1 );
  }  /* catch */

}  /* method makeHTML */


/******************************************************************************/
public void printHistograms ( )
{
  if ( histo_all.numberOfValues () > 0 )
    histo_all.printHistogram ( );

  if ( histo_clone.numberOfValues () > 0 )
    histo_clone.printHistogram ( );

  if ( histo_ne_dt.numberOfValues () > 0 )
    histo_ne_dt.printHistogram ( );

  if ( histo_dp.numberOfValues () > 0 )
    histo_dp.printHistogram ( );

  if ( histo_ne_x.numberOfValues () > 0 )
    histo_ne_x.printHistogram ( );

  if ( histo_ne_y.numberOfValues () > 0 )
    histo_ne_y.printHistogram ( );

  if ( histo_r.numberOfValues () > 0 )
    histo_r.printHistogram ( );

  if ( histo_s.numberOfValues () > 0 )
    histo_s.printHistogram ( );

  if ( histo_sa.numberOfValues () > 0 )
    histo_sa.printHistogram ( );

  if ( histo_rc.numberOfValues () > 0 )
    histo_rc.printHistogram ( );

  if ( histo_bla.numberOfValues () > 0 )
    histo_bla.printHistogram ( );

  if ( histo_pr.numberOfValues () > 0 )
    histo_pr.printHistogram ( );

}  /* method printHistograms */


/******************************************************************************/
public void printHistograms ( PrintStream html_data )
{
  if ( histo_all.numberOfValues () > 0 )
    histo_all.printHistogram ( html_data );

  if ( histo_clone.numberOfValues () > 0 )
    histo_clone.printHistogram ( html_data );

  if ( histo_ne_dt.numberOfValues () > 0 )
    histo_ne_dt.printHistogram ( html_data );

  if ( histo_dp.numberOfValues () > 0 )
    histo_dp.printHistogram ( html_data );

  if ( histo_ne_x.numberOfValues () > 0 )
    histo_ne_x.printHistogram ( html_data );

  if ( histo_ne_y.numberOfValues () > 0 )
    histo_ne_y.printHistogram ( html_data );

  if ( histo_r.numberOfValues () > 0 )
    histo_r.printHistogram ( html_data );

  if ( histo_s.numberOfValues () > 0 )
    histo_s.printHistogram ( html_data );

  if ( histo_sa.numberOfValues () > 0 )
    histo_sa.printHistogram ( html_data );

  if ( histo_rc.numberOfValues () > 0 )
    histo_rc.printHistogram ( html_data );

  if ( histo_bla.numberOfValues () > 0 )
    histo_bla.printHistogram ( html_data );

  if ( histo_pr.numberOfValues () > 0 )
    histo_pr.printHistogram ( html_data );

}  /* method printHistograms */


/******************************************************************************/
public void addToHistograms ( int clear )
{
  // If no name is specified, add it to the all data histogram.
  histo_all.addValue ( clear );
}  // method addToHistograms


/******************************************************************************/
public void addToHistograms ( int clear, ParseName parseName )
{
  histo_all.addValue ( clear );

  // Check for Dye Primer reaction.
  if ( parseName.isPrimer () == true )  histo_dp.addValue ( clear );

  // Check for this clone.
  if ( parseName.sameClone ( cloneName ) == true )  histo_clone.addValue ( clear );

  // Check for Nebulized Dye Terminator reaction.
  if ( ( parseName.isTerminator () == true ) &&
       ( parseName.is_ne ()        == true ) )  histo_ne_dt.addValue ( clear );

  // Check for Dye Terminator forward reaction.
  if ( ( parseName.is_x () == true ) && 
       ( parseName.is_ne () == true ) )  histo_ne_x.addValue ( clear );

  // Check for Dye Terminator reverse reaction.
  if ( ( parseName.is_y () == true ) &&
       ( parseName.is_ne () == true ) )  histo_ne_y.addValue ( clear );

  // Check for Dye Primer forward reaction.
  if ( parseName.is_r () == true )  histo_r.addValue ( clear );

  // Check for Dye Primer reverse reaction.
  if ( parseName.is_s () == true )  histo_s.addValue ( clear );

  // Check for Sample Sequence (SaSe).
  if ( parseName.isSaSe () == true )  histo_sa.addValue ( clear );

  // Check for Restriction Cocktail sequence.
  if ( parseName.is_rc () == true )  histo_rc.addValue ( clear );

  // Check for Blunt End sequence.
  if ( parseName.is_bla () == true )  histo_bla.addValue ( clear );

  // Check for primer walk sequence.
  if ( parseName.is_pr () == true )  histo_pr.addValue ( clear );

}  /* method addToHistograms */


/******************************************************************************/

}  /* class HistoLengths */
