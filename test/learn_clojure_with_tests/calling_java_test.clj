(ns learn_clojure_with_tests.calling-java-test
  (:require [clojure.test :refer :all]))

; the dot technique also works with JS when doing clojurescript

(deftest calling-java-test
  (testing "we can call java from clojure"
    (is (= 2 (.length "hi"))))
  (testing "we can call new to make a hashmap"
    (is (= {"foo" 42} (new java.util.HashMap {"foo" 42}))))
  (testing "or use the . form"
    (is (= {"foo" 43} (java.util.HashMap. {"foo" 43})))))

(deftest working-with-objects
  (let [origin (java.awt.Point. 10 20)]
    (testing "we can call methods on objects"
      (is (= 10 (.x origin)))
      (is (= 20 (.y origin))))
    (testing "we can call methods on objects with arguments"
      (.translate origin 5 5)
      (is (= 15 (.x origin)))
      (is (= 25 (.y origin))))
    (testing "we can use setters"
      (set! (.-x origin) 15)
      (println origin)
      (is (= 15 (.x origin))))))

(deftest doto-macro
  (let [some-hashmap (doto (java.util.HashMap.)
                       (.put "foo" 42)
                       (.put "bar" 43))]
    (is (= {"foo" 42 "bar" 43} some-hashmap))))