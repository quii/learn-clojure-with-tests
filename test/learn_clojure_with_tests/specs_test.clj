(ns learn_clojure_with_tests.specs-test
  (:require [clojure.spec.alpha :as s]
            [learn_clojure_with_tests.specs :refer :all]
            [clojure.test :refer :all]))

(deftest validating-specs
         (testing "we can create specs and then validate data"
                  (is (s/valid? :learn_clojure_with_tests.specs/area 2))
                  (not (s/valid? :learn_clojure_with_tests.specs/area "two"))))