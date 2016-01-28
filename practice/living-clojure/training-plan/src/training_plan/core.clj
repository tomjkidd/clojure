(ns training-plan.core
  (:gen-class)
  (:use clojure.test)
  (:require [training-plan.week1.day1 :as day1]
            [training-plan.week1.day2 :as day2]))

(defn run-unit-tests
  "Run the unit tests created for training."
  []
  (run-tests 'training-plan.week1.day1
             'training-plan.week1.day2
             'training-plan.week1.day3))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
