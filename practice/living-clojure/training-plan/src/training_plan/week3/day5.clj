(ns training-plan.week3.day5
  (:require [clojure.test :refer :all]))


(defn anagram?
  [src tgt]
  (= (group-by identity src)
     (group-by identity tgt)))

(defn find-anagrams
  [words]
  (loop [acc #{}
         remaining words]
    (let [cur (first remaining)
          groups (group-by #(keyword (str (anagram? cur %))) remaining)
          anagrams (:true groups)
          new-anagram-count (->> anagrams
                                 (filter #(not (= cur %)))
                                 (count))
          not-anagrams (:false groups)
          new-acc (if (<= new-anagram-count 0)
                        acc
                        (conj acc (into #{} anagrams)))]
      (if (nil? cur)
        acc
        (recur new-acc not-anagrams)))))

(deftest anagram-finder
  (is (= (find-anagrams ["meat" "mat" "team" "mate" "eat"])
         #{#{"meat" "team" "mate"}}))
  (is (= (find-anagrams ["veer" "lake" "item" "kale" "mite" "ever"])
         #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})))
