(defproject training-plan "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [yesql "0.5.2"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [org.clojure/java.jdbc "0.4.2"]]
  :main ^:skip-aot training-plan.week7.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
