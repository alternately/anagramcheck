(ns anagram.core-test
  (:require [clojure.test :refer :all]
            [anagram.core :refer :all]))

(deftest primecheck-test
  (testing "checks the functionality of prime?")
  (is (= false (prime? 1)))
  (is (= true (prime? 2)))
  (is (= true (prime? 3)))
  (is (= false (prime? 4)))
  (is (= false (prime? 16)))
  (is (= true (prime? 19))))

(deftest primesseq-test
  (testing "checks the functionality of primes")
  (is (= '(2 3 5 7 11 13 17) (take 7 (primes)))
      )
  )
(deftest primeencode-test
  (testing "checks the functionality of primeencode")
  (is (= 30 (primeencode "abc"))))

(deftest anagram-test
  (testing "checks the functionality of anagram?")
  (is (= true (anagram? "abc" "cba")))
  (is (= true (anagram? "aiden" "diane")))
  (is (= false (anagram? "TRISTAn" "tristan")))
  (is (= false (anagram? "abc" "nope")))
  )
