(ns conlang.sca
  (:use conlang.utils))

(defn parse-rule 
  ([rule] (parse-rule rule {}))
  ([rule phonemes]
	(let [[old new pattern] (clojure.string/split rule #"/")
      from (generate-source-regex pattern phonemes)
      to (generate-target-regex from)]
    (if (match-phoneme-sets old)
      (let [[old-set new-set]
            (map #(phonemes (keyword (match-phoneme-sets %))) [old new])]
        (map
         #(parse-rule
          (str % "/" (nth (or new-set (repeat new)) (.indexOf old-set %)) "/" pattern)
           phonemes) old-set))

      (apply str (replace-blank-with from old) "**" (replace-blank-with to new))))))

(defn process-rule [in rule]
  (let [[from to] (clojure.string/split rule #"\*\*")]
  	(loop [ret in]
      (if (nil? (re-find (re-pattern from) ret))
        ret
        (recur (clojure.string/replace ret (re-pattern from) to))))))

(defn apply-rule
  ([in] in)
  ([in rule] (apply-rule in rule {}))
  ([in rule phonemes]
    (let [rules (flatten (vector (parse-rule rule phonemes)))]
      (loop [ret in rs rules]
        (if (empty? rs)
         ret
          (recur (process-rule ret (first rs)) (rest rs)))))))

(defn apply-rules
  ([in] in)
  ([in rules] (apply-rules in rules {}))
  ([in rules phonemes]
    (loop [ret in rs rules]
      (if (empty? rs)
        ret
        (recur (apply-rule ret (first rs) phonemes) (rest rs))))))