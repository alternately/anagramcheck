(ns anagram.core
  (:gen-class))

(defn prime?
  "takes a single argument and returns true iff it is prime"
  ([x]
   (prime? x 2)
   )
  

  ([x n] 
   (if  (> n (Math/sqrt x))
     (if (= x 1)
       false
       true)
     (if (= (int (/ (float x) (float n))) (/ (int x) (int n)))
       false
       (prime? x (inc n))
       )
     )
   )
  )

(defn primes
  "provides a lazy sequence of primes"
  ([]
   (primes 2))
  ([n]
   (lazy-seq (if (prime? n)
               (cons n (primes (inc n)))
               (primes (inc n))
               )
             )
   )
  )

; alphabet is a pregenerated alphabet of characters to be assigned primes. If you need a character that isn't included, add it here. By default, contains characters a-z A-Z 0-9 .,/ _-*

(def alphabet '("a" "b" "c" "d" "e" "f" "g" "h" "i" "j" "k" "l" "m" "n" "o" "p" "q" "r" "s" "t" "u" "v" "w" "x" "y" "z" "A" "B" "C" "D" "E" "F" "G" "H" "I" "J" "K" "L" "M" "N" "O" "P" "Q" "R" "S" "T" "U" "V" "W" "X" "Y" "Z" "0" "1" "2" "3" "4" "5" "6" "7" "8" "9" "." "," "/" " " "_" "-" "*"))

; alphamap is the map that allows encoding between characters and numbers
(def alphamap (zipmap alphabet (take (count alphabet) (primes))))
  
(defn primeencode
  "takes a string of characters from alphabet and encodes the characters as prime numbers, and returns the product of all the primes"
  [input]
  (let [output
        (for [n (range (count input))
              :let [y (alphamap (subs input n (inc n)))]]
          y)]
    (reduce * output)
    )
  )

(defn anagram?
  "takes two strings as inputs, and returns true if they are anagrams of each other"
  [str1 str2]
  (= (primeencode str1) (primeencode str2)))


(defn -main
  []
  (println "What is the first word to check?")
  (let [a   (read-line)]
    (println "What is the second word to check?")
    (let [b  (read-line)]
      (if (anagram? a b)
        (println "they are anagrams")
        (println "they are not anagrams")))))
