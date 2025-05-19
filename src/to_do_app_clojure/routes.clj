(ns to_do_app_clojure.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :refer [redirect]]
            [to_do_app_clojure.state :as state]
            [to_do_app_clojure.views :as views]
            [cheshire.core :as json]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
           (GET "/" [] (views/todo-page @state/todos))
           (POST "/todo" [description]
             (do
               (state/add description)
               (redirect "/")))
           (PUT "/todo/:id" [id :as request]
             (let [body (json/parse-string (slurp (:body request)) true)
                   completed (:completed body)]
               (do
                 (state/complete (Integer/parseInt id) completed)
                 (redirect "/")))))
(route/not-found "not found")

(def app
  (wrap-defaults app-routes (assoc-in site-defaults [:security :anti-forgery] false)))

