(ns conlang.generator-test
  (:use clojure.test
        conlang.generator))

(deftest basic-generator-test
  (testing 
    (is (= "bab" (generate-syllable ["cvc"] {:c ["b"] :v ["a"]})))))