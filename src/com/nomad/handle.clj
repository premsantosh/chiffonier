(ns com.nomad.handle
  (:use [clojure.java.shell :only [sh]])
  (:use [clojure.string :only [split]])
  (:import [java.io File FileNotFoundException]
           [java.lang System])
  (:require [clojure.java.io :as cio]))

(defn pwd
  "Returns the current working directory"
  []
  (->
    (sh "pwd")
    (second)
    (second)
    (split #"\n")
    (first)))

(defn ls
  "Returns a list of strings of files and
    directories in the provided path"
  [path]
  (.list (File. path)))

(defn mkdir
  "Creates the directory and returns true
    if created successfully"
  [dir-name]
  (.mkdir (File. dir-name)))

(defn mkpath
  "Created the path recursively and returns true
    if successful else returns the error occurred"
  [path]
  (let [ret (sh "mkdir" "-p" path)]
    (if (= (:exit ret) 0) (Boolean. true) (:err ret))))

(defn rm
  "Deletes the file or directory (only if not empty)
    and returns true if successful"
  [name]
  (.delete (File. name)))

(defn rm-force
  "Force deletes file or directory and returns
  the return code (0 if true)"
  [name]
  (->
   (sh "rm" "-rf" name)
   (first)))

(defn exist?
  "Returns true if the file or directory exists
    in the given path else returns false"
  [name]
  (.exists (File. name)))

(defn cmod
  "Modifies the file permissions for the specified file
    with the given user group and other privilidges. The
    user group and other are decimal values"
  [user group other path-to-file]
  (let [privs (str user group other)
        ret (sh "chmod" privs path-to-file)]
    (if (= (:exit ret) 0) (Boolean. true) (:err ret))))

(defn last-modified
  "Returns the last modified time (in milli seconds) of the file"
  [file]
  (.lastModified (File. file)))

(defn size
  "Returns the size of the file in bytes"
  [file]
  (if (exist? file)
      (.length (File. file))
      (throw (FileNotFoundException. (str "Couldn't locate the file" file)))))

(defn file-join
  "Joins multiple paths to create a true unified path"
  [& files]
  (let [new-files (map (fn [file] (clojure.string/replace file #"/" "")) files)]
    (reduce (fn [a b] (str a "/" b)) new-files)))

(defn home
  "Returns the home for the current user"
  []
  (System/getProperty "user.home"))

(defn basename
  "Returns the base file name given a path
  to a file"
  [path]
  (last (clojure.string/split path #"/")))
