
import string

#
# This class provides an object model for a FASTA sequence.
#
# Author:    	Darrell O. Ricke, Ph.D.  (mailto: ricke@opengenes.com)
# Copyright: 	Copyright (c) 2000 Darrell O. Ricke, Ph.D., Paragon Software
# License:   	GNU GPL license  (http://www.gnu.org/licenses/gpl.html)
# Contact:   	Paragon Software, 1314 Viking Blvd., Cedar, MN 55011
#
#             	This program is free software; you can redistribute it and/or modify
#             	it under the terms of the GNU General Public License as published by
#             	the Free Software Foundation; either version 2 of the License, or
#             	(at your option) any later version.
#         
#             	This program is distributed in the hope that it will be useful,
#             	but WITHOUT ANY WARRANTY; without even the implied warranty of
#             	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#             	GNU General Public License for more details.
#         
#             	You should have received a copy of the GNU General Public License
#             	along with this program; if not, write to the Free Software
#             	Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
#
###############################################################################
class FastaSequence:

  #############################################################################
  #
  # FastaSequence initialization routine.
  #
  def initialize ( self ):
    self.name = ""			# Sequence name
    self.description = ""		# Sequence description
    self.sequence = ""			# Sequence
    self.type = ""			# Sequence type


  #############################################################################
  #
  # FastaSequence Constructor.
  #
  def __init__ ( self ):
    self.initialize ()			# initialize


  #############################################################################
  #
  # This methods returns the FASTA sequence description.
  #
  def getDescription ( self ):
    return self.description


  #############################################################################
  #
  # This method returns the name of the FASTA sequence.
  #
  def getName ( self ):
    return self.name


  #############################################################################
  #
  # This method returns the FASTA sequence.
  #
  def getSequence ( self ):
    return self.sequence


  #############################################################################
  #
  # This method returns the FASTA sequence type.
  #
  def getSequenceType ( self ):
    return self.type


  #############################################################################
  #
  # This method sets the description of the FASTA sequence.
  #
  def setDescription ( self, value ):
    self.description = str ( value )


  #############################################################################
  #
  # This method sets the name of the FASTA sequence.
  #
  def setName ( self, name ):
    self.name = str ( name )


  #############################################################################
  #
  # This method sets the FASTA sequence.
  #
  def setSequence ( self, value ):
    self.sequence = str ( value )


  #############################################################################
  #
  # This method sets the FASTA sequence type.
  #
  def setSequenceType ( self, value ):
    self.type = str ( value )


  #############################################################################
  #
  # This method parses the FASTA sequence header line.
  #
  def parseHeader ( self, line ):
    self.name = ""
    self.description = ""
    if ( line == "" ):  return
    space = line.find(" ")
    if ( space == -1 ):
      if ( len ( line ) > 1 ):
        self.name = line [ 1: ]
      return
    self.name = line [ 1:space ]
    if ( len ( line ) > space + 1 ):
      self.description = line[ space+1: ].rstrip()


###############################################################################
#
# This code tests the FastaSequence object.
#
if ( __name__ == "__main__" ):
  test = FastaSequence ()
  test.setName ( "test.data" )
  test.setDescription ( "This is a test" )
  test.setSequenceType ( "DNA" )
  test.setSequence ( "AAAACCCGGT" )
  
  print(">" + test.getName (), test.getDescription ())
  print(test.getSequence ())

