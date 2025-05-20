(ns to-do-app-clojure.core
  (:require
    [com.stuartsierra.component :as component]
    [to_do_app_clojure.system :as sys]))

(defonce system-instance (atom nil))

(defn start-dev
  []
  (reset! system-instance
          (component/start (sys/new-system))))

(defn stop-dev
  []
  (swap! system-instance
         component/stop))

(defn restart []
  (stop-dev)
  (start-dev))

(defn -main []
  (start-dev))
