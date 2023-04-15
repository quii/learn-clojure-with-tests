(ns learn_clojure_with_tests.state_test
  (:require [clojure.test :refer :all]))

(deftest refs-and-transactional-memory
  (testing "refs wrap and protect access to state"
    (let [current-weather (ref "sunny")
          next-weather (ref "rainy")]
      (is (= (deref current-weather) "sunny"))
      (is (= @next-weather "rainy"))
      (dosync
        (ref-set current-weather @next-weather)
        (ref-set next-weather "sunny"))
      (is (= @current-weather "rainy"))
      (is (= @next-weather "sunny"))))
  (testing "use alter to see what the previous state was and update it"
    (let [current-weather (ref "sunny")]
      (dosync
        (alter current-weather (fn [weather] (str weather " and windy"))))
      (is (= @current-weather "sunny and windy"))))
  (testing "design your update functions like conj for a nice way to use alter (your-func thing-that-gets-updated & optional-other-args)"
    (let [messages (ref [])]
      (dosync
        (alter messages conj "hello")
        (alter messages conj "world"))
      (is (= @messages ["hello" "world"])))))