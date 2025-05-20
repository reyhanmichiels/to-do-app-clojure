(ns to_do_app_clojure.system
  (:require
    [com.stuartsierra.component :as component]
    [to-do-app-clojure.service :as service]
    [to_do_app_clojure.pedestal :as pedestal]))


(defn new-system []
  (component/system-map
    :pedestal (pedestal/new-pedestal service/service-map)))