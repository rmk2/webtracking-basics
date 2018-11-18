(ns webtracking-basics.core
  (:gen-class)
  (:require [clojure.tools.logging :as log]
            [aleph.http :as http]
            [aleph.netty :refer [AlephServer]]
            ;; Project-interal requires
            [webtracking-basics.handler :refer [reloadable-app]]))

(defn -main
  "We are starting a webserver here..."
  [& args]
  (log/info "Aleph webserver started on port 3000. Enjoy!")
  (.wait-for-close (http/start-server reloadable-app {:port 3000})))
