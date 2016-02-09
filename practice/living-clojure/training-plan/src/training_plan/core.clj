(ns training-plan.core
  (:use clojure.test)
  (:require [training-plan.week1.day1]
            [training-plan.week1.day2]
            [training-plan.week1.day3]
            [training-plan.week1.day4]
            [training-plan.week1.day5]
            [training-plan.week2.day1]
            [training-plan.week2.day2]
            [training-plan.week2.day3]
            [training-plan.week2.day4]
            [training-plan.week2.day5]
            [training-plan.week3.day1]
            [training-plan.week3.day2])
  (:gen-class))

(defn run-unit-tests
  "Run the unit tests created for training."
  []
  (run-tests 'training-plan.week1.day1
             'training-plan.week1.day2
             'training-plan.week1.day3
             'training-plan.week1.day4
             'training-plan.week1.day5
             'training-plan.week2.day1
             'training-plan.week2.day2
             'training-plan.week2.day3
             'training-plan.week2.day4
             'training-plan.week2.day5
             'training-plan.week3.day1
             'training-plan.week3.day2))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run-unit-tests))
