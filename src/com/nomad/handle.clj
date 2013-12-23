(ns com.nomad.handle
  (:use [clojure.java.shell :only [sh]])
  (:use [clojure.string :only [split]])
  (:import [java.io File FileNotFoundException])
  (:require [clojure.java.io :as cio]))


(defn pwd []
  "Returns the currect working directory"
  (->
    (sh "pwd")
    (second)
    (second)
    (split #"\n")
    (first)))

(defn ls [path]
  "Returns a list of stirngs of files and
    directories in the provided path"
  (.list (File. path)))

(defn mkdir [dir_name]
  "Creates the directory and returns true
    if created successfully"
  (.mkdir (File. dir_name)))

(defn mkpath [path]
  "Created the path recursively and returns true
    if successful else returns the error occurred"
  (let [ret (sh "mkdir" "-p" path)]
    (if (= (:exit ret) 0) (Boolean. true) (:err ret))))

(defn rm [name]
  "Deletes the file or directory (only if not empty)
    and returns true if successful"
  (.delete (File. name)))

(defn exist? [name]
  "Returns true if the file or directory exists
    in the given path else returns false"
  (.exists (File. name)))

(defn cmod [user group other path_to_file]
  "Modifies the file permissions for the specified file
    with the given user group and other privilidges. The
    user group and other are decimal values"
  (let [privs (str user group other)
        ret (sh "chmod" privs path_to_file)]
    (if (= (:exit ret) 0) (Boolean. true) (:err ret))))

(defn last-modified [file]
  "Returns the last modified time (in milli seconds) of the file"
  (.lastModified (File. file)))

(defn size [file]
  "Returns the size of the file in bytes"
  (if (exist? file)
      (.length (File. file))
      (throw (FileNotFoundException. (str "Couldn't locate the file" file)))))

(defn file-join [file_one file_two]
  "Joins two paths to create a true unified path"
  (let [one (clojure.string/replace file_one #"/+$" "")
        two (clojure.string/replace file_two #"^/+" "")]
    (str one "/" two)))


