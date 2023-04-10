(ns learn_clojure_with_tests.adder-test
  (:require [learn-clojure-with-tests.adder :refer :all]
            [clojure.test :refer :all]))

(defn add-5-contract [add-5-fn]
  (is (= (add-5-fn 5) 10)))

(deftest add-5-test "Test add-5"
                    (add-5-contract add-5))

(deftest add-5-with-a-lambda "Test add-5 with a lambda"
                             (add-5-contract (fn [x] (+ x 5))))

(deftest add-5-with-partial "Test add-5 with a partial"
                            (add-5-contract (partial + 5)))

(deftest add-5-with-threading-macro "Test add-5 with a threading macro"
                                    (add-5-contract add-5-with-threading))