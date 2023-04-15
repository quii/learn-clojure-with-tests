(ns learn-clojure-with-tests.state)

(def valid-weather-states #{:sunny :cloudy :rainy})

(defn valid-weather? [weather] (contains? valid-weather-states weather))

(def validate-weather-list #(every? valid-weather? %))

(defn new-weather-list [] (ref () :validator validate-weather-list))

(defn add-weather [weather item] (conj weather item))