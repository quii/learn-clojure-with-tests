(ns learn_clojure_with_tests.specs
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as stest]))

(s/def ::rectangle (s/keys :req [::height ::width]))
(s/def ::height number?)
(s/def ::width number?)
(s/def ::area number?)

(defn rectangle-area [rect]
  (* (rect :height) (rect :width)))
(s/fdef rectangle-area
        :args (s/cat :rectangle ::rectangle)
        :ret ::area)

; this will run all the fdefs and replace all the symbols it refers to (in this case, rectangle-area)
; instrumenting is not designed for production usage
(stest/instrument (stest/enumerate-namespace `learn_clojure_with_tests.specs))

; so after running this, the following will use the spec to validate things, if you don't, you get usual janky errors
;(rectangle-area {:height "20" :width "four"})

; generative testing
(stest/check 'rectangle-area)

; remember to send to repl for fun
(s/valid? ::area "bob")
(s/valid? ::area 1)

; can define valid enumerated values with sets, which is nice
(s/def ::submission #{:awaiting-qc :awaiting-handling-editor :etc})
(s/valid? ::submission :awaiting-handling-editor)
(s/valid? ::submission :pie)

; logical specs
(s/def ::odd-int (s/and int? odd?))
(s/valid? ::odd-int 5)
(s/valid? ::odd-int 10)
(s/valid? ::odd-int 5.2)

; feels like the combination of specs can be pretty rad
(s/def ::odd-or-42 (s/or ::odd-int #{42}))
(s/valid? ::odd-or-42 42)

; you can label the things to get useful output from conform
(s/def ::chris-or-james (s/or ::chris-matched #{"chris"} ::james-matched #{"james"}))
(s/valid? ::chris-or-james "chris")
(s/conform ::chris-or-james "james")
; => [:learn_clojure_with_tests.specs/james-matched "james"]

; range specs
;TODO ask Nicky (or Petrus), i want to do ::bowling/range but i have troubles importing in the test file
(s/def ::bowlingrange (s/int-in 0 11))

; collection specs
(s/def ::colofcjs (s/coll-of ::chris-or-james))