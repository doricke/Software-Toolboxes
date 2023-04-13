

//  This is the test class for InputFile class.
//
//  Author:     Darrell O. Ricke, Ph.D.  (mailto: d_ricke@yahoo.com)
//  Copyright:  Copyright (c) 2011 Ricke Informatics
//  License:    GNU GPL license (http://www.gnu.org/licenses/gpl.html)  
//  Contact:   	Ricke Informatics, 37 Pilgrim Drive, Winchester, MA 01890
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 2 of the License, or
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
object InputFileTest {

  //****************************************************************************
  // This method tests the InputFile class.
  def main(args: Array[String]) {

    println("InputFile.main called")

    var app : InputFile = new InputFile
    app.set_filename("InputFileTest.scala")
    // app.file_name =  "InputFileTest.scala"
    app.read_contents()
    println("InputFileTest.scala source code:")
    println(app.get_contents)

    app.set_filename("InputFile.scala")
    // app.file_name =  "InputFile.scala"
    app.open_file()
    println("InputFile.scala first line:")

    var l : String = ""
    while ( app.end_of_file == false )
    {
      app.next_line()
      l = app.get_line
      if ( app.end_of_file == false )
        println(l)
    }  // while

  }  // main

}  // object InputFileTest
