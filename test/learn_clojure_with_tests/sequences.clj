(ns learn_clojure_with_tests.sequences
  (:require [clojure.test :refer :all]))

;; sequences are the mega-abstraction around lots of data types. It has a rich library of functions that work with them.

(defn can-create-sequence [data] (is (= data [0 1 2])))
(defn can-create-sequence-of-evens [data] (is (= data [0 2 4 6 8])))

(deftest generating-vectors "we can generate vectors in different ways"
  (can-create-sequence (range 3))
  (can-create-sequence (vec (range 3)))
  (can-create-sequence (vector 0 1 2))
  (can-create-sequence (into [0 1] [2]))
  (can-create-sequence (into [] (range 3)))
  (can-create-sequence (rest [10000000000 0 1 2]))
  (can-create-sequence-of-evens (range 0 10 2))
  (testing "note conj adds to the end"
    (can-create-sequence (conj [0 1] 2))
    (can-create-sequence (conj [0] 1 2)))
  )

(deftest can-create-lists "we can generate lists in different ways"
  (can-create-sequence (list 0 1 2))
  (can-create-sequence `(0 1 2))
  (testing "conj adds to the start with lists"
    (can-create-sequence (conj (list 1 2) 0))
    (can-create-sequence (conj (list 2) 1 0))))

(defn can-sum [sum] (is (= 10 (sum (range 5)))))
(deftest reducing-sequences "we can reduce sequences in different ways"
  (can-sum (fn [x] (reduce + x)))
  (can-sum (fn [x] (apply + x))))

;; see here https://clojure.org/api/cheatsheet
(def some-vec [1 2 3])
(def some-list `(1 2 3))
(def some-set #{1 2 3})

(deftest useful-seq-functions-test "there are many operations common to seq, here are a few"
  (testing "count"
    (are [data] (= 3 (count data))
                some-vec
                some-list
                some-set))
  (testing "is-empty"
    (is (empty? []))
    (not (empty? some-vec))
    (is empty? #{})
    (not (empty? some-set)))
  (testing "first"
    (are [data] (= 1 (first data))
                some-vec
                some-list
                some-set))
  (testing "rest"
    (are [data] (= [2 3] (rest data))
                some-vec
                some-list)))