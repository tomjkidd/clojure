(ns training-plan.week1.day5
  (:require [clojure.test :refer :all]
            [clojure.string :refer [split join]]))

(defn compress [xs]
  (let [reducer (fn [acc cur]
                  (let [prev (last acc)]
                    (if (= cur prev)
                      acc
                      (conj acc cur))))]
    (seq (reduce reducer [] xs))))

(defn compress-alternative [xs]
  "This was a [clever solution](https://github.com/jamiltron/4clojure-solutions/blob/master/solutions.clj) I came across while investigating mine. "
  (map first (partition-by identity xs)))

(deftest compressASequence
  (is (= (apply str (compress "Leeeeeerrroyyy")) "Leroy"))
  (is (= (compress [1 1 2 3 3 2 2 3]) '(1 2 3 2 3)))
  (is (= (compress [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))))

(defn pack [xs]
  (partition-by identity xs))

(deftest packASequence
  (is (= (pack [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3))))
  (is (= (pack [:a :a :b :b :c]) '((:a :a) (:b :b) (:c))))
  (is (= (pack [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))))

(defn dropEveryNth
  [coll n]
  (let [partitioned (partition-all n coll)
        dropper (fn [xs] (if (= (count xs) n) (butlast xs) xs))]
    (apply concat (map dropper partitioned))))

(deftest dropEveryNthItem
  (is (= (dropEveryNth [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8]))
  (is (= (dropEveryNth [:a :b :c :d :e :f] 2) [:a :c :e]))
  (is (= (dropEveryNth [1 2 3 4 5 6] 4) [1 2 3 5 6])))

(deftest introToIterate
  "NOTE: iterate starts with 1 in this case, then generates a sequence of 5 element, where 3 is added to the prev to make the next."
  (is (= [1 4 7 10 13] (take 5 (iterate #(+ 3 %) 1)))))

(defn replicate-n
  [coll n]
  (apply concat (map (fn [x] (take n (iterate identity x))) coll)))

(deftest replicateASequence
  (is (= (replicate-n [1 2 3] 2) '(1 1 2 2 3 3)))
  (is (= (replicate-n [:a :b] 4) '(:a :a :a :a :b :b :b :b)))
  (is (= (replicate-n [4 5 6] 1) '(4 5 6)))
  (is (= (replicate-n [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4])))
  (is (= (replicate-n [44 33] 2) [44 44 33 33])))
