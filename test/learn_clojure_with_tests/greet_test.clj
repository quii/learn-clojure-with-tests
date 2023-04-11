(ns learn_clojure_with_tests.greet-test
  (:require [learn-clojure-with-tests.greet :refer :all]
            [clojure.test :refer :all]))
(deftest greeting-tests
  (testing "greeting functions"
    (are [greeter] (= "Hello, Chris" (greeter "Chris"))
                   greet1
                   greet2
                   greet3
                   greet4)))

(defn greet-contract-multi-arity [greet-fn]
  (is (= (greet-fn) (str "Hello, World"))))
(deftest greet1-test "Test greet1"
                     (greet-contract-multi-arity greet1))
