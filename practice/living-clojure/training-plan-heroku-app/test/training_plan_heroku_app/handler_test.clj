(ns training-plan-heroku-app.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [cheshire.core :refer :all]
            [training-plan-heroku-app.handler :refer :all]
            [training-plan-heroku-app.db :refer :all]))

(defn name->id
  [name]
  (-> {:name name}
      (find-location-by-name)
      (:id)))

(defn mock-json
  "Convenience method to create JSON request mocks"
  [method
   url
   body]
  (-> (mock/request method url)
      (mock/header "Content-Type" "application/json")
      (mock/body body)))

(defn parse-data
  "Convenience method to get data from a JSend reponse map"
  [response]
  (:data (parse-string (:body response) true)))

;; https://clojure.github.io/clojure/clojure.test-api.html
;; TODO: Make it so that a test database is used!
(defn with-clean-database
  "A test fixture that will put the database into a known state."
  [f]
  (clear-location!)
  (insert-location! {:name "Mercure"})
  (f)
  (clear-location!))

(use-fixtures :each with-clean-database)

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404))))

  (testing "list of locations route"
    (let [response (app (mock-json :get "/location" nil))
          test (:body response)
          data (parse-data response)]
      (is (>= 1 (count data)))))

  (testing "specific location route"
    (let [id (name->id "Mercure")
          response (app (mock-json :get (str "/location/" id) nil))
          data (parse-data response)]
      (is (= "Mercure" (:name data)))))

  (testing "update specific location route"
    (let [response (app (mock-json :post "/location/1" "{\"name\":\"Mercury\"}"))
          data (parse-data response)]
      (is (= (:status response) 200))
      (is (= "Mercury" (:name data)))))

  (testing "insert new location route"
    (let [response (app (mock-json :post "/location" "{\"name\":\"Paddington\"}"))
          data (parse-data response)]
      (is (= (:status response) 200))
      (is (= "Paddington" (:name data)))
      (is (not (nil? (:id data))))))

  (testing "remove specific location route"
    (let [id (name->id "Paddington")
          response (app (mock-json :delete (str "/location/" id) nil))
          data (parse-data response)]
      (is (= (:status response) 200))
      (is (= "Paddington" (:name data)))
      (is (not (nil? (:id data)))))))
