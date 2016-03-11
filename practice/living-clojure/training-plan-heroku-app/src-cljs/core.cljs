(ns training-plan-heroku-app.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [clojure.browser.repl :as repl]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [enfocus.core :as ef]
            [enfocus.events :as ev]
            [enfocus.effects :as ee]))

(defn say-goodbye
  []
  (ef/at
   "#status" (ee/fade-out 5000)
   "#button1" (ee/fade-out 500)))

(defn ^:export init []
  (repl/connect "http://localhost:9000/repl")
  (go
    (let [response (<! (http/get "/location"))
          body (:body response)]
      (js/alert (:body response))
      (ef/at "#status" (ef/do->
                        (ef/content (:status body))
                        (ef/set-style :font-size "500%")))
      (ef/at "#button1" (ev/listen :click #(say-goodbye))))))
