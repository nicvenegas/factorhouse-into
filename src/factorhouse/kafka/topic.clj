(ns factorhouse.kafka.topic)

;; Technical Challenge! Implement this function.

(defn sizes
  "Transform raw topic information into a more useable sizes shape"
  [topics]
  (sort-by
   (juxt :broker :dir :topic :partition)
   (sequence
    (comp (map (fn [[broker dirs]]
                 (map (fn [[dir {:keys [replica-infos error]}]]
                        (when (= error "NONE")
                          (map (fn [[{:keys [topic partition]} stats]]
                                 (merge {:broker broker
                                         :dir dir
                                         :topic topic
                                         :partition partition}
                                        stats))
                               replica-infos)))
                      dirs)))
          (mapcat flatten)
          (filter some?))
    topics)))

(comment
  (let [topics {1 {"/kfk" {:replica-infos {{:topic "br.ch" :partition 1}
                                           {:size 2838281 :offset-lag 0 :future? false}}
                           :error "NONE"}}
                2 {"/kfk" {:error "Could not connect"}}}]
   (sizes topics))
  ;;
  )

;; Extension Challenge! Implement these functions.

(defn categories-logical
  "Transform topic sizes into categorised logical view"
  [_sizes])

(defn categories-physical
  "Transform topic sizes into categorised physical view"
  [_sizes])
