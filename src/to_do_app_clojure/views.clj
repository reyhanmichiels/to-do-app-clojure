(ns to_do_app_clojure.views
  (:require [net.cgrand.enlive-html :as html]))

(html/deftemplate todo-page "public/index.html" [todos]
                  [:ul#todo-list] (html/content
                                    (map (fn [todo]
                                           (html/html
                                             [:li
                                              [:span.description (:description todo)]
                                              "  "
                                              [:input (merge {:type    "checkbox"
                                                              :class   "todo-checkbox"
                                                              :data-id (:id todo)}
                                                             (when (:completed todo) {:checked "checked"}))]]))
                                         todos)))
