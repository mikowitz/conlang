(ns conlang.utils-test
  (:use clojure.test
        conlang.utils))

(deftest to-regex-array-test
  (testing
    (is (= "[aeiou]" (to-regex-array ["a" "e" "i" "o" "u"])))))

(deftest generate-target-regex-test
  (testing
    (is (= "$1_$2hello$3" (generate-target-regex "(xy)_(\\d+)hello(\\w*)")))))

(deftest replace-blank-with-test
  (testing
    (is (= "xyzzy" (replace-blank-with "x_zz_" "y")))))