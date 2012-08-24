(ns conlang.sca-test
  (:use clojure.test
        conlang.sca))

(deftest very-basic-change-rule-test
  (testing
    (is (= "(e)c(t)**$1i$2" (parse-rule "c/i/e_t")))))

(deftest slightly-more-complex-change-rule-test
  (testing
    (is (= "([aeiou])t($)**$1$2" (parse-rule "t//V_$" {:V  ["a" "e" "i" "o" "u"]})))))

(deftest complex-change-rule-test
  (testing
    (is (= ["(o)p(i)**$1b$2" "(o)t(i)**$1d$2" "(o)k(i)**$1g$2"]
            (parse-rule "S/Z/o_i" {:S ["p" "t" "k"] :Z ["b" "d" "g"]})))))

(def complex-imbalanced-change-rule-test
  (testing
    (is (= ["(o)p(i)**$1i$2" "(o)t(i)**$1i$2" "(o)k(i)**$1i$2"]
           (parse-rule "S/i/o_i" {:S ["p" "t" "k"]})))))

(deftest simple-apply-rule-test
  (testing
    (is (= "leitor" (apply-rule "lector" "c/i/e_t" )))))
