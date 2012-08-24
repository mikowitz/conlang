(ns conlang.sca
  (:use conlang.utils))

(defn parse-rule 
  ([rule] (parse-rule rule {}))
  ([rule phonemes]
	(let [[old new pattern] (clojure.string/split rule #"/")
      from (
        clojure.string/replace
          (if (re-find #"[A-Z]" pattern)
            (clojure.string/replace pattern #"[A-Z]"
              (fn [m] (to-regex-array (phonemes(keyword m)))))
              pattern)
            #"([^_]+)" "($1)")
      to (generate-target-regex from)]
    (if (re-find #"[A-Z]" old)
	  (let [[old-set new-set] (map #(phonemes (keyword (re-find #"[A-Z]" %))) [old new])]
        (map #(parse-rule (str % "/" (nth (or new-set (repeat new)) (.indexOf old-set %)) "/" pattern)) old-set))
      (apply str (replace-blank-with from old) "**" (replace-blank-with to new))))))

(defn apply-rule
  ([in] in)
  ([in rule] (apply-rule in rule {}))
  ([in rule phonemes]
    (let [[from to] (clojure.string/split (parse-rule rule phonemes) #"\*\*")]
      (clojure.string/replace in (re-pattern from) to))))