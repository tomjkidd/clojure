(ns training-plan.week2.day4
  (:require [clojure.set :as set])
  (:use [clojure.test]))

(defn sym-diff
  "Takes the symmetric difference of two sets, which is the set of items that
belong to one but NOT both sets."
  [s1 s2]
  (let [d1 (set/difference s1 s2)
        d2 (set/difference s2 s1)]
    (->> #{} (into  d1) (into d2))))

(deftest symmetricDifference
  (is (= (sym-diff #{1 2 3 4 5 6} #{1 3 5 7})
         #{2 4 6 7}))
  (is (= (sym-diff #{:a :b :c} #{})
         #{:a :b :c}))
  (is (= (sym-diff #{} #{4 5 6})
         #{4 5 6}))
  (is (= (sym-diff #{[1 2] [2 3]} #{[2 3] [3 4]})
         #{[1 2] [3 4]})))

(defn factors-of-x-to-n
  "Returns the ordered set of factors of x up to, and including, n."
  [x n]
  (loop [acc (sorted-set) cur x]
    (if (> cur n)
      acc
      (recur (conj acc cur) (+ cur x)))))

(defn lcm-first-attempt
  "Calculates the least common multiple. Accepts integers or ratios.
NOTE: At very worst it is the product of all the inputs.
NOTE: This solution fell down for ratios."
  [& xs]
  (let [ceiling (reduce * 1 xs)
        factors (map #(factors-of-x-to-n % ceiling) xs)
        all-factors (reduce set/union factors)
        common-to-all (reduce #(set/intersection %1 %2) all-factors factors)]
    (reduce min (into [] common-to-all))))

(defn capture-min
  [acc cur]
  (if (< (:cur cur) (:cur acc))
    cur
    acc))

(defn step-forward-min
  [step-maps]
  (let [min-map (reduce capture-min step-maps)]
    (map (fn [m]
           (if (= (:index min-map) (:index m))
             (assoc m :cur (+ (:cur m) (:step m)))
             m))
         step-maps)))

(defn lcm
  "By using a simple hashmap to keep track of :step, :cur, and :index,
the map with the minimum value for :cur will be stepped. If all :cur values
map to the same value, that is the condition that causes termination.

NOTE: If there is no lcm, this will run in an infinite loop."
  [& xs]
  (loop [cur-factors (map-indexed (fn [idx x] {:step x :cur x :index idx}) xs)]
    (if (every? #(= (:cur (first cur-factors)) (:cur %)) cur-factors)
      (:cur (first cur-factors))
      (recur (step-forward-min cur-factors)))))

(deftest leastCommonMultiple
  (is (== (lcm 2 3) 6))
  (is (== (lcm 5 3 7) 105))
  (is (== (lcm 1/3 2/5) 2))
  (is (== (lcm 3/4 1/6) 3/2))
  (is (== (lcm 7 5/7 2 3/5) 210)))
