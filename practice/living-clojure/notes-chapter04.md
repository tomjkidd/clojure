### Chapter 4

#### JVM ([from Wikipedia](https://en.wikipedia.org/wiki/Java_virtual_machine))
* Specification, Implementation, Instance
* Oracle: Implementation -> HotSpot, Java Class Library -> JRE

#### Handling Interop with Java
* Solves the 'library problem' by interop with Java Classes
* Use of the JVM and existing Java libraries provide competitive advantage, battle tested.
* Clojure strings are of type java.lang.String, and there are ways to access the constructor as well as static methods.
    (. "test" toUpperCase)
    (.toUpperCase "test")
    (.indexOf "test" "es")
    (new String "Hi!")
    (String. "Hi!")
    (ns caterpillar.network
        (:import (java.net InetAddress)))
    (InetAddress/getByName "localhost")
    (.getHostName (InetAddress/getByName "localhost"))
* `doto` function can be used to chain actions together
* `import` function used to pull in java libs
    (import 'java.util.UUID)
    (UUID/randomUUID)

#### Practical Polymorphism
* Idiomatic Clojure uses a small amount of types and many functions for them
* Java uses polymorphism heavily, so Clojure provides a story for how to use that functionality, with `multimethods`

##### Multimethods (one defmulti with many defmethods)
* `defmulti` and `defmethod` are used together
* `defmulti` signifies the function signature
* `defmethod` implements specific behaviors
    (defmulti <fn-name> <dispatch-fn>)
    (defmethod <fn-name> <match-value> [<input>] <body>)
* `class` function can be used with `defmulti` to have class specific behaviors
* `:default` keyword is used to provide a catch-all for when a specific `defmethod` doesn't satisfy the attempted call
* In general, `defmulti` takes a dispatcher, which was `class` in the first example.

##### Protocols
* `defprotocol` and `extend-protocol` can be used together
* A protocol is used to handle polymorphism with a group of functions (reminds me of an interface)
* `defrecord` and `deftype` are used to create user-defined types
    (defrecord <name> <arguments>) ;; used to define a record
    (<name>. arg1 arg2 ... argN) ;; used to create a record
    (.-<feild-name> <record-instance>) ;; used to access the record
* records have feilds, accessed with .- (dot-dash)
* `defrecord` is targeted toward making structured data
* records can implement protocols, serve as implementation of protocol interface
* One practical example would be to have different record types, fed the same data, in order to save to different types of persistent storage.
* `deftype` is targeted to simple data, this was loosely explained. Look for more info.
* types are lighter weight, don't provide dot-dash lookup, do support type-dot construction.
* types can also implement protocols
* WARNING: Think before you use protocols!
