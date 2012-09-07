# conlang

Some tools for conlanging written in Clojure. I'm using Mark Rosenfelder's
[zompist.com](http://zompist.com) as inspiration, particularly

* [Vocabulary Generator](http://zompist.com/gen.html)
* [Sound Change Applier](http://zompist.com/sca2.html)

## Usage

### General Notes

All phoneme sets are defined as simple vectors:

```
  (def V ["a" "e" "i" "o" "u"])
  (def S ["p" "t" "k"])
```

### Generator

The generator currently provides two methods, ```generate-syllable``` and
```generate-word```. Both take as their arguments a vector containing phonetic
combinations that represent valid syllables, and a map containing the
definitions of those phoneme sets

```generate-syllable``` returns a single syllable, and ```generate-word```
returns a word of between 1 and 4 syllables.

```(generate-syllable ["CVC"] {:C ["b"] :V ["a"]}) #=> "bab"```

```(generate-word ["CVC"] {:C ["b"] :V ["a"]}) #=> "bab"``` or ```"babbab"```
or ```"babbabbab"``` or ```"babbabbabbab"```

### SCA

The SCA provides a single method -- ```apply-rules``` -- which takes as its
arguments a word (string), a vector of rules, and a map of phonemes.

#### How the rules work

Rules take the form "original-string/replacement-string/replacement-context",
      where the location of the original-string is represented by "_"

For example: "c/i/e_t" will replace "c" with "i" anytime it appears between "e"
and "t" : "lector" => "leitor"

Rules can also contain references to phoneme sets:

Given

```
  (def V ["a" "e" "i" "o" "u"])
  (def S ["p" "t" "k"])
  (def Z ["b" "d" "g"])
```

"c/i/V_t" will replace "c" with "i" between any phoneme in the V set and "t"

"lector" => "leitor" and also "locter" => "loiter"

"V//i_i" will remove any phoneme in the V set appearing between two "i"s

"sioi" => "sii"

"S/Z/V_V" will replace any phoneme in the S set with its matching phoneme from
the Z set between any two phonemes in the V set.

"apokeete" => "abogeede"

A more complete example from the tests:

```
  (deftest apply-rules-test
    (testing
      (is (= ["doutor" "fogo" "cidade"]
             (map #(apply-rules %
                ["[sm]//_$" "v//V_V" "u/o/_$" "S/Z/V_V" "c/i/F_t" "c/u/B_t" "ii/i/_"]
                 {:V ["a" "e" "i" "o" "u"]
                 :S ["p" "t" "c"] :Z ["b" "d" "g"]
                 :F ["i" "e"] :B ["o" "u"]}) ["doctor" "focus" "civitatem"])))))
```

## License

Copyright © 2012 Michael Berkowitz

Distributed under the Eclipse Public License, the same as Clojure.
