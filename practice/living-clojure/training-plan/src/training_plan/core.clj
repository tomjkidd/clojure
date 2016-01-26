(ns training-plan.core
  (:gen-class)
  (:require [training-plan.week1.day1 :as day1]))

(defn all-tests-pass []
  (every? (fn [x] (= x true)) 
          [(day1/tests-pass)]))

(let [{a :a, b :b, c :c, :as m :or {a 2 b 3}} {:a 5 :c 6}]
  [a b c m])

(let [{:keys [fred]} {:fred "f"}]
  fred)

(def test-address
  {:street-address "123 Test Lane"
   :city "Testerville"
   :state "TX"})

((fn [[a b] c] (let [name (str a " " b) {:keys [street-address city state]} test-address] (interpose ", " [name street-address city state]))) ["Test" "Testerson"] test-address)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
