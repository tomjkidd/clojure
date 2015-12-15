http://leiningen.org
Ended up having to use http://leiningen-win-installer.djpowell.net/

## Notes
### Preface
lein command line did not work in Git Bash, so I use a normal window.
* Is there a way to fix this?

### Chapter 1
* DoubleQuotes ("") are used for strings
* The colon (:) is used as a prefix for `Keywords`
* The backslash (\) is used for characters
* `true` and `false` are bools
* `nil` represents absence of a value AND the empty list
* Collections : { lists, vectors, maps, sets }

#### Lists
'(1 2 "abc" :def)

* Single quote and open paren is used to start the list, close paren to end it.
* Idiomatic to space separate elements in list (though you can use comma (,))
* Heterogeneous
* functions { first, rest, cons, nth, last }
* performance tuned for first, rest, cons
* '(1 2 3) == (list 1 2 3) == (cons 1 (cons 2 ( cons 3 nil )))

#### Vectors
* Square brackets are used to define vectors
* function { first, rest, nth, last }
* performance tuned for nth

#### Collections in general
* immutable: the value of collection does not change.
* persistent: smart creation of new elements, use of structural sharing
* functions { first, rest, last, count, conj }
* conj adds one or more elements to the collection in the most natural way for that data structure.
    * for lists, they are added to the front
    * for vectors, they are added to the end
* conj is short for conjoin

#### Maps
* Curly braces are used to define maps
* functions { get, keys, vals, assoc, dissoc, merge }
* Idiomatic to use comma (,) to separate pairs for readability, not required.
* get returns nil if not found.
* get can take an additional parameter as a default.
* keywords can be used as name of function to get value without using `get` function. This is the more idiomatic way, (:a { :a 1 :b 2 })
* merge is similar to extend in JS, it will go from left to right, overwriting matching keys with the rightmost values.

#### Sets
* Pound and open curly brace are used to start a set, close curly brace to end it.
* Duplicate keys throw exceptions
* clojure.set namespace for { union, difference, intersection }
    * (clojure.set/union #{ :r :b :w } #{ :w :p :y })
* functions { get, set, contains?, conj, disj }
* set function will create a set out of a collection
* get will attempt to get an item, nil if not present in the set
* keyword as function name will also work
* disj is used to remove an item from a set

#### Type review
* Strings
* Integers
* Ratios
* Decimals
* Keywords
* Characters
* Booleans

#### Lists are the heart of Clojure
* Code is data, all Clojure code is made of lists of data

#### Symbols to bind (functional equivalent of something like variables)
* `def` is used to create a var object for a symbol `name`
    (def <name> <value>)
* vars use namespaces
* fully qualified names include the namespace followed by a forward slash, then the name of the var.
* `let` is used for a vector form way to locally define a var/symbol. Expects pairs of symbols/values. Creates temporary binding
* "What happens in a let, stays in a let"

#### Creating your own functions
* `defn` creates vars for functions
* `defn` takes the following parameters
    * name of the function
    * a vector of parameters
    * the body of the function
* `fn` is used to define anonymous functions (like lambda in scheme)
* (def f (fn [] 1)) == (defn f [] 1)
* There is a shorthand form, (#(str "blah" "!" " - " %1 %2))

#### Keep Your Symbols Organized in Namespaces
* `ns` is used to create and switch to a new namespace
* `*ns*` will return current namespace (The asterisks are called `earmuffs`)
* `require` is used to load other namespaces
    (require 'namespace')
    (require '[namespace :as af]')
    (ns wonderland
        (:require [alice.favfoods :as af]))
* Forward slash is used to separate namespace from symbols
