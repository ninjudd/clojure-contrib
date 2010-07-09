(ns clojure.contrib.test-bean
  (:gen-class :name clojure.contrib.TestBean
              :init init
              :state state
              :methods [[getFoo    [] String ] [setFoo    [String]  void]
                        [getBarBaz [] Integer] [setBarBaz [Integer] void]])
  (:use clojure.test clojure.contrib.bean))

(defn -init []
  [[] (atom {})])

(defn -getFoo [this]
  (:foo @(.state this)))

(defn -setFoo [this #^String val]
  (swap! (.state this) assoc :foo val))

(defn -getBarBaz [this]
  (:bar-baz @(.state this)))

(defn -setBarBaz [this #^Integer val]
  (swap! (.state this) assoc :bar-baz val))

(deftest test-update-bean
  (let [b (clojure.contrib.TestBean.)]
    (is (= clojure.contrib.TestBean (class (update-bean b {:foo 43}))))
    (is (= "43" (.getFoo b)))

    (is (= clojure.contrib.TestBean
           (class (update-bean b {:foo "foo" :bar-baz 18}))))
    (is (= "foo" (.getFoo b)))
    (is (= 18 (.getBarBaz b)))))
