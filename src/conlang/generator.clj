(ns conlang.generator
  (:use conlang.utils))


(defn generate-syllable [syls phonemes]
  (let [syl (split-into-chars (rand-nth syls))]
    (apply str (map #(rand-nth ((keyword %) phonemes)) syl))
))

(defn generate-word [syls phonemes]
  (apply str (take (-> 4 rand-int inc)
                   (repeatedly #(generate-syllable syls phonemes)))))
