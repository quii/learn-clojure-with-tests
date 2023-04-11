(ns learn-clojure-with-tests.greet
  (:require [clojure.string :as str]))

(defn greet1
  "Example impl of greet function with multiple arity"
  ([] "Hello, World")
  ([name] (str "Hello, " name)))

(defn greet2
  "Example of greet function using str/join"
  [name]
  (str/join " " ["Hello," name]))

(defn greet3
  "Example of greet function using format"
  [name]
  (format "Hello, %s" name))

(defn greet4
  "Example of greet function using string replace (dumb but fun)"
  [name]
  (str/replace "Hello, WAT" #"WAT" name))