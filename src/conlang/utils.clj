(ns conlang.utils)

(defn to-regex-array [coll]
  (apply str (flatten ["[" coll "]"])))

(defn generate-target-regex [in]
  (loop [in in ct 1]
    (if (not (re-find #"\(" in))
      in
      (recur (clojure.string/replace-first in #"\([^)]*\)" (str "\\$" ct)) (inc ct)))))

(defn replace-blank-with [in replacement]
  (clojure.string/replace in #"_" replacement))

(defn split-into-chars [in]
  (rest (clojure.string/split in #"")))
