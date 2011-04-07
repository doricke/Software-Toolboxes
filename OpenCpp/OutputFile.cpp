
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

/**
  * This class implements a model for an output text file.
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
class OutputFile
{
public:

  //***************************************************************************
  // Constructors
  explicit OutputFile() { initialize(); }
  OutputFile( char* value ) { initialize(); setFileName ( value ); }


  //***************************************************************************
  // Operators
  // void operator<<( char* value ) { output_file << value; }
  void operator<<( string value ) { output_file << value; }
  

  //***************************************************************************
  // Destructor
  ~OutputFile() { ; }

  // Methods
 

  //***************************************************************************
  // This method closes the output file.
  void closeFile() { output_file.close(); }


  //***************************************************************************
  // This method returns the output file name.
  string getFileName() { return file_name; }


  //***************************************************************************
  // This method initializes the OutputFile class.
  void initialize() { file_name = ""; }


  //***************************************************************************
  // This method opens the output file for writing.
  void openFile() 
  { 
    output_file.open( file_name.c_str(), ios::out | ios::app ); 
  }  // openFile


  //***************************************************************************
  // This method sets the output file name.
  void setFileName( string value ) { file_name = value; }


//*****************************************************************************
protected:
  string  file_name;		// Output file name

  std::ofstream output_file;		// Output file

};  // class OutputFile



//*****************************************************************************
// This main method tests the OutputFile class.
/*
int main()
{
  OutputFile out_file;
  out_file.setFileName ( "test.out" );
  out_file.openFile();

  out_file << "This is the first line of the test output file.\n";
  out_file << "This is the second line.\n";

  out_file.closeFile();
  return 0;
};  // main
*/

