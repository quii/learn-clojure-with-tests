(ns learn_clojure_with_tests.specs-test
  (:require [clojure.spec.alpha :as s]
            [learn_clojure_with_tests.specs :refer :all]
            [clojure.test :refer :all]))

(deftest validating-specs
  (testing "we can create specs and then validate data"
    (is (s/valid? :learn_clojure_with_tests.specs/area 2))
    (is (not (s/valid? :learn_clojure_with_tests.specs/area "two"))))
  (testing "enumerated values"
    (is (s/valid? :learn_clojure_with_tests.specs/submission :awaiting-handling-editor))
    (is (not (s/valid? :learn_clojure_with_tests.specs/submission :poo))))
  (testing "logical specs"
    (is (s/valid? :learn_clojure_with_tests.specs/odd-int 9))
    (is (not (s/valid? :learn_clojure_with_tests.specs/odd-int 2))))
  (testing "labelling"
    (is (= [:learn_clojure_with_tests.specs/james-matched "james"]
           (s/conform :learn_clojure_with_tests.specs/chris-or-james "james"))))
  (testing "ranges"
    (is (s/valid? :learn_clojure_with_tests.specs/bowlingrange 10))
    (is (not (s/valid? :learn_clojure_with_tests.specs/bowlingrange 20))))
  (testing "collections"
    (is (s/valid? :learn_clojure_with_tests.specs/colofcjs ["chris" "chris" "james"]))
    (is (not (s/valid? :learn_clojure_with_tests.specs/colofcjs ["chris" "james" "nicky"])))))