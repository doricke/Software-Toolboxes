
#include <iostream>
#include <string>

using namespace std;


/**
  * This class implements a model for a FASTA sequence and header line.
  *
  * Author:     Darrell O. Ricke, Ph.D.  (mailto: d_ricke@yahoo.com)
  * Copyright:  Copyright (c) 2000 Darrell O. Ricke, Ph.D., Paragon Software
  * License:    GNU GPL license  (http://www.gnu.org/licenses/gpl.html)
  * Contact:    Paragon Software, 1314 Viking Blvd., Cedar, MN 55011
  *
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 2 of the License, or
  * (at your option) any later version.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  *
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>. 
  */       
class FastaSequence
{
public:
  // Constructors
  explicit FastaSequence() { initialize(); }

  // This method returns the description of the FASTA sequence.
  string getDescription() { return description; }

  // This method returns the length of the FASTA sequence.
  int getLength() { return sequence.length(); }

  // This method returns the name of the FASTA sequence.
  string getName() { return name; }
 
  // This method returns the current FASTA sequence. 
  string getSequence() { return sequence; }
 

  //***************************************************************************
  // This method initializes the class FastaSequence. 
  void initialize() 
  {
    description = ""; 
    name = "";
    sequence = "";
  }  // method initialize


  //***************************************************************************
  // Parse the FASTA header line with sequence name and description.
  void parseHeader ( string line )
  {
    // Assert: valid line
    if ( line.size () <= 0 )  return;

    // Assert: header line starts with '>' character.
    if ( line.at ( 0 ) != '>' )  return;

    // Split the name from the description at the first white space.
    string::size_type index = line.find_first_of( " \t" );
    if ( index != string::npos )
    {
      name = line.substr ( 1, index - 1 );
      description = line.substr ( index + 1 );
    }  // if
    else
    {
      name = line.substr ( 1 );
    }  // else
  }  // method parseHeader


  //***************************************************************************
  // This method sets the description for the FASTA sequence.
  void setDescription ( string value ) { description = value; }


  //***************************************************************************
  // This method sets the name for the FASTA sequence.
  void setName ( string value ) { name = value; }


  //***************************************************************************
  // This method sets the FASTA sequence.
  void setSequence ( string value ) { sequence = value; }


  //***************************************************************************
  // This method converts this object to a FASTA sequence with header string.
  string toString()
  {
    string str = ">" + name + " " + description + "\n";

    for ( int index = 0; index < sequence.size(); index += 50 )
    {
      if ( index + 50 > sequence.size() )
	str += sequence.substr ( index ) + "\n";
      else
	str += sequence.substr ( index, 50 ) + "\n";
    }  // for

    return str;
  }  // method toString


//*****************************************************************************
protected:
  string name;		// FASTA sequence name

  string description;	// FASTA sequence description

  string sequence;	// FASTA sequence

};  // class FastaSequence


// Testing module.

//*****************************************************************************
// This main method tests the FastaSequence object.
/*
int main()
{
  FastaSequence fasta_seq;

  string header_line = ">Seq_Name This is the description.";
  fasta_seq.parseHeader ( header_line );

  cout << "Name: '" << fasta_seq.getName() << "'\n";
  cout << "Desc: '" << fasta_seq.getDescription() << "'\n";
};  // main
*/
