
package InputFile;

# This class provides an object model for an input text file.
#
# Author::      Darrell O. Ricke, Ph.D.  (mailto: d_ricke@yahoo.com)
# Copyright::   Copyright (C) 2000 Darrell O. Ricke, Ph.D., Paragon Software
# License::     GNU GPL license:  http://www.gnu.org/licenses/gpl.html
# Contact::     Paragon Software, 1314 Viking Blvd., Cedar, MN 55011
#
#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 2 of the License, or
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
sub new
{
  my $class = shift;

  my $end_of_file = 7;
  my $file_name = "Susan";
  my $line = "text line";
  my $fh = @_;

  if ( ref $fh !~ /GLOB/ )
  {
    $class->{INPUT_FILE} = \*IN_FILE;	# create and save a file handle reference
  }  # if
  else
  {
    $class->{INPUT_FILE} = $fh;		# save the file handle reference
  }  # else

  my $self = { _file_name => $file_name, _end_of_file => $end_of_file, _line => $line };
  bless $self, $class;
  my (%params) = @_;

  return $self;
}  # sub new


# Accessors

###############################################################################
# accessor file_name
sub file_name 
{ 
  my ($obj, $name) = shift ( @_ ); 

  $obj->{_file_name} = $name;
  print "InputFile.file_name name = $name\n"; 

  return $obj;
}  # accessor file_name


###############################################################################
# accessor end_of_file
sub end_of_file
{ 
  my $obj = shift; 

  # Check for the end of the file.
  if ( ! $obj->{_line} )  { return $obj, 0; }
  return $obj, 1;
}  # accessor end_of_file


###############################################################################
# accessor line
sub line { shift->{_line} }


# Methods

###############################################################################
sub closeFile
{
  my $obj = shift;

  close INPUT_FILE;

  return $obj;
}  # method closeFile


###############################################################################
sub initialize
{
  my $obj = shift;

  $obj->{_end_of_file} = 0;
  $obj->{_file_name} = "";
  $obj->{_line} = "";

  return $obj;
}  # method initialize


###############################################################################
sub next
{
  my $obj = shift;

  my $fh = $obj->{INPUT_FILE};

  $obj->{_line} = <$fh>;
  $obj->{_line} = chomp ( $obj->{_line} );

  # Check for the end of the file.
  if ( ! defined $obj->{_line} )  { $obj->{_end_of_file} = 1; }

  return $obj, $obj->{_line};
}  # method next


###############################################################################
sub openFile
{
  my $obj = shift;

  my $filename = $obj->{_file_name};

  print "InputFile.openFile: _file_name = $filename\n";

  open $obj->{INPUT_FILE}, $obj->{_file_name};

  return $obj;
}  # method openFile

1;
