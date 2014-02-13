(ns com.nomad.t_core
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [com.nomad.handle :refer :all]))

(fact "File join will occur with proper forward slashes"
      (let [result (file-join "foo///" "//bar" "/test/")]
        result => "foo/bar/test"))

(fact "Testing the size of license file as it will not change"
      (size (str (pwd) "/" "LICENSE")) => 11220)

(fact "Testing the basename function"
      (basename "/home/code/foo/bar.clj") => "bar.clj")


