(ns conlang.sca-test
  (:use clojure.test
        conlang.sca))

(deftest very-basic-change-rule-test
  (testing
    (is (= "(e)c(t)**$1i$2" (parse-rule "c/i/e_t")))))

(deftest slightly-more-complex-change-rule-test
  (testing
    (is (= "([aeiou])t($)**$1$2" (parse-rule "t//V_$" {:V  ["a" "e" "i" "o" "u"]})))))

(deftest simple-apply-rule-test
  (testing
    (is (= "leitor" (apply-rule "lector" "c/i/e_t" )))))