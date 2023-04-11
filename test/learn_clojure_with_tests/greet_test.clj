(ns learn_clojure_with_tests.greet-test
  (:require [learn-clojure-with-tests.greet :refer :all]
            [clojure.test :refer :all]))

(defn can-greet
  "this is a contract for a greet function, that other ns can import to verify"
  [greet-fn]
  (let [name "Chris"]
    (is (= (greet-fn name) (str "Hello, " name)))))

(deftest greeting-tests
  (testing "greeting functions"
    (are [greeter] (can-greet greeter)
                   greet1
                   greet2
                   greet3
                   greet4)))

(defn greet-contract-multi-arity [greet-fn]
  (is (= (greet-fn) (str "Hello, World"))))
(deftest greet-with-arity-test "Test greet1"
                     (testing "greeting with multiple arity"
                       (are [greeter] (greet-contract-multi-arity greeter)
                                      greet1
                                      greet2)))
