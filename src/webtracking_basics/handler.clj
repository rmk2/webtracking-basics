(ns webtracking-basics.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.logger :as logger]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.anti-forgery :refer [wrap-anti-forgery]]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.gzip :refer [wrap-gzip]]
            [ring.middleware.reload :refer [wrap-reload]]
            ;; Project-interal requires
            [webtracking-basics.pages :refer :all]))

(defroutes app-routes
  (GET "/" [] index)
  (GET "/about" [] about)
  (GET "/form" [] form)
  (POST "/form" [] form)
  (GET "/tracking" [] tracking)
  (route/resources "/")
  (route/files "/")
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      (wrap-defaults site-defaults)
      (wrap-session)
      (wrap-content-type)
      (wrap-params)
      (wrap-keyword-params)
      (wrap-gzip)
      (logger/wrap-log-request-start)
      (logger/wrap-log-request-params)))

(def reloadable-app
  (-> app
      (wrap-reload)))
