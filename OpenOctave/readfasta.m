function [seqs] = readfasta(filename)
% readfasta - reads in a FASTA formatted sequence file.
%
% filename - file name of FASTA sequence file.
%
%  @author	    Darrell O. Ricke, Ph.D.  (mailto: d_ricke@yahoo.com)
%  Copyright:	Copyright (c) 2011 Ricke Informatics
%  License:	    GNU GPL license (http://www.gnu.org/licenses/gpl.html)  
%  Contact:   	Ricke Informatics, 37 Pilgrim Drive, Winchester, MA 01890
%
%   This program is free software: you can redistribute it and/or modify
%   it under the terms of the GNU General Public License as published by
%   the Free Software Foundation, either version 2 of the License, or
%   (at your option) any later version.
%
%   This program is distributed in the hope that it will be useful,
%   but WITHOUT ANY WARRANTY; without even the implied warranty of
%   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
%   GNU General Public License for more details.
%
%   You should have received a copy of the GNU General Public License
%   along with this program.  If not, see <http://www.gnu.org/licenses/>.
   
  seqs.name = '';
  seqs.header = '';
  seqs.seq = '';

  % Assert: a filename was specified.
  if nargin < 1
    error('readfasta', 'No filename specified');
    return;
  end  % if

  % if ~(exist(filename, 'file'))
  %   error('readfasta', 'File does not exist');
  % end  % if

  [fasta_file,msg] = fopen(filename, 'r');

  if fasta_file == -1
    disp(message);
    return;
  end  % if

  name = '';
  seq = '';
  i = 0;
  while feof(fasta_file) == 0

    line = fgetl(fasta_file);

    if line(1) == '>'              % start of new sequence
      if i > 0
        % disp(seq);
        seqs(i).seq = seq;
        seq = '';
      end  % if

      i = i + 1;
      % disp(line);
      len = size(line);
      name = substr(line, 2, len(2)-1);
      seqs(i).name = substr(line, 2, len(2)-1);
      seqs(i).header = line;

      % disp(name);
      i
      name
      len(2) 
    else
      seq = strcat(seq,line);
    end  % if
  end  % while

  % Save the sequence for the last FASTA sequence entry.
  if i > 0                       % assert: at least one sequence
    seqs(i).seq = seq;
  end  % if

  fclose(fasta_file);

  seqs.name
  seqs.header

  readfasta = seqs;
end  % readline
