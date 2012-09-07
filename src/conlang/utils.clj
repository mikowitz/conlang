(ns conlang.utils)

(defn to-regex-array [coll]
  (apply str (flatten ["[" coll "]"])))

(defn match-phoneme-sets [st]
  (re-find #"[A-Z]" st))

(defn generate-source-regex [pattern phonemes]
  (clojure.string/replace
    (if (match-phoneme-sets pattern)
      (clojure.string/replace pattern #"[A-Z]"
        (fn [m] (to-regex-array (phonemes(keyword m)))))
        pattern)
      #"([^_]+)" "($1)"))

(defn generate-target-regex [in]
  (loop [in in ct 1]
    (if (not (re-find #"\(" in))
      in
      (recur (clojure.string/replace-first in #"\([^)]*\)" (str "\\$" ct)) (inc ct)))))

(defn replace-blank-with [in replacement]
  (clojure.string/replace in #"_" replacement))

(defn split-into-chars [in]
  (rest (clojure.string/split in #"")))
