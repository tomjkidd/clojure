(ns training-plan.week2.day2
  (:use [clojure.test]))

(deftest advancedDestructuring
  "NOTE: & will match with the rest, in this case [3 4 5]
   NOTE: :as will match with the whole thing, in this case [1 2 3 4 5]"
  (is (= [1 2 [3 4 5] [1 2 3 4 5]]
         (let [[a b & c :as d] [1 2 3 4 5]]
           [a b c d]))))

(defn halfTruth1?
  "Take a variable number of booleans, returns true is some but NOT ALL are true."
  [& bs]
  (let [trueCount (count (filter #(= % true) bs))
        totalCount (count bs)]
    (and (> trueCount 0) (not (= trueCount totalCount)))))

(defn halfTruth?
  "This method creates a map for :true and :false to group the results,
then uses the counts to get the result."
  [& bs]
  (let [{ts :true fs :false} (group-by #(keyword (str %)) bs)
        allTrue (= (count fs) 0)
        atLeastOneTrue (> (count ts) 0)]
    (and atLeastOneTrue (not allTrue))))

(deftest aHalfTruth
  (is (= false (halfTruth? false false)))
  (is (= true (halfTruth? true false)))
  (is (= false (halfTruth? true)))
  (is (= true (halfTruth? false true false)))
  (is (= false (halfTruth? true true true)))
  (is (= true (halfTruth? true true true false))))

(defn divisors
  "Get a list of the numbers that divide a number with no remainder"
  [n]
  (filter #(= 0 (rem n %)) (range 1 (+ n 1))))

(defn gcd
  [n1 n2]
  (let [n1-divisors (-> n1 divisors reverse)
        n2-divisors (-> n2 divisors reverse)]
    (loop [remaining n1-divisors]
      (let [head (first remaining)
            tail (rest remaining)]
        (if (some #(= head %) n2-divisors)
          head
          (recur tail))))))

(deftest greatestCommonDivisor
  []
  (is (= (gcd 2 4) 2))
  (is (= (gcd 10 5) 5))
  (is (= (gcd 5 7) 1))
  (is (= (gcd 1023 858) 33)))
