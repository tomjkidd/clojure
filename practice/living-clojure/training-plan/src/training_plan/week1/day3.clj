(ns training-plan.week1.day3
  (:use clojure.test))

(deftest regularExpressions
  "The regular expression should retain all the captial letters"
  (is (= '("A" "B" "C") (re-seq #"[A-Z]+" "bA1B3Ce")))
  (is (= "ABC" (apply str (re-seq #"[A-Z]+" "bA1B3Ce")))))

(deftest simpleRecursion
  (is (= '(5 4 3 2 1) ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5))))

(deftest recurringTheme
  "NOTE: Loop binds to 2 pieces of data, so 2 are supplied in the recur"
  (is (= [7 6 5 4 3]
         (loop [x 5
                result []]
           (if (> x 0)
             (recur (dec x) (conj result (+ 2 x)))
             result)))))

(deftest rearrangingCode->
  "Uses the thread-first macro"
  (is (= (last (sort (rest (reverse [2 5 4 1 3 6]))))
         (-> [2 5 4 1 3 6] reverse rest sort last)
         5)))

(deftest rearrangingCode->>
  "Uses the thread-last macro"
  (is (= (reduce + (map inc (take 3 (drop 2 [2 5 4 1 3 6]))))
         (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) (reduce +))
         11)))

(deftest forTheWin
  (is (= (for [x (range 40)
               :when (= 1 (rem x 4))]
           x)
         (for [x (iterate #(+ 4 %) 0)
               :let [z (inc x)]
               :while (< z 40)]
           z)
         (for [[x y] (partition 2 (range 20))]
           (+ x y))
         '(1 5 9 13 17 21 25 29 33 37))))
