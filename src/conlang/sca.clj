(ns conlang.sca)

(defn parse-rule 
  ([rule] (parse-rule rule {}))
  ([rule phonemes]
	(let [[old new pattern] (clojure.string/split rule #"/")]
      (apply str (interpose "**" (map #(clojure.string/replace pattern #"_" %) [old new])))))) 