(ns training-plan-heroku-app.sqlite
  (:require [yesql.core :refer [defqueries]]))

(def db-config
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname "data/db.sqlite"})

(defqueries "../resources/sqlite-init.sql"
  {:connection db-config})

(defqueries "../resources/sqlite-queryfile.sql"
  {:connection db-config})
