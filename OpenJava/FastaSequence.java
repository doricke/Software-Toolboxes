

/******************************************************************************/
/**
  This class provides an object model for a FASTA sequence.
  @author	    Darrell O. Ricke, Ph.D.  (mailto: d_ricke@yahoo.com)
  Copyright:	Copyright (c) 2000 Paragon Software
  License:	    GNU GPL license (http://www.gnu.org/licenses/gpl.html)  
  Contact:   	Paragon Software, 1314 Viking Blvd., Cedar, MN 55011
 
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 2 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
public class FastaSequence extends Object
{


/******************************************************************************/

/** The sequence name */
private   String  name = "";

/** The sequence description */
private   String  description = "";

/** The sequence */
private   String  sequence = "";	// sequence

/** The sequence type */
private   byte  sequence_type = 0;


/******************************************************************************/
/** This constructor creates and initializes a new FastaSequence object. */
public FastaSequence ()
{
  initialize ();
}  // constructor FastaSequence


/******************************************************************************/
/** This method initializes the class variables. */
public void initialize ()
{
  name = "";
  description = "";
  sequence = "";
  sequence_type = 0;
}  // method initialize 


/******************************************************************************/
/** 
  This method returns the FASTA sequence description.

  @return String of current sequence description.
*/
public String getDescription ()
{
  return description;
}  // method getDescription


/******************************************************************************/
/**
  This method returns the current sequence length.

  @return int of current FASTA sequence length.
*/
public int getLength ()
{
  return sequence.length ();
}  // method getLength


/******************************************************************************/
/**
  This method returns the current FASTA sequence name.

  @return String of current FASTA sequence name.
*/
public String getName ()
{
  return name;
}  // method getName


/******************************************************************************/
/**
  This method returns the current FASTA sequence.

  @return String of current sequence.
*/
public String getSequence ()
{
  return sequence;
}  // method getSequence


/******************************************************************************/
/**
  This method returns the current sequence type.

  @return byte value indicating current sequence type.
*/
public byte getSequenceType ()
{
  return sequence_type;
}  // method getSequenceType


/******************************************************************************/
/** This method sets the current sequence name. */
public void setName ( String value )
{
  name = value;
}  // method setName


/******************************************************************************/
/** This method sets the current sequence description. */
public void setDescription ( String value )
{
  description = value;
}  // method setDescription


/******************************************************************************/
/** This method sets the current sequence. */
public void setSequence ( String value )
{
  sequence = value;
}  // method setSequence


/******************************************************************************/
/** This method sets the current sequence type. */
public void setSequenceType ( byte value )
{
  sequence_type = value;
}  // method setSequenceType


/******************************************************************************/
/* This method parses the a FASTA sequence header line. */
public void parseHeader ( String line )
{
  // Check for no header line.
  if ( line.length () <= 0 )
  {
    System.out.println ( "FastaSequence.parseHeader: *Warning* No header line." );
    return;
  }  // if

  // Check for invalid header line.
  if ( line.charAt ( 0 ) != '>' )
  {
    System.out.println ( "FastaSequence.parseHeader: *Warning* Invalid header line '" + line + "'" );
    return;
  }  // if

  int index1 = line.indexOf ( ' ' );
  int index2 = line.indexOf ( '\t' );
  if ( ( index2 > 0 ) && ( index2 < index1 ) )  index1 = index2;
  if ( ( index1 < 0 ) && ( index2 > 0 ) )  index1 = index2;

  if ( index1 < 0 )
  {
    name = line.substring ( 1 ).trim ();
    return;
  }  // if
 
  name = line.substring ( 1, index1 );

  if ( index1 + 1 < line.length () ) 
    description = line.substring ( index1 + 1 ).trim ();
}  // method parseHeader


/******************************************************************************/
/**
  This method returns a the current FASTA sequence as a text string.

  @return  String of current FASTA sequence and header line.
*/
public String toString ()
{
  StringBuffer str = new StringBuffer ( 10000 );
  str.append ( ">" + name + " " + description + "\n" );

  for ( int index = 0; index < sequence.length (); index += 50 )
  {
    if ( index + 50 >= sequence.length () )
      str.append ( sequence.substring ( index ) + "\n" );
    else
      str.append ( sequence.substring ( index, index + 50 ) + "\n" );
  }  // for

  return str.toString ();
}  // method toString


/******************************************************************************/

}  // class FastaSequence
