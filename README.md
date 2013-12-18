# chiffonier

A Clojure library for file system operations. Currently in development stage hence there might be bugs.

## Usage

The library currently supports the following commands:
* pwd `pwd` - Returns the current working directory
* ls `ls path/to/ls` - Returns a list of stirngs containing the files and directories
* mkdir `mkdir name` - Creates a directory with given name, returns the error, if any
* mkpath `mkpath path` - Creates the provided path recursively, returns the error, if any
* rm `rm name` - Deletes the file or directory, returns the error, if any

## License

Copyright © 2013 Prem Santosh

Distributed under the Eclipse Public License version 1.0 or any later version.
