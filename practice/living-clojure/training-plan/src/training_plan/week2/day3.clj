(ns training-plan.week2.day3
  (:require [clojure.math.numeric-tower :as math])
  (:use [clojure.test]))

(defn wrapExpt
  [n]
  (fn [x] (math/expt x n)))

(deftest simpleClosures
  (is (= 256
         ((wrapExpt 2) 16)
         ((wrapExpt 8) 2)))
  (is (= [1 8 27 64] (map (wrapExpt 3) [1 2 3 4])))
  (is (= [1 2 4 8 16] (map #((wrapExpt %) 2) [0 1 2 3 4]))))

(defn pair-with
  [x ys]
  (loop [acc #{} cur (first ys) remaining (rest ys)]
    (if (nil? cur)
      acc
      (recur (conj acc [x cur]) (first remaining) (rest remaining)))))

(pair-with "a" ["1" "2" "3"])

(defn cartesian-product
  [s1 s2]
  (let [l1 (seq s1)
        l2 (seq s2)
        l1-pairs (map #(pair-with % l2) l1)]

    (reduce #(apply conj %1 %2) #{} l1-pairs)))

(deftest cartesianProduct
  (is (= (cartesian-product #{"ace" "king" "queen"} #{"spade" "heart" "diamond" "club"})
         #{["ace" "spade"] ["ace" "heart"] ["ace" "diamond"] ["ace" "club"]
           ["king" "spade"] ["king" "heart"] ["king" "diamond"] ["king" "club"]
           ["queen" "spade"] ["queen" "heart"] ["queen" "diamond"] ["queen" "club"]
  }))
  (is (= (cartesian-product #{1 2 3} #{4 5})
         #{[1 4] [1 5]
           [2 4] [2 5]
           [3 4] [3 5]}))
  (is (= 300 (count (cartesian-product (into #{} (range 10))
                                       (into #{} (range 30)))))))
