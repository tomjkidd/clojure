(ns training-plan.core
  (:use clojure.test)
  (:require [training-plan.week1.day1 :as day1]
            [training-plan.week1.day2 :as day2]
            [training-plan.week1.day3 :as day3]
            [training-plan.week1.day4 :as day4]
            [training-plan.week1.day5 :as day5])
  (:gen-class))

(defn run-unit-tests
  "Run the unit tests created for training."
  []
  (run-tests 'training-plan.week1.day1
             'training-plan.week1.day2
             'training-plan.week1.day3
             'training-plan.week1.day4
             'training-plan.week1.day5))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (run-unit-tests))
