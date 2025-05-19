(ns to-do-app-clojure.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [to_do_app_clojure.routes :refer [app]]))

(defn -main []
  (run-jetty app {:port 3000 :join? false?}))
