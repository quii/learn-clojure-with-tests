(ns learn_clojure_with_tests.specs
  (:require [clojure.spec.alpha :as s]))

(s/def ::rectangle (s/keys :req [::height ::width]))
(s/def ::height number?)
(s/def ::width number?)
(s/def ::area number?)

(s/fdef rectangle-area
        :args (s/cat :rectangle ::rectangle)
        :ret ::area)

; remember to send to repl for fun
(s/valid? ::area "bob")
(s/valid? ::area 1)