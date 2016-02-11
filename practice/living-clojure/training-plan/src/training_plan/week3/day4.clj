(ns training-plan.week3.day4
  (:require [clojure.test :refer :all]))

(defn prime?
  [n]
  (cond (<= n 0) false
        (= 1 n) false
        (= 2 n) true
        :else (let [found-factor (reduce (fn [acc cur]
                                           (if (= 0 (rem n cur))
                                             (or true  acc)
                                             (or false acc)))
                                         false
                                         (range 2 n))]
                (not found-factor))))

(defn primes
  "Generates the first n prime numbers."
  [n]
  (if (<= n 0) [])
  (->> (range)
       (filter prime?)
       (take n)))

(deftest primeNumbers
  (is (= (primes 2) [2 3]))
  (is (= (primes 5) [2 3 5 7 11]))
  (is (= (last (primes 100)) 541)))
