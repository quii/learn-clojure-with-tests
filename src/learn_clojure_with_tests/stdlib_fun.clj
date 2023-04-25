(ns learn_clojure_with_tests.stdlib_fun)

;Takes an expression and a set of test/form pairs. Threads expr (via ->)
;through each form for which the corresponding test
;expression is true. Note that, unlike cond branching, cond-> threading does
;not short circuit after the first true test expression.


; from clojure doc

(cond-> 1                                                   ; we start with 1
        true inc                                            ; the condition is true so (inc 1) => 2
        false (* 42)                                        ; the condition is false so the operation is skipped
        (= 2 2) (* 3))                                      ; (= 2 2) is true so (* 2 3) => 6

(defn cond-example [n]
  (cond
    (> 5 n) (inc n)
    (< 5 n) (* n 42)))

(cond-example 2)
(cond-example 6)

(defn threaded-cond-example [n]
  (cond-> n
          (> n 5) inc
          (> n 10) inc))

(threaded-cond-example 3)
(threaded-cond-example 6)
(threaded-cond-example 11)                                  ;; inc'd twice

;todo: think of an example, that isn't weird an arbitary like this, but not fizzbuzz