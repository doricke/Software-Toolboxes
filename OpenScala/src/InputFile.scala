import scala.io.Source

//  This class provides an object model for an input text file.
//
//  Author:     Darrell O. Ricke, Ph.D.  (mailto: d_ricke@yahoo.com)
//  Copyright:  Copyright (c) 2011 Ricke Informatics
//  License:    GNU GPL license (http://www.gnu.org/licenses/gpl.html)  
//  Contact:   	Ricke Informatics, 37 Pilgrim Drive, Winchester, MA 01890
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

// ******************************************************************************
class InputFile {

  private[this] var eof_flag: Boolean = true	// end of file flag
  private[this] var contents: String = ""	// contents of text file
  private[this] var cursor: Iterator[String] = null    // cursor for reading text lines from a file
  private[this] var line: String = ""           // current line of text file
  private[this] var file_name: String = ""      // current text file name


  // ****************************************************************************
  // This method returns the end of file flag.
  def end_of_file: Boolean = eof_flag


  // ****************************************************************************
  // This method returns the text file read by read_conents.
  def get_contents: String = contents


  // ****************************************************************************
  // This method returns the current text line of text file.
  def get_line: String = line


  // ****************************************************************************
  // This method opens a text file for reading line by line.
  def open_file() {
    eof_flag = false
    cursor = Source.fromFile(file_name).getLines()
  }  // open_file


  // ****************************************************************************
  // This file returns the next line from the text file.
  def next_line() {
    line = ""
    if ( cursor.hasNext )
      line = cursor.next()
    else
      eof_flag = true
    return line
  }  // next_line    


  // ****************************************************************************
  // This method reads the entire text file into a string. 
  def read_contents() {
    contents = ""
    for (line <- Source.fromFile(file_name).getLines())
      contents = contents + line + "\n"
  }  // read_contents


  // ****************************************************************************
  // This method sets the file name.
  def set_filename( value: String ) {
    file_name = value
  }  // set_filename

}  // class InputFile

