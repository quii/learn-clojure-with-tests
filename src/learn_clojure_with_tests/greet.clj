(ns learn-clojure-with-tests.greet
  (:require [clojure.string :as str]))

;; Example implementation of the greet function
(defn greet1
  ([] "Hello, World")
  ([name] (str "Hello, " name)))

;; Another way to do a greet function
(defn greet2 [name]
  (str/join " " ["Hello," name]))

;; Greet using fmt
(defn greet3 [name]
  (format "Hello, %s" name))

;; Greet using replace (dumb but fun)
(defn greet4 [name]
  (str/replace "Hello, WAT" #"WAT" name))