(ns learn_clojure_with_tests.preconditions_test
  (:require [clojure.test :refer :all]))

(defn add-apart-from-6 [x y]
  {:pre [(not= 6 x) (not= 6 y)]}
  (+ x y))

(deftest add-apart-from-6-test
         (testing "it blows up if you pass in 6 because of the precondition"
           (is (thrown? AssertionError (add-apart-from-6 6 5))))
         (testing "works for other numbers though"
           (is (= 2 (add-apart-from-6 1 1)))))

(defn cant-add-part-6 [x y]
  {:post [(> 6 %)]}
  (+ x y))

(deftest cant-add-past-6-test
  (testing "it blows up if you pass in 6 because of the postcondition"
    (is (thrown? AssertionError (cant-add-part-6 1 6))))
  (testing "works for other numbers though"
    (is (= 5 (cant-add-part-6 1 4)))))