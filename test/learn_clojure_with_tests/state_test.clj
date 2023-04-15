(ns learn_clojure_with_tests.state_test
  (:require [clojure.test :refer :all]
            [learn-clojure-with-tests.state :refer :all]))

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
      (is (= @messages ["hello" "world"]))))
  (testing "alter locks, if you dont care about order, use commute (it has the same API at alter")
    (let [messages (ref [])]
      (dosync
        (commute messages conj "hello")
        (commute messages conj "world"))
      (is (= @messages ["hello" "world"]))))

(deftest adding-validation-to-refs
  (testing "like databases we can add validation to transactions"
    (let [weather-list (new-weather-list)]
      (is (= (deref weather-list) []))
      (dosync
        (alter weather-list add-weather :sunny))
      (is (= (deref weather-list) [:sunny]))
      (is (thrown? Exception (dosync
                               (alter weather-list add-weather :raining-cats-and-dogs))))
      (is (= @weather-list [:sunny])))))