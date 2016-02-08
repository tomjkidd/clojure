(ns training-plan.week3.day1
  (:require [clojure.test :refer :all]))

(defn binary-tree?
  [maybe-tree]
  (let [datum (first maybe-tree)
        left (first (rest maybe-tree))
        right (first (rest (rest maybe-tree)))
        nil-or-binary-tree? (fn [x]
                              (if (nil? x)
                                true
                                (if (or (vector? x) (list? x))
                                  (binary-tree? x)
                                  false)))]
    (and
     (not (nil? datum))
     (= (count maybe-tree) 3)
     (nil-or-binary-tree? left)
     (nil-or-binary-tree? right))))

(deftest toTreeOrNotToTree
  (is (= (binary-tree? '(:a (:b nil nil) nil)) true))
  (is (= (binary-tree? '(:a (:b nil nil))) false))
  (is (= (binary-tree? [1 nil [2 [3 nil nil] [4 nil nil]]]) true))
  (is (= (binary-tree? [1 [2 [3 [4 nil nil] nil] nil] nil]) true))
  (is (= (binary-tree? [1 [2 [3 [4 false nil] nil] nil] nil]) false))
  (is (= (binary-tree? '(:a nil ())) false)))

(defn generate-symmetric-tree
  "Takes in a branch (which is a binary tree) and generates a branch that is symmetric.
NOTE: Combine this with the branch and a datum to make a symmetric tree."
  [branch]
  (if (binary-tree? branch)
    (let [datum (first branch)
          left (first (rest branch))
          right (first (rest (rest branch)))]
      (list datum
              (generate-symmetric-tree right)
              (generate-symmetric-tree left)))
    nil))

(defn symmetric-binary-tree?
  "A symmetric binary tree is one where the left half of the tree is the mirror image of the right half of the tree."
  [maybe-tree]
  (let [is-tree? (binary-tree? maybe-tree)]
    (and is-tree?
         (let [datum (first maybe-tree)
               left (first (rest maybe-tree))
               right (first (rest (rest maybe-tree)))]
           (= (list datum left right) (list datum (generate-symmetric-tree right) (generate-symmetric-tree left)))))))

(deftest beautyIsSymmetry
  (is (= (symmetric-binary-tree? '(:a (:b nil nil) (:b nil nil))) true))
  (is (= (symmetric-binary-tree? '(:a (:b nil nil) nil)) false))
  (is (= (symmetric-binary-tree? '(:a (:b nil nil) (:c nil nil))) false))
  (is (= (symmetric-binary-tree? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                                    [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])
         true))
  (is (= (symmetric-binary-tree? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                                    [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]])
         false))
  (is (= (symmetric-binary-tree? [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                                    [2 [3 nil [4 [6 nil nil] nil]] nil]])
         false)))



