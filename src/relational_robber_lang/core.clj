(ns relational-robber-lang.core
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic :only [appendo defne conso conde fresh membero run* ==]]))

(defn robo [decoded encoded]
  (fresh [x xs ys]
    (conde
      [(conso x [] decoded)
       (== [x \o x] encoded)]
      [(conso x xs decoded)
       (membero x (seq "bcdfghjklmnpqrstvwxz"))
       (appendo [x \o x] ys encoded)
       (robo xs ys)]
      [(conso x xs decoded)
       (membero x (seq "aeiouyåäö ."))
       (conso x ys encoded)
       (robo xs ys)])))

;; Encode
(apply str
  (first
    (run* [q]
      (robo (seq "hello world") q))))
; => "hohelollolo wowororloldod"

;; Decode
(apply str
  (first
    (run* [q]
      (robo q (seq "hohelollolo wowororloldod")))))
; => "hello world"
