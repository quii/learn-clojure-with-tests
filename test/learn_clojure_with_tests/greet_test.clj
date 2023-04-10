(ns learn_clojure_with_tests.greet-test
  (:require [learn-clojure-with-tests.greet :refer :all]
            [clojure.test :refer :all]))

(defn greet-contract [greet-fn]
  (let [name "Chris"]
    (is (= (greet-fn name) (str "Hello, " name)))))

(defn greet-contract-multi-arity [greet-fn]
  (is (= (greet-fn) (str "Hello, World"))))
(deftest greet1-test "Test greet1"
                     (greet-contract greet1) (greet-contract-multi-arity greet1))
(deftest greet2-test "Test greet2" (greet-contract greet2))
(deftest greet3-test "Test greet3" (greet-contract greet3))
(deftest greet4-test "Test greet3" (greet-contract greet4))