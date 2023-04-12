(ns learn_clojure_with_tests.sequences
  (:require [clojure.test :refer :all]))

;; sequences are the mega-abstraction around lots of data types. It has a rich library of functions that work with them.

(defn can-create-vector [data] (is (= data [0 1 2])))
(defn can-create-vector-of-evens [data] (is (= data [0 2 4 6 8])))

(deftest generating-vectors "we can generate vectors in different ways"
                         (can-create-vector (range 3))
                         (can-create-vector (vec (range 3)))
                         (can-create-vector (into [0 1] [2]))
                         (can-create-vector (into [] (range 3)))
                         (can-create-vector (rest [10000000000 0 1 2]))
                         (can-create-vector-of-evens (range 0 10 2))
                         )

(defn can-sum [sum] (is (= 10 (sum (range 5)))))
(deftest reducing-sequences "we can reduce sequences in different ways"
                              (can-sum (fn [x] (reduce + x)))
                              (can-sum (fn [x] (apply + x))))

;; see here https://clojure.org/api/cheatsheet
(def some-vec [1 2 3])
(def some-list `(1 2 3))
(def some-set #{1 2 3})
(deftest count-test "we can count all sequences"
                    (is (= 3 (count some-vec)))
                    (is (= 3 (count some-list)))
                    (is (= 3 (count some-set))))

(defn can-check-is-not-empty [col]
  (is (not-empty col)))

(deftest not-empty-test "we can check if any sequence is not empty"
                        (can-check-is-not-empty some-vec)
                        (can-check-is-not-empty some-list)
                        (can-check-is-not-empty some-set))