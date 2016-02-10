(ns training-plan.week3.day3
  (:require [clojure.test :refer :all]))

(defn rev-interleave
  "This function will take a collection and a number of items and
return the list of lists that when passed to interleave would create
coll."
  [coll num-seqs]
  (-> (reduce (fn [acc cur]
                (conj acc (take-nth num-seqs (drop cur coll))))
              '()
              (range num-seqs))
      (reverse)))

(deftest reverseInterleave
  (is (= (rev-interleave [1 2 3 4 5 6] 2)
         '((1 3 5) (2 4 6))))
  (is (= (rev-interleave (range 9) 3)
         '((0 3 6) (1 4 7) (2 5 8))))
  (is (= (rev-interleave (range 10) 5)
         '((0 5) (1 6) (2 7) (3 8) (4 9)))))

(defn split-by-type
  [xs]
  (vals (group-by (fn [x] (class x)) xs)))

(deftest splitByType
  (is (= (set (split-by-type [1 :a 2 :b 3 :c]))
         #{[1 2 3] [:a :b :c]}))
  (is (= (set (split-by-type [:a "foo" "bar" :b]))
         #{[:a :b] ["foo" "bar"]}))
  (is (= (set (split-by-type [[1 2] :a [3 4] 5 6 :b]))
         #{[[1 2] [3 4]] [:a :b] [5 6]})))
