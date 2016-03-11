# training-plan-heroku-app

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## Notes

Currently, the unit tests are not in their own configuration.

Deployed the first version of the [app](https://evening-shelf-37877.herokuapp.com/index.html).

## Commands

### Rhino repl

    lein trampoline cljsbuild repl-rhino

### Browser connected repl

    lein trampoline cljsbuild repl-listen

### Build and watch cljs files

    lein cljsbuild auto


## License

Copyright © 2016 Tom Kidd
