;;  Copyright (c) Justin Balthrop. All rights reserved.  The use and
;;  distribution terms for this software are covered by the Eclipse Public
;;  License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can
;;  be found in the file epl-v10.html at the root of this distribution.  By
;;  using this software in any fashion, you are agreeing to be bound by the
;;  terms of this license.  You must not remove this notice, or any other
;;  from this software.
;;
;;  Tests for clojure.contrib.bean
;;
;;  code@justinbalthrop.com
;;  Created July 7, 2010

(ns clojure.contrib.test-bean
  (:use clojure.test clojure.contrib.bean))

(deftest test-update-bean
  (let [b (java.awt.TextField.)]
    (is (= "" (.getText b)))
    (is (= java.awt.TextField (class (update-bean b {:text 43}))))
    (is (= "43" (.getText b)))

    (is (= 0 (.getSelectionStart b)))
    (is (= 0 (.getSelectionEnd b)))
    (is (= java.awt.TextField
           (class (update-bean b {:text "foo" :name "bar" :selection-start 1 :selection-end 2}))))
    (is (= "foo" (.getText b)))
    (is (= "bar" (.getName b)))
    (is (= 1 (.getSelectionStart b)))
    (is (= 2 (.getSelectionEnd b)))))

