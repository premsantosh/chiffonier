# chiffonier

[![Build Status](https://secure.travis-ci.org/premsantosh/chiffonier.png)](http://travis-ci.org/premsantosh/chiffonier)

A Clojure library for file system and handling, utilities and operations. This is only for Unix/Linux (BSD) systems. If used in windows no gurantee is given for all the commands to function.
Currently in development stage hence there might be bugs.

## Usage

Add the dependency to your project.clj
**com.nomad/chiffonier 0.1.6**

The library currently supports the following commands:

| Command | Usage | Description |
|-----------| :------------: | :--------------: |
| pwd | `pwd` | Returns the current working directory |
| ls | `ls path/to/ls` | Returns a list of strings containing the files and directories |
| mkdir | `mkdir name` | Creates a directory with given name, returns the error, if any |
| mkpath | `mkpath path` | Creates the provided path recursively, returns the error, if any |
| rm | `rm name` | Deletes the file or directory, returns the error, if any |
| rm-force| `rm-force` | Force deletes the file or the directory without checking if its empty or not |
| exist? | `exist? name` | Returns a boolean true if the file or directory exists |
| cmod | `cmod user group other file_name` | Changes the file permissions on the specified file. The user, group and other are decimal values/ Returns the error, if any |
| last-modified | `last-modified file_name` | Returns the last modified time of the file in milli seconds |
| size | `size file` | Returns the size of the specified file in bytes |
| file-join | `files` | Joins multiple paths to form one unified file path |
| home | `home` | Returns home of the current user |
| basename| `basename` | Returns the base file name from the given path |


### Example

```
(:require [com.nomad.handle :as fs])

(fs/pwd)

(fs/file-join "/foo/" "/bar/my_file.clj") => "/foo/bar/my_file.clj"

(fs/basename "/home/foo/bar/example.clj") => "example.clj"

```

## License

Copyright © 2013 Prem Santosh

Distributed under the Eclipse Public License version 1.0 or any later version.
