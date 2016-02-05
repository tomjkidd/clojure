(ns training-plan.week2.day5
  (:require [clojure.test :refer :all]))

(defn pascal
  "Will produce a Pascal's Triangle list with n elements"
  [n]
  (cond (= n 1) [1]
        :else
        (let [previous-row (pascal (- n 1))
              current-inner (loop [acc [] remaining previous-row]
                              (let [cur (first remaining)
                                    rem (rest remaining)
                                    next (first rem)]
                                (if (nil? next)
                                  acc
                                  (recur (conj acc (+ cur next)) rem))))]
          (into [] (concat [1] current-inner [1])))))


(deftest pascalsTriangle
  (is (= (pascal 1) [1]))
  (is (= (map pascal (range 1 6))
         [[1]
          [1 1]
          [1 2 1]
          [1 3 3 1]
          [1 4 6 4 1]]))
  (is (= (pascal 11) [1 10 45 120 210 252 210 120 45 10 1])))
