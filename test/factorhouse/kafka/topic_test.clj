(ns factorhouse.kafka.topic-test
  (:require [clojure.test :refer [deftest is]]
            [factorhouse.kafka.topic :as topic]
            [factorhouse.test.data :as data]))

(deftest sizes

  (is (= data/sizes
         (topic/sizes data/topics)))

  (is (= [{:broker 2 :dir "/kfk" :topic "br.ch" :partition 1
           :size 2838281 :offset-lag 0 :future? false}]
         (topic/sizes {1 {"/kfk" {:replica-infos {}
                                  :error "Unable to connect"}}
                       2 {"/kfk" {:replica-infos {{:topic "br.ch" :partition 1}
                                                  {:size 2838281 :offset-lag 0 :future? false}}
                                  :error "NONE"}}}))))

;; TODO: Optional Bonus Extension!
(deftest categories

  (is (= data/categories-physical
         (topic/categories-physical data/sizes)))

  (is (= data/categories-logical
         (topic/categories-logical data/sizes))))
