(ns com.nomad.t_core
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [com.nomad.handle :refer :all]))

(fact "File join will occur with proper forward slashes"
      (let [result (file-join "foo///" "//bar")]
        result => "foo/bar"))

(fact "Testing the size of license file as it will not change"
      (size (str (pwd) "/" "LICENSE")) => 11220)


