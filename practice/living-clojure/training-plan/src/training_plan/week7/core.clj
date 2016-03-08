(ns training-plan.week7.core
  (:require [yesql.core :refer [defqueries]]
            [clojure.java.jdbc :as jdbc]))

(def config
  {:port 8080
   :bind "127.0.0.1"
   :db {:classname "org.sqlite.JDBC"
        :subprotocol "sqlite"
        :subname "data/db.sqlite"}})

(def db-spec
  (:db config))

(defqueries "../resources/sqlite-init.sql"
  {:connection db-spec})

(defqueries "../resources/sqlite-queryfile.sql"
  {:connection db-spec})

(defn test-queries
  "TODO: Put this into a unit testable format, these were used in the repl to make sure that the function calls worked through yesql as advertised."
  []
  (get-locations)
  (insert-location! {:name "Mercure"})
  (update-location! {:id (:id (find-location-by-name {:name "Mercure"})) :name "Mercury"})
  (insert-location! {:name "Paddington"})
  (delete-location! {:id (:id (find-location-by-name {:name "Paddington"}))})
  (find-location-by-name {:name "Mercure"})
  (get-locations))
