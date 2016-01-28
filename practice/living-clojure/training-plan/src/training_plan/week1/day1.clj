(ns training-plan.week1.day1
  (:require [clojure.set])
  (:use [clojure.test]))

;; [Google Group](http://bit.ly/4clojure_group)

(deftest introToStrings
  (is (= "HELLO WORLD" (.toUpperCase "hello world"))))

(deftest introToLists
  (is (= (list :a :b :c) '(:a :b :c))))

(deftest listsConj
  (is (= '(1 2 3 4) 
         (conj '(2 3 4) 1)
         (conj '(3 4) 2 1))))

(deftest introToVectors
  (is (= [:a :b :c]
         (list :a :b :c)
         (vec '(:a :b :c))
         (vector :a :b :c))))

(deftest vectorsConj
  (is (= [1 2 3 4] (conj [1 2 3] 4)
         [1 2 3 4] (conj [1 2] 3 4))))

(deftest introToSets
  (is (= #{:a :b :c :d} (set '(:a :a :b :c :c :c :c :d :d))
         (clojure.set/union #{:a :b :c} #{:b :c :d}))))

(deftest setsConj
  (is (= #{1 2 3 4} (conj #{1 4 3} 2))))

(deftest introToMaps
  (is (= 20 
         ((hash-map :a 10 :b 20 :c 30) :b)
         (:b {:a 10 :b 20 :c 30}))))

(deftest mapsConj
  (is (= {:a 1 :b 2 :c 3} (conj {:a 1} [:b 2] [:c 3]))))

(deftest introToSequences
  (is (= 3 
         (first '(3 2 1))
         (second [2 3 4])
         (last (list 1 2 3)))))
