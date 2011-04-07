
#include <iostream>
#include "InputFile.cpp"
#include "FastaSequence.cpp"

using namespace std;


/**
  * This class implements an iterator for a FASTA sequence library file.
  *
  * Author:     Darrell O. Ricke, Ph.D.  (mailto: d_ricke@yahoo.com)
  * Copyright:  Copyright (c) 2000 Darrell O. Ricke, Ph.D., Paragon Software
  * License:    GNU GPL license  (http://www.gnu.org/licenses/gpl.html)
  * Contact:    Paragon Software, 1314 Viking Blvd., Cedar, MN 55011
  *
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
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
class FastaIterator : InputFile
{
public:
  // Constructors
  explicit FastaIterator() { initialize(); }
  FastaIterator( char* value ) { initialize(); setFileName ( value ); };

  // Using methods
  using InputFile::closeFile;
  using InputFile::getEndOfFile;
  using InputFile::getFileName;
  using InputFile::isEndOfFile;
  using InputFile::openFile;
  using InputFile::setFileName;

  // Methods
 
  // This method returns the current FASTA sequence object.
  FastaSequence getFastaSequence() { return fasta_sequence; }


  //***************************************************************************
  // This method returns the next FASTA sequence as an object.
  FastaSequence nextSequence()
  {
    // Check for first or blank line.
    if ( line.size () <= 0 )  InputFile::nextLine();

    // Parse the header line.
    fasta_sequence.parseHeader ( line );

    // Read in and set the sequence.
    fasta_sequence.setSequence ( readSequence () );

    return fasta_sequence;
  }  // method nextSequence


//*****************************************************************************
protected:
  FastaSequence fasta_sequence;		// Current FASTA sequence


  //***************************************************************************
  // This method reads in the next sequence from the FASTA library file.
  string readSequence()
  {
    string seq;

    // Advance past the header line.
    InputFile::nextLine();			// get the next line

    while ( ( line.size() > 0 ) && ( line.at( 0 ) != '>' ) )
    {
      seq += line;

      InputFile::nextLine();		// get the next line
    }  // while

    return seq;
  }  // method readSequence

};  // class FastaIterator


//*****************************************************************************
// This main method tests the FastaIterator object.
/*
int main()
{
  FastaIterator app;
  app.setFileName ( "test.fasta" );
  app.openFile ();

  FastaSequence fasta;

  while ( app.isEndOfFile() != true )
  {
    fasta = app.nextSequence ();

    cout << fasta.toString();
  }  // while

  app.closeFile ();
};  // main
*/
