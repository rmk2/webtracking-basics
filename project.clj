(defproject webtracking-basics "0.1.0-SNAPSHOT"
  :description "A quick practical introduction to webtracking"
  :url "https://bitbucket.org/ryko/webtracking-basics/"
  :license {:name "GNU General Public License v3"
            :url "https://www.gnu.org/licenses/gpl-3.0.txt"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.logging "0.4.1"]
                 [org.slf4j/slf4j-log4j12 "1.7.1"]
                 [log4j/log4j "1.2.17" :exclusions [javax.mail/mail
                                                    javax.jms/jms
                                                    com.sun.jmdk/jmxtools
                                                    com.sun.jmx/jmxri]]
                 [ring "1.7.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-anti-forgery "1.3.0"]
                 [ring-logger "1.0.1"]
                 [bk/ring-gzip "0.3.0"]
                 [hiccup "1.0.5"]
                 [compojure "1.6.1"]
                 [aleph "0.4.6"]
                 [org.clojure/java.jdbc "0.7.8"]
                 [org.xerial/sqlite-jdbc "3.25.2"]]
  ;; :plugins [[lein-ring "0.12.4"]]
  ;; :ring {:handler webtracking-basics.handler/app}
  :main ^:skip-aot webtracking-basics.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.3.0"]]}})
