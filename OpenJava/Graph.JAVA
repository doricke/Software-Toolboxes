import java.awt.*;
import java.applet.*;
import java.io.*;

/******************************************************************************/
public class Graph extends Applet
{
  double values [] [];				// Data set values

  int    sets;					// Number of data sets

  String labels [];				// Data set labels

  double maxValue = 0;				// Maximum value in data

  int    minScale = 250;			// Minimum scale value

  String names [];				// X axis names

  int    numberOfValues;			// Number of values per line

  String title;					// Graph title

  Color colors [];				// Color array


/******************************************************************************/
private void init_colors ()
{

  // Initialize color array.
  colors = new Color [ sets ];
  for ( int i = 0; i < sets; i++ )
    colors [ i ] = Color.black;

  if ( sets >= 1 )  colors [ 0 ] = Color.blue;
  if ( sets >= 2 )  colors [ 1 ] = Color.red;
  if ( sets >= 3 )  colors [ 2 ] = Color.green;
  if ( sets >= 4 )  colors [ 3 ] = Color.white;
  if ( sets >= 5 )  colors [ 4 ] = Color.cyan;
  if ( sets >= 6 )  colors [ 5 ] = Color.magenta;
  if ( sets >= 7 )  colors [ 6 ] = Color.yellow;
  if ( sets >= 8 )  colors [ 7 ] = Color.orange;
  if ( sets >= 9 )  colors [ 8 ] = Color.pink;
}  /* method init_colors */


/******************************************************************************/
public void init ()
{
  numberOfValues = getInteger ( getParameter ( "values" ) );
  sets = getInteger ( getParameter ( "sets" ) );
  init_colors ();
  values = new double [ sets ] [ numberOfValues ];
  names = new String [ numberOfValues ];
  labels = new String [ sets ];
  title = getParameter ( "title" );

  // Read in the data points for each data set.
  for ( int j = 0; j < sets; j++ )
  {
    // Read in the Y axis data points for the current data set.
    for ( int i = 0; i < numberOfValues; i++ )

      values [ j ] [ i ] = getInteger ( getParameter ( "set" + (j + 1) + "_y" + i ) );

    // Read in the data set labels.
    labels [ j ] = getParameter ( "name_set" + (j + 1) );
  }  /* for */

  // Get the X axis labels.
  for ( int i = 0; i < numberOfValues; i++ )

    names [ i ] = getParameter ( "point_x" + i );
}  /* init */


/******************************************************************************/
private int draw_labels ( Graphics g, int top, int bottom, int left, int right, 
    FontMetrics labelFontMetrics )
{
  int x = 0;

  // Set left to the width of the widest label.
  int widest_label = labelFontMetrics.stringWidth ( labels [ 0 ] );
  for ( int j = 1; j < sets; j++ )

    if ( labelFontMetrics.stringWidth ( labels [ j ] ) > widest_label )

      widest_label = labelFontMetrics.stringWidth ( labels [ j ] );

  top += labelFontMetrics.getHeight ( );
 
  int labelHeight = (bottom - top) / sets;

  // Draw the label names.
  for ( int j = 0; j < sets; j++ )
  {
    g.setColor ( colors [ j ] );
    int y = top + j * labelHeight; 
    g.drawString ( labels [ j ], x, y ); 
  }  /* for */

  g.setColor ( Color.black );
  return widest_label + labelFontMetrics.stringWidth ( " " );
}  /* method draw_labels */


/******************************************************************************/
public void paint ( Graphics g )
{
  int i;

  Dimension d = size ();
  Insets in = insets ();

  int left = in.left;
  if ( in.left == 0 )  left += 1;

  int right = d.width - in.right;
  if ( in.right == 0 )  right -= 1;

  int bottom = d.height - in.bottom;

  Font titleFont = new Font ( "helvetica", Font.BOLD, 20 );
  FontMetrics titleFontMetrics = g.getFontMetrics ( titleFont );
  Font labelFont = new Font ( "Helvetica", Font.BOLD, 12 );
  FontMetrics labelFontMetrics = g.getFontMetrics ( labelFont );

  // Draw the Graph title.
  int titleWidth = titleFontMetrics.stringWidth ( title );
  int y = titleFontMetrics.getAscent ();
  int x = ( (right - left) - titleWidth) / 2;
  g.setFont ( titleFont );
  g.drawString ( title, x, y );

  int top = in.top + titleFontMetrics.getHeight ();

  g.setFont (labelFont);

  // Draw the Data set names on the Y axis.
  left += draw_labels ( g, top, bottom, left, right, labelFontMetrics );

  // Draw the graph.
  draw_graph ( g, top, bottom, left, right, labelFontMetrics );
}  /* method paint */


/******************************************************************************/
private double set_scale ( int top, int bottom )
{
  double minValue = 0;

  // Note that minValue is set to zero.
  for ( int j = 0; j < sets; j++ )
    for ( int i = 0; i < numberOfValues; i++ )
    {
      if ( minValue > values [ j ] [ i ] )  minValue = values [ j ] [ i ];
      if ( maxValue < values [ j ] [ i ] )  maxValue = values [ j ] [ i ];
    }  /* for */

  // Shift the values to multiples of minScale.
  maxValue = (double) ( ( ( ( (int) maxValue - 1 ) / minScale ) + 1 ) * minScale );

  return (bottom - top) / (maxValue - minValue);
}  /* set_scale */


/******************************************************************************/
private void draw_graph ( Graphics g, int top, int bottom, 
    int left, int right, FontMetrics labelFontMetrics )
{
  int x;			// x axis coordinate
  int y;			// y axis coordinate


  bottom -= (labelFontMetrics.getHeight () + 4)+ labelFontMetrics.getHeight () ;

  double scale = set_scale ( top, bottom );

  // Draw the y axis labels.
  String label = new String ( );
  label = label.valueOf ( (int) maxValue );
  int labelWidth = labelFontMetrics.stringWidth ( label );

  left += labelWidth + 4;

  int interval = (int) maxValue / 5;
  if ( interval < 1 )  interval = 1;

  x = left - labelWidth - 4;;
  int x2;
  for ( int i = 0; i <= (int) maxValue; i += interval )
  {
    label = label.valueOf ( i );
    x2 = x + ( labelWidth - labelFontMetrics.stringWidth ( label ) );

    y = (int) ( top + (maxValue - i) * scale );
    g.drawLine ( left-4, y, left, y );		// draw tick mark

    y += ( labelFontMetrics.getHeight () - labelFontMetrics.getDescent () ) / 2;
    g.drawString ( label, x2, y );
  }  /* for */

  // Draw the X axis labels.
  int barWidth = (right - left) / numberOfValues;
  y = bottom + labelFontMetrics.getHeight () - labelFontMetrics.getDescent ();

  for ( int i = 0; i < numberOfValues; i++ )
  {
    labelWidth = labelFontMetrics.stringWidth ( names [ i ] );
    x = left + i * barWidth - labelWidth/2;
    g.drawString ( names [ i ], x, y );
  }  /* for */

  // Draw the tick marks.
  for ( int i = 0; i < numberOfValues; i++ )
  {
    x = left + i * barWidth;

    // Draw tick mark
    g.drawLine ( x, bottom, x, bottom+4 );
  }  // for

  right = left + (numberOfValues - 1) * barWidth + 1;

  // Box the graph
  g.drawLine ( left,  top,    right, top );		// top	
  g.drawLine ( right, top,    right, bottom );		// right side
  g.drawLine ( left,  bottom, right, bottom );		// bottom
  g.drawLine ( left,  top,    left,  bottom );		// left side

  // Draw the data sets as lines
  for ( int i = 0; i < sets; i++ )
  {
    g.setColor ( colors [ i ] );

    draw_line ( g, i, barWidth, scale, top, bottom, left );
  }  /* for */

}  // method draw_graph


/******************************************************************************/
private void draw_line ( Graphics g, int set_index, int barWidth, double scale, 
    int top, int bottom, int left )
{
  for ( int i = 0; i < numberOfValues - 1; i++ )
  {
    int x1 = left + i * barWidth + 1;
    int y1 = top;
    int x2 = left + (i + 1) * barWidth + 1;
    int y2 = top;

    if ( values [ 0 ] [ i ] > 0 )
      y1 += (int) ( (maxValue - values [ set_index ] [ i ]) * scale);
    else
    {
      y1 += (int) (maxValue * scale);
    }  /* else */

    if ( values [ 0 ] [ i + 1 ] > 0 )
      y2 += (int) ( (maxValue - values [ set_index ] [ i + 1 ]) * scale);
    else
    {
      y2 += (int) (maxValue * scale);
    }  /* else */

    g.drawLine ( x1, y1, x2, y2);
  }  /* for */
}  /* method draw_line */


/******************************************************************************/
private int getInteger ( String line )
{
  int i = 0;
  int index = 0;
  int sign = 1;					// Default sign = +

  if ( line == null )  return ( 0 );

  // Skip leading white space.
  while ( ( line.charAt ( index ) == ' ' ) ||
          ( line.charAt ( index ) == '\t' ) )  index++;

  // Check for a sign.
  if ( line.charAt ( index ) == '+' )
    index++;
  else
    if ( line.charAt ( index ) == '-' )
    {
      sign = -1;
      index++;
    }  /* if */

  // Traverse the integer.
  while ( index < line.length () )
  {
    if ( ( line.charAt ( index ) >= '0' ) && ( line.charAt ( index ) <= '9' ) )

      i = i * 10 + (int) line.charAt ( index ) - (int) '0';

    else  index = line.length ();		// Terminate loop

    index++;
  }  /* while */

  // Set the sign.
  i *= sign;

  return ( i );					// Return the integer
}  /* method getInteger */


}  /* class Graph */
