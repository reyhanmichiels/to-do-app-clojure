(ns to_do_app_clojure.state)

(def todos (atom []))

(defn add
  [description]
  (swap! todos
         (fn [todos]
           (let [newID (if (empty? todos)
                         0
                         (apply max (map :id todos)))]
             (conj todos {
                          :id          (inc newID)
                          :description description
                          :completed   false
                          })))))


(defn complete [id completed]
  (swap! todos
         (fn [current-todos]
           (vec
             (map (fn [todo]
                    (if (= (:id todo) id)
                      (assoc todo :completed completed)
                      todo)
                    )
                  current-todos)))))