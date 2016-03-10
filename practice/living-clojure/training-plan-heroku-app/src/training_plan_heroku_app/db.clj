(ns training-plan-heroku-app.db
  (:require [training-plan-heroku-app.sqlite :as sql]))

; This db section is meant to abstract the fact that the implementation is sqlite
; It also makes it more explicit the way that data is retrieved from the yesql querries.

(defn get-locations
  []
  (sql/get-locations))

(defn find-location-by-id
  [id]
  (let [located (sql/find-location-by-id {:id id})]
    (if (= 1 (count located))
      (first located)
      nil)))

(defn find-location-by-name
  [hashmap]
  (let [located (sql/find-location-by-name hashmap)]
    (if (= 1 (count located))
      (first located)
      nil)))

(defn update-location!
  "loc is a hashmap with :id and :name keys so far."
  [loc]
  (let [updated-count (sql/update-location! loc)
        updated (find-location-by-id (:id loc))]
    (if (= 1 updated-count)
      updated
      nil)))

(defn insert-location!
  "loc is a hashmap with :id and :name keys"
  [loc]
  (let [inserted-count (sql/insert-location! loc)
        inserted (find-location-by-name loc)]
    (if (= 1 inserted-count)
      inserted
      nil)))

(defn delete-location!
  "removes the location from locations"
  [id]
  (let [to-delete (find-location-by-id id)
        delete-count (sql/delete-location! {:id id})]
    (if (= 1 delete-count)
      to-delete
      nil)))
