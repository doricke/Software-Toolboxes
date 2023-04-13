
#include <iostream>
#include <fstream>
#include <string>

using namespace std;


/**
  * This class implements a model of an input text file.
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
class InputFile
{
public:

  //***************************************************************************
  // Constructors
  explicit InputFile()
  {
    initialize();
  }  // constructor InputFile


  //***************************************************************************
  // Constructor with input file name parameter.
  InputFile( char* value )
  {
    initialize();
    setFileName( value );
  }  // constructor InputFile


  //***************************************************************************
  // Destructor
  ~InputFile() { ; }

  // Methods
 

  //***************************************************************************
  // This method closes the input file.
  void closeFile() { input_file.close(); }


  //***************************************************************************
  // This method returns the end of file flag.
  bool getEndOfFile() 
  { 
    // Test for end of input file.
    if ( ! input_file )  end_of_file = true;
    return end_of_file; 
  }


  //***************************************************************************
  // This method returns the input file name.
  string getFileName() { return file_name; }


  //***************************************************************************
  // This method returns the current text line.
  string getLine () { return line; }


  //***************************************************************************
  // This method initializes the InputFile class.
  void initialize()
  {
    end_of_file = false;
    file_name = "";
    line = "";
  }  // method initialize


  //***************************************************************************
  // This method returns the end of file flag.
  bool isEndOfFile() { return getEndOfFile(); }


  //***************************************************************************
  // This method returns the next text line from the input file.
  string nextLine() 
  { 
    getline( input_file, line, '\n' ); 
    getEndOfFile();  // Test the end of file condition
    return line; 
  }  // nextLine


  //***************************************************************************
  // This method opens the input file for text input.
  void openFile() 
  { 
    input_file.open( file_name.c_str(), ios::in ); 
  }  // openFile


  //***************************************************************************
  // This method sets the input file name.
  // void setFileName( char* value ) { file_name = value; }


  //***************************************************************************
  void setFileName( string value ) { file_name = value; }
 

//*****************************************************************************
protected:
  bool end_of_file;				// End of file flag

  string file_name;				// Input file name

  std::ifstream input_file;		// Input file

  string line;					// current line of the input file

};  // class InputFile


 //****************************************************************************
 // This main method tests the InputFile class.
 /*
 int main()
 {
   InputFile in_file;
   in_file.setFileName ( "test.out" );

   // cout << "After setFileName\n";

   in_file.openFile();

   // cout << "After openFile\n";

   string text_data;
   while ( in_file.isEndOfFile() == false )
   {
     text_data = in_file.nextLine();
     
     if ( in_file.isEndOfFile() == false )  
       cout << "<" << text_data << ">\n";
   }  // while

   in_file.closeFile();
   return 0;
 };  // main
*/
