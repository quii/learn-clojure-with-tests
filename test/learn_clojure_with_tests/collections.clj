(ns learn_clojure_with_tests.collections
  (:require [clojure.test :refer :all]))

(defn can-create-vector [data] (is (= data [0 1 2])))
(defn can-create-vector-of-evens [data] (is (= data [0 2 4 6 8])))

(deftest generating-data "we can generate data in different ways"
                         (can-create-vector (range 3))
                         (can-create-vector (vec (range 3)))
                         (can-create-vector (into [0 1] [2]))
                         (can-create-vector (into [] (range 3)))
                         (can-create-vector (rest [10000000000 0 1 2]))
                         (can-create-vector-of-evens (range 0 10 2))
                         )

(defn can-sum [sum] (is (= 10 (sum (range 5)))))
(deftest reducing-collections "we can reduce collections in different ways"
                              (can-sum (fn [x] (reduce + x)))
                              (can-sum (fn [x] (apply + x))))