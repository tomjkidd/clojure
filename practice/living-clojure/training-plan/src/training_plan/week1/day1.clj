(ns training-plan.week1.day1
  (:require [clojure.set]))

;; [Google Group](http://bit.ly/4clojure_group)

(def introToStrings
  (= "HELLO WORLD" (.toUpperCase "hello world")))

(def introToLists
  (= (list :a :b :c) '(:a :b :c)))

(def listsConj
  (= '(1 2 3 4) 
     (conj '(2 3 4) 1)
     (conj '(3 4) 2 1)))

(def introToVectors
  (= [:a :b :c]
     (list :a :b :c)
     (vec '(:a :b :c))
     (vector :a :b :c)))

(def vectorsConj
  (= [1 2 3 4] (conj [1 2 3] 4)
     [1 2 3 4] (conj [1 2] 3 4)))

(def introToSets
  (= #{:a :b :c :d} (set '(:a :a :b :c :c :c :c :d :d))
     (clojure.set/union #{:a :b :c} #{:b :c :d})))

(def setsConj
  (= #{1 2 3 4} (conj #{1 4 3} 2)))

(def introToMaps
  (= 20 
     ((hash-map :a 10 :b 20 :c 30) :b)
     (:b {:a 10 :b 20 :c 30})))

(def mapsConj
  (= {:a 1 :b 2 :c 3} (conj {:a 1} [:b 2] [:c 3])))

(def introToSequences
  (= 3 
     (first '(3 2 1))
     (second [2 3 4])
     (last (list 1 2 3))))

(def tests
  [introToStrings
   introToLists
   listsConj
   introToVectors
   vectorsConj
   introToSets
   setsConj
   introToMaps
   mapsConj
   introToSequences
   ])

(defn tests-pass []
  (every? (fn [x] (= true x)) tests))
