;; add 5 ns
(ns learn-clojure-with-tests.adder)

(defn add-5 [x]
  (+ x 5))

(defn add-5-with-threading [x]
  (-> x inc inc inc inc inc))

(defn add-1
  "adds 1"
  [x]
  (+ x 1))

(defn multiply-by-2
  [x] (* x 2))

(defn print-number
  [num]
  (format "This number is %d" num))

;repl notes
;all are roughly around shift+option
;L will load file
;M will sync file
;P will send current form and run it
;R sets namespace to current file

(+ 2 2 2)