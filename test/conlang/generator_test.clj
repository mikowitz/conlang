(ns conlang.generator-test
  (:use clojure.test
        conlang.generator))

(deftest basic-syllable-generator-test
  (testing 
    (is (= "bab" (generate-syllable ["cvc"] {:c ["b"] :v ["a"]})))))

(deftest basic-word-generator-test
  (testing
    (is (some #(= (generate-word ["cvc"] {:c ["b"] :v ["a"]}) %)
              	  (map #(apply str (take % (repeat "bab"))) (range 1 5))))))