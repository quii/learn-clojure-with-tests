(ns learn_clojure_with_tests.protocols_test
  (:require [clojure.test :refer :all])
  (:import (java.io BufferedReader FileInputStream InputStreamReader)))

(defn gulp [src]
  (let [sb (StringBuilder.)]
    (with-open [reader (-> src
                           FileInputStream.
                           InputStreamReader.
                           BufferedReader.)
                (loop [c (.read reader)]
                  (if (neg? c)
                    (str sb)
                    (do
                      (.append sb (char c))
                      (recur (.read reader)))))])))

;; TODO: demo spit and slurp