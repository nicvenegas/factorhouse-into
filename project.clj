(defproject io.factorhouse/into "1.0.0"

  :description "A hurdle of the technical variety"

  :dependencies [[org.clojure/clojure "1.12.0"]
                 [org.clojure/tools.reader "1.5.2"]
                 [clj-kondo "2025.04.07"]]

  :aliases {"kondo" ["run" "-m" "clj-kondo.main" "--lint" "src:test" "--parallel"]})
