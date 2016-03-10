(ns training-plan-heroku-app.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.coercions :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [cheshire.core :as json]
            [ring.middleware.json :as ring-json]
            [ring.util.response :as rr]
            [ring.middleware.anti-forgery :as af]
            [training-plan-heroku-app.db :as db]))

(defn jsend-response
  "Use JSend as a application format for all service endpoints."
  [data]
  (rr/response {:status "success"
                :data data}))

;; https://github.com/weavejester/compojure/wiki/Routes-In-Detail
;; https://github.com/weavejester/compojure-example
(defroutes app-routes
  "The defroutes macro returns a Ring handler based on a list of routes."
  (GET "/" [] "Hello World")

  ;; Services
  (GET "/location" []
    (jsend-response (db/get-locations)))

  (GET "/location/:id" [id :<< as-int]
    (jsend-response (db/find-location-by-id id)))

  (POST "/location/:id" [id :<< as-int :as request]
    (let [loc (merge {:id id} (:body request))]
      (jsend-response (db/update-location! loc))))

  (DELETE "/location/:id" [id :<< as-int]
    (jsend-response (db/delete-location! id)))

  (POST "/location" {body :body}
    (jsend-response (db/insert-location! body)))

  ;; This line will serve static requrests out of public directory
  (route/resources "/")

  ;; Not found for all other requests.
  (route/not-found "Not Found"))

(def app
  "app-routes is a Ring handler that handles our desired routes.
  wrap-json-response will convert responses to JSON.
  wrap-defaults uses app-routes as a handler and site-defaults as a config."
  (let [defaults (assoc-in site-defaults [:security :anti-forgery] false)]
    (-> app-routes
        (ring-json/wrap-json-response)
        (ring-json/wrap-json-body {:keywords? true :bigdecimals? true})
        (wrap-defaults defaults))))
