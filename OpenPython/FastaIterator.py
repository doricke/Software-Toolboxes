
import string
from InputFile import InputFile;
from FastaSequence import FastaSequence;

#
# This class provides an iterator for a FASTA sequence library file.
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
class FastaIterator ( InputFile ):

  #############################################################################
  #
  # FastaIterator constructor.
  #
  def __init__ ( self, name = "" ):
    self.initialize ();		# initialize
    if ( name != "" ):
      self.setFileName ( name )
      self.openFile ()
    self.fasta = None


  #############################################################################
  #
  # This method reads the FASTA sequence library file to the next header line.
  #
  def nextHeader ( self ):
    # Find the start of the next sequence.
    while ( ( self.isEndOfFile () == 0 ) and \
            ( self.line != "" ) and \
            ( self.line [ 0 ] != '>' ) ):
      self.nextLine ()
    return self.line


  #############################################################################
  #
  # This method returns the next FastaSequence sequence object.
  #
  def nextSequence ( self ):
    self.nextHeader ()

    # Parse the header line.
    self.fasta = FastaSequence ()
    self.fasta.parseHeader ( self.line )

    # Read in the sequence data.
    self.nextLine ()
    seq = ""
    while ( ( self.isEndOfFile () == 0 ) and \
            ( self.line != "" ) and
            ( self.line [ 0 ] != '>' ) ):
      seq = seq + self.line.rstrip ()
      self.nextLine ()
    self.fasta.setSequence ( seq )
    return self.fasta


###############################################################################
#
# This code tests the FastaIterator object.
#
print("Start-----------------------------------------")
test = FastaIterator ()
test.setFileName ( "test.data" )
test.openFile ()
while ( test.isEndOfFile () == 0 ):
  fasta = test.nextSequence ()
  if ( fasta.sequence != "" ):
    print(fasta.getName (), fasta.getDescription ())
    print(fasta.getSequence ())
print("End-------------------------------------------")
test.closeFile ()
