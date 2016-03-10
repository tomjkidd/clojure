(defproject cheshire-cat "0.1.0-SNAPSHOT"
  :description "living-clojure Week7 Heroku project."
  :url ""
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [cheshire "5.5.0"]
                 [ring/ring-json "0.4.0"]
                 [javax.servlet/servlet-api "2.5"]
                 [yesql "0.5.2"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [org.clojure/java.jdbc "0.4.2"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler training-plan-heroku-app.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
