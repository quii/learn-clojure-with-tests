(ns learn_clojure_with_tests.sequences
  (:require [clojure.test :refer :all] [clojure.set :refer :all]))

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
                some-list))
  (testing "filter"
    (are [data] (= [2] (filter even? data))
                some-vec
                some-list))
  (testing "remove (opposite of filter)"
    (are [data] (= [1 3] (remove even? data))
                some-vec
                some-list))
  )

(deftest predicates "every checks a collection against a predicate"
  (testing "every?"
    (letfn [(less-than-5? [x] (< x 5))]
      (is (every? less-than-5? some-vec))
      (is (every? less-than-5? some-list))))
  (testing "some"
    (is (some even? some-vec))
    (is (some even? some-list)))
  (testing "not-every"
    (is (not-every? even? some-vec))))

(deftest mapping "transforming sequences yay"
  (testing "basic map examples"
    (is (= [2 4 6] (map (fn [x] (* x 2)) some-vec)))
    (is (= [2 3 4] (map inc some-vec)))
    (is (= [3 6 9] (map #(* 3 %) some-vec)))))

(deftest reducing "reducing sequences yay"
  (testing "basic reduce examples"
    (is (= 6 (reduce + some-vec)))))

(deftest sorting
  (testing "sort examples"
    (let [col [2 3 1]]
      (is (= [1 2 3] (sort col)))
      (is (= [3 2 1] (sort > [2 3 1]))))))

(deftest peek-a-boo
        (testing "peek works differently depending on the ds"
          (is (= 3 (peek [vec 1 2 3])))
          (is (= 3 (peek (list 3 2 1))))))

(deftest pop-a-poo
  (testing "pop also works differently depending on the ds"
    (is (= [1 2] (pop [1 2 3])))
    (is (= [2 3] (pop (list 1 2 3))))))

(deftest vectors-as-functions
  (testing "vectors can be used as functions"
    (is (= :a ([:a :b :c] 0)))))

(deftest maps
  (testing "map keys as functions"
    (let [team {:tom "lead", :hilverd "dev", :sharma "ux"}]
      (is (= [:tom :hilverd :sharma] (keys team)))
      (is (= ["lead" "dev" "ux"] (vals team)))
      (is (= "ux" (:sharma team)))
      (is (= false (contains? team :chris)))))
  (testing "manipulating maps"
    (let [scores {:chris 1000 :hugo 2}]
      (is (= {:chris 1000 :hugo 2 :cleo 999} (assoc scores :cleo 999)))
      (is (= {:hugo 2} (dissoc scores :chris)))
      (is (= {:chris 1 :hugo 2} (merge scores {:chris 1}))))))

(deftest sets
  (testing "set operations"
    (let [a #{1 2 3} b #{3 4 5}]
      (is (= #{1 2 3 4 5} (union a b)))
      (is (= #{3} (intersection a b)))
      (is (= #{1 2} (difference a b))))))