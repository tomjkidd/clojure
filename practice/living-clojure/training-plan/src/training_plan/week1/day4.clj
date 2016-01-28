(ns training-plan.week1.day4
  (:use clojure.test))

(deftest penultimateElement
  (let [penultimate (fn [xs] (-> xs butlast last))]
    (is (= (penultimate (list 1 2 3 4 5)) 4))
    (is (= (penultimate ["a" "b" "c"]) "b"))
    (is (= (penultimate [[1 2] [3 4]])))))

(deftest sumItAllUp
  (let [sum (fn [xs] (reduce + xs))]
    (is (= (sum [1 2 3]) 6))
    (is (= (sum (list 0 -2 5 5)) 8))
    (is (= (sum #{4 2 1}) 7))
    (is (= (sum '(0 0 -1)) -1))
    (is (= '(1 10 3)) 14)))

(deftest findTheOddNumbers
  (let [odd-filter (fn [xs] (filter odd? xs))]
    (is (= (odd-filter #{1 2 3 4 5}) '(1 3 5)))
    (is (= (odd-filter [4 2 1 6]) '(1)))
    (is (= (odd-filter [2 2 4 6]) '()))
    (is (= (odd-filter [1 1 1 3]) '(1 1 1 3)))))

(deftest palindromeDetector
  (let [palindrome? (fn [xs] (= (seq xs) (reverse xs)))]
    (is (false? (palindrome? '(1 2 3 4 5))))
    (is (true? (palindrome? "racecar")))
    (is (true? (palindrome? [:foo :bar :foo])))
    (is (true? (palindrome? '(1 1 3 3 1 1))))
    (is (false? (palindrome? '(:a :b :c))))))

(deftest duplicateASequence
  (let [dup (fn [xs] (mapcat (fn [x] [x x]) xs))]
    (is (= (dup [1 2 3]) '(1 1 2 2 3 3)))
    (is (= (dup [:a :a :b :b]) '(:a :a :a :a :b :b :b :b)))
    (is (= (dup [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4])))))


