(ns training-plan.week2.day1
  (:require [clojure.string])
  (:use [clojure.test]))

(defn fibonacci-num
  [n]
  (cond (= n 0) 0
        (= n 1) 1
        (= n 2) 1
        :else (+ (fibonacci-num (- n 2)) (fibonacci-num (- n 1)))))

(defn fibonacci-seq
  [n]
  (drop 1 (map #(fibonacci-num %) (range (+ n 1)))))

(deftest fibonacciSequence
  (is (= (fibonacci-seq 3) '(1 1 2)))
  (is (= (fibonacci-seq 6) '(1 1 2 3 5 8)))
  (is (= (fibonacci-seq 8) '(1 1 2 3 5 8 13 21))))

(defn getCaps
  [string]
  (clojure.string/replace string #"[^A-Z]" ""))

(deftest getTheCaps
  (is (= (getCaps "HeLlO, WoRlD!") "HLOWRD"))
  (is (= (empty? (getCaps "nothing"))))
  (is (= (getCaps "$#A(*&987Zf") "AZ")))

(deftest introToSome
  (is (= 6 (some #{2 7 6} [5 6 7 8])))
  (is (= 6 (some #(when (even? %) %) [5 6 7 8]))))

(defn factorial-first-attempt
  [n]
  (cond (= n 0) 1
        :else (* n (factorial-first-attempt (- n 1)))))

(defn factorial
  [x]
  (loop [n x
         acc 1]
    (if (= n 0)
      acc
      (recur (- n 1) (* n acc)))))

(deftest factorialFun
  (is (= (factorial 1) 1))
  (is (= (factorial 3) 6))
  (is (= (factorial 5) 120))
  (is (= (factorial 8) 40320)))

(deftest introToDestructuring
  (is (= [2 4] (let [[a b c d e f g] (range)] [c e]))))
