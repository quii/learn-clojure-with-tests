;; add 5 ns
(ns learn-clojure-with-tests.adder)

(defn add-5 [x]
  (+ x 5))

(defn add-5-with-threading [x]
  (-> x inc inc inc inc inc))