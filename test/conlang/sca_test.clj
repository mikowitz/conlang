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

(deftest more-complex-apply-rule-test
  (testing
    (is (= "edado" (apply-rule "etato" "S/Z/V_V" {:S ["t"] :Z ["d"] :V ["a" "e" "o"]})))))

(deftest apply-rules-test
  (testing
    (is (= ["doutor" "fogo" "cidade"]
           (map #(apply-rules %
              ["[sm]//_$" "v//V_V" "u/o/_$" "S/Z/V_V" "c/i/F_t" "c/u/B_t" "ii/i/_"]
               {:V ["a" "e" "i" "o" "u"]
               :S ["p" "t" "c"] :Z ["b" "d" "g"]
               :F ["i" "e"] :B ["o" "u"]}) ["doctor" "focus" "civitatem"])))))