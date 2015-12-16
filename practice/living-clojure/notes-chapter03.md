### Chapter 3

#### Using Atoms
* Used for independent, synchronous changes
* `Atoms` are intended for state that is independent
* independent: able to change the value independently of changing any other state.
    (def who-atom (atom :caterpillar))
* `@` is used to dereference an atom (retrieve it's current value)
* `reset!` is used to replace the atom's current value with a new one
    (reset! <atom> <new-val>)
* `swap!` allows a function to be applied to the current value to bring it to a new value
    (swap! <atom> <swap-fn>)
* swap-fn is a single parameter function defined (with defn)
* `do-times` function is used to call a function repeatedly
* `future` function is used to spawn a thread.

#### Using Refs
* Used for coordinated, synchronous changes
* Refs are { atomic, consistent, isolated }, ACI in ACID
* `@` is used to dereference a ref, same as an atom.
* `alter` is used to update a ref using it's existing values
    (alter right-hand-bites dec)
    (alter <ref> <fn>)
* `dosync` will run a given function (containing alter refs) in a transaction
* `alter` must be side-effect free because there might be retries
* `commute` takes a ref and a function, and will not retry during the transaction.
* The function applied by commute must be commutative, like addition/multiplication or have a last-in-wins behavior
* An atom with map of state can be used over many refs.
* `ref-set` can be used to replace the current value, like reset! for atoms

#### Using Agents
* Used for independent, asynchronous changes.
* Agents will handle long-running working, without blocking.
* `agent` is used to create an agent.
* `send` function is used to dispatch actions to the agent, which happens in a separate thread. It returns immediately. Fixed thread pool
* agent will process one action at a time
* `send-off` function is used for potentially I/O blocking actions. Expandable thread pool.
* Transactions can be handled in agent actions.
* `agent-errors` is used to inspect errors from agent.
* `restart-agent` clear the failed state of an agent, takes agent and new value
* `set-error-mode!` will allow you to specify how to handle failure, :fail or :continue
* `set-error-handler` allows you to provide a function to call when an agent fails

| Type | Communication | Coordination |
| --- | --- | --- |
| Atom | Sync | Uncoordinated |
| Ref | Sync | Coordinated |
| Agent | Async | Uncoordinated |
