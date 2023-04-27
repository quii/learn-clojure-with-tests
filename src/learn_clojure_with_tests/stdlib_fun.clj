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

(defn cj-max [items]
  (loop [largest-value 0 items items]
    (if (empty? items)
      largest-value
      (let [item (first items)
            new-largest-val (max item largest-value)
            rest (rest items)]
        (recur new-largest-val rest)))))

(defn cond-max [items]
  (loop [largest-value 0 items items]
    (cond (empty? items) largest-value
          :else (recur (max (first items) largest-value)
                       (rest items)))))

(defn cj-max-2
  ([items] (cj-max-2 items 0))
  ([items largest]
   (cond (empty? items) largest
         :else (recur (rest items) (max (first items) largest)))))

(defn max-with-reduce [items]
  (reduce max items))

(def some-list [2 100 3 15 101])
(cj-max some-list)
(cj-max-2 some-list)
(max-with-reduce some-list)
(cond-max some-list)
(cond-max [])
