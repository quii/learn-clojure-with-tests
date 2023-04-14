(ns learn_clojure_with_tests.specs
  (:require [clojure.spec.alpha :as s]))

(s/def ::rectangle (s/keys :req [::height ::width]))
(s/def ::height number?)
(s/def ::width number?)
(s/def ::area number?)

(s/fdef rectangle-area
        :args (s/cat :rectangle ::rectangle)
        :ret ::area)

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
(s/def ::bowlingrange (s/int-in 0 11))

; collection specs
(s/def ::colofcjs (s/coll-of ::chris-or-james))