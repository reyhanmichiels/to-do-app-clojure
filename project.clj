(defproject to-do-app-clojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 ;[compojure "1.7.0"]
                 ;[ring/ring-core "1.11.0"]
                 ;[ring/ring-jetty-adapter "1.11.0"]
                 ;[ring/ring-defaults "0.3.4"]
                 ;[cheshire "6.0.0"]
                 [enlive "1.1.6"]

                 ;; dependency for pedestal and component
                 [io.pedestal/pedestal.service "0.5.10"]
                 [io.pedestal/pedestal.jetty "0.5.10"]
                 [org.clojure/data.json "2.5.0"]
                 [org.slf4j/slf4j-simple "2.0.10"]
                 [com.stuartsierra/component "1.1.0"]]
  :main ^:skip-aot to-do-app-clojure.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot      :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
