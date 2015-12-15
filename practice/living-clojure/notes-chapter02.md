### Chapter 2
* `expression`: code that can be evaluated for a result
* `form`: a valid expression that can be evaluated
* function { class, true?, false?, nil?, not, =, not=, empty? }
* Equality on collections can give that a list and vector are equal
* `seq` returns an enumerable list for the collection types
* Use (seq []) instead of (not (empty? [])) to tell when empty, because of truthy values.
* functions for bool on collection { every?, not-any?, some }
* `if` has form (if <cond> <when true> <when false>)
* `if-let` allows a named binding to be used
* `when` has form (if <cond> <when true>) allowing you to only do something when true.
* `when-let` like `if-let`, but for `when`
* `cond` for multiple conditions, like scheme
* `case` used as `cond` shorthand
* functions for currying/partial application and composition
    { partial, comp }

#### Destructuring
* Reminds me of pattern-matching from Elm or Haskell.
* :as keyword allows for inner and outer pattern/structure to be named
* Destructuring works with vectors and maps

#### The Power of Laziness
* functions { range, take, class, repeat, repeatedly, count, rand-int, cycle }
* range produces a LazySeq
* repeat evaluates once and returns the value over and over
* repeatedly evaluates over and over

#### Recursion
* Same as encountered in other functional languages flavored like LISP
    * Check for empty?
    * Use (first lst)
    * Recur with (rest lst)
* functions { loop, recur }
* `recur` prevents consuming the stack

#### The Functional Shape of Data Transformations
* functions { map, reduce, filter, remove }
* No surprises here, basics are just like other functional implementations
* Using `take`, you can map over infinite sequences.
* Side-effects are not produced until the sequence is consumed!
* `do-all` function can force side-effects.
* `map` can take more than one collection, it will zip arguments, and stop when the first collection runs out. A sort of limiting reagent.
* Cannot `reduce` an infinite sequence!
* function `complement` takes a function and returns the opposite truth value
* function `keyword?` can be used to determine if a parameter is a keyword
* `for` is used like foreach in C#, but maps over a collection instead of plain iteration.
* Multiple collections given to a `for` will do a Cartesian Product.
* `for` allows :let and :when modifiers to be used to specify the desired combinations more readily.
* function `flatten` used to create a flat list of a nested structure.
* function `into` used to push a collection into another collection (through conj)
* functions { `partition`, `partition-all`, `partition-by` } used to split up a collection
