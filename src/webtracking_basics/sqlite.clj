(ns webtracking-basics.sqlite
  (:require [clojure.java.jdbc :as j]
            [clojure.tools.logging :as log]))

(def sqlite-db {:classname "org.sqlite.JDBC" :subprotocol "sqlite" :subname "/dev/shm/test.db"})


(try (j/db-do-commands sqlite-db
                       (j/create-table-ddl :event
                                           [[:timestamp :datetime :default :current_timestamp]
                                            [:event :text]]))
     (catch Exception e
       (log/warn (.getMessage e))))

(defn insert-into-db [data]
  (j/insert! sqlite-db :event data))
