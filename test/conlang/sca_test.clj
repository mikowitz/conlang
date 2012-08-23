(ns conlang.sca-test
  (:use clojure.test
        conlang.sca))

(deftest very-basic-change-rule-test
  (testing
    (is (= "ect**eit" (parse-rule "c/i/e_t")))))