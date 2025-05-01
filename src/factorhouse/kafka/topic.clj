(ns factorhouse.kafka.topic)

;; Technical Challenge! Implement this function.

(defn sizes
  "Transform raw topic information into a more useable sizes shape"
  [topics]
  (->> topics
       (map (fn [[broker dirs]]
              (map (fn [[dir {:keys [replica-infos]}]]
                     (map (fn [[{:keys [topic partition]} stats]]
                            (merge {:broker broker
                                    :dir dir
                                    :topic topic
                                    :partition partition}
                                   stats))
                          replica-infos))
                   dirs)))
       flatten
       (sort-by (juxt :broker :dir :topic :partition))))

;; Extension Challenge! Implement these functions.

(defn categories-logical
  "Transform topic sizes into categorised logical view"
  [_sizes])

(defn categories-physical
  "Transform topic sizes into categorised physical view"
  [_sizes])
