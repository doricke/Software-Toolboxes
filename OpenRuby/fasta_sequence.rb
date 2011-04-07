
# This class provides an object model for a FASTA sequence (header line, sequence).
#
# Author::    	Darrell O. Ricke, Ph.D.  (mailto: d_ricke@yahoo.com)
# Copyright:: 	Copyright (C) 2000 Darrell O. Ricke, Ph.D., Paragon Software
# License::   	GNU GPL license:  http://www.gnu.org/licenses/gpl.html
# Contact::   	Paragon Software, 1314 Viking Blvd., Cedar, MN 55011
#
#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.
        
         
###############################################################################
class FastaSequence

# annotation - current sequence annotation
attr_accessor :annotation
# sequence_data - current sequence data.
attr_accessor :sequence_data
# sequence_description - current sequence description.
attr_accessor :sequence_description
# sequence_name - current sequence name.
attr_accessor :sequence_name
# sequence_type - current sequence type.
attr_accessor :sequence_type


###############################################################################
# Create a new FASTA sequence object.
def initialize
  @annotation = {}		# sequence annotation
  @sequence_data = ""		# sequence
  @sequence_description = ""	# sequence description
  @sequence_name = ""		# sequence name
  @sequence_type = ""		# sequence type
end  # method initialize


###############################################################################
# Parse the FASTA description line for annotation /key=value pairs.
def parse_annotation
  tokens = @sequence_description.split('/')
  tokens.each do |pair|
    if ( pair.length > 0 )
      tuple = pair.strip.split('=')
      @annotation[tuple[0]] = tuple[1].delete('"') if tuple[1] != nil
    end  # if
  end  # do
end  # method parse_annotation


###############################################################################
# Parse the FASTA header line.
def parse_header( line )
  return if ( line == nil )  

  space = line.index( ' ' )
  if ( space == nil )
    @sequence_name = line[1..-1]
    return
  end  # if
  @sequence_name = line[1..space]

  @sequence_description = line[(space+1)..-1].chomp()
end  # method parse_header

end  # FastaSequence


###############################################################################
# Testing module.
def test_fasta_sequence
  fasta = FastaSequence.new()
  fasta.parse_header( ">AB00001 /name=\"fun\" /gene=\"test\" " )
  fasta.sequence_data = "ACGT"
  print "name = ", fasta.sequence_name, "\n"
  print "desc = ", fasta.sequence_description, "\n"
  fasta.parse_annotation
  print "seq  = ", fasta.sequence_data, "\n"
end  # method test_fata_sequence

# test_fasta_sequence()

