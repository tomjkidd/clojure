(ns training-plan.week3.day2
  (:require [clojure.test :refer :all]))

(defn flip
  [f]
  (fn [x y]
    (f y x)))

(deftest flippingOut
  (is (= 3 ((flip nth) 2 [1 2 3 4 5])))
  (is (= true ((flip >) 7 8)))
  (is (= 4 ((flip quot) 2 8)))
  (is (= [1 2 3] ((flip take) [1 2 3 4 5] 3))))

(defn rotate
  "Rotate a sequence by n. Negative numbers to rotate right, positive to rotate left."
  [n coll]
  (let [num-items (count coll)
        to-drop (if (< n 0)
                  (mod (+ num-items n) num-items)
                  (mod n num-items))
        [dropped remaining] (split-at to-drop coll)]
    (concat remaining dropped)))

(deftest rotatingASequence
  (is (= (rotate 2 [1 2 3 4 5]) '(3 4 5 1 2)))
  (is (= (rotate -2 [1 2 3 4 5]) '(4 5 1 2 3)))
  (is (= (rotate 6 [1 2 3 4 5]) '(2 3 4 5 1)))
  (is (= (rotate 1 '(:a :b :c)) '(:b :c :a)))
  (is (= (rotate -4 '(:a :b :c)) '(:c :a :b))))
