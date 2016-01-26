(ns training-plan.week1.day2
  (:use clojure.test))

(deftest sequencesRest
  (is (= [20 30 40] (rest [10 20 30 40]))))

(deftest introToFunctions
  (is (= 8 ((fn add-five [x] (+ x 5)) 3)))
  (is (= 8 ((fn [x] (+ x 5)) 3)))
  (is (= 8 (#(+ % 5) 3)))
  (is (= 8 ((partial + 5) 3))))

(deftest doubleDown
  (let [double-fn (fn [x] (* x 2))]
    (is (= (double-fn 2) 4))
    (is (= (double-fn 3) 6))
    (is (= (double-fn 11) 22))
    (is (= (double-fn 7) 14))))

(deftest helloWorld
  (let [hw (fn [x] (str "Hello, " x "!"))]
    (is (= (hw "Dave") "Hello, Dave!"))
    (is (= (hw "Jenn") "Hello, Jenn!"))
    (is (= (hw "Rhea") "Hello, Rhea!"))))

(deftest sequencesMaps
  (is (= '(6 7 8) (map #(+ % 5) '(1 2 3)))))

(deftest sequencesFilter
  (is (= '(6 7) (filter #(> % 5) '(3 4 5 6 7)))))

(deftest localBindings
  (is (= 7 (let [x 5] (+ 2 x))))
  (is (= 7 (let [x 3, y 10] (- y x))))
  (is (= 7 (let [x 21] (let [y 3] (/ x y))))))

(deftest letItBe
  (is (= 10 (let [x 7 y 3] (+ x y))))
  (is (= 4 (let [y 3 z 1] (+ y z))))
  (is (= 1 (let [z 1] z))))
