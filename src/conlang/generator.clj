(ns conlang.generator)

(defn generate-syllable [syls phonemes]
  (let [syl (rest (clojure.string/split (rand-nth syls) #""))]
    (apply str (map #(rand-nth ((keyword %) phonemes)) syl))
))