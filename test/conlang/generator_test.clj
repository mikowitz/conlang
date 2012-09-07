(ns conlang.generator-test
  (:use clojure.test
        conlang.generator))

(deftest basic-syllable-generator-test
  (testing 
    (is (= "bab" (generate-syllable ["CVC"] {:C ["b"] :V ["a"]})))))

(deftest basic-word-generator-test
  (testing
    (is (= ["bab"] (distinct (re-seq #"\w{3}" (generate-word ["CVC"] {:C ["b"] :V ["a"]})))))))
