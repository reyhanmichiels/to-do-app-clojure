(ns to-do-app-clojure.service
  (:require
    [io.pedestal.http :as http]
    [io.pedestal.http.body-params :as body-params]
    [io.pedestal.http.route :as route]
    [to_do_app_clojure.views :as views]
    [to_do_app_clojure.state :as state]))

(defn ping-handler
  [request]
  {:status 200 :body "pong"})

(defn todo-page-handler
  [request]
  {:status  200
   :headers {"Content-Type"            "text/html"
             "Content-Security-Policy" "default-src 'self'; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline'"}
   :body    (apply str (views/todo-page @state/todos))})

(defn add-todo-handler
  [request]
  (let [description
        (get-in request [:form-params :description])]
    (state/add description)
    {
     :status  302
     :headers {"Location" "/"}
     })
  )

(defn complete-todo-handler
  [request]
  (let [completed
        (get-in request [:json-params :completed])
        todo-id
        (get-in request [:path-params :todo-id])]
    (state/complete (Integer/parseInt todo-id) completed)
    {
     :status  302
     :headers {"Location" "/"}
     })
  )

(def routes
  (route/expand-routes
    #{
      ["/ping" :get ping-handler :route-name :ping]
      ["/" :get todo-page-handler :route-name :todo-page]
      ["/todo" :post [(body-params/body-params) add-todo-handler] :route-name :add-todo]
      ["/todo/:todo-id" :put [(body-params/body-params) complete-todo-handler] :route-name :complete-todo]
      }))

(def csp-headers
  {:name  ::csp-headers
   :enter (fn [context]
            (let [response (or (:response context) {})]
              (assoc context :response
                             (update response :headers merge
                                     {"Content-Security-Policy"
                                      "default-src 'self'; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline'"}))))})

(def service-map
  {
   ::http/routes       routes
   ::http/type         :jetty
   ::http/port         8080
   ::http/join?        false
   })