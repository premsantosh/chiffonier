(ns chiffonier.core
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
