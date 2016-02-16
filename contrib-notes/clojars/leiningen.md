# Purpose

While attempting to work on 492, I didn't understand exactly how the project gets stood up by leiningen. This required doing some research.

# project.clj

The project.clj file is the starting point for any leiningen based project.
This file creates a call to the macro [defproject](https://github.com/technomancy/leiningen/blob/master/leiningen-core/src/leiningen/core/project.clj#L399)

A [sample](https://github.com/technomancy/leiningen/blob/master/sample.project.clj) is provided describing some of the options.

I used this sample as a reference while analyzing the Clojars one.

# :main

    :main ^:skip-aot clojars.main

:main defines which ns the -main will run from
AOT compilation is used by default, ^:skip-aot prevents this

# :target-path
    
    :target-path "target/%s/"
    
:target-path defines where all generated non-code files go

# :release-tasks

    :release-tasks [["vcs" "assert-committed"]...
    
:release-tasks defines tasks that `lein release` will perform

# :aliases

    :aliases {"migrate" ["run" "-m" "clojars.tools.migrate-db"]}
    
:aliases support project-specific task aliases, so a migrate command is provided with Clojars

# :profiles

    :profiles { :dev [:project/dev :profiles/dev] }

:profiles are control merging useful dependencies into the project map

## Profiles that load by default
[:user :dev :base]

:user is defined in ~/.lein/profiles.clj
:dev is defined in the project's project.clj file
:base is a built in profile.

For Clojars, the profile information of interest follows:

:dev profile points to [:project/dev :profiles/dev]

### :project/dev Profile

:project/dev has multiple customizations

* `:source-paths ["dev"]` adds dev/user.clj file to project
* `:repl-options {:init-ns user}` specifies the ns to start the REPL in. In this case it is the `user` namespace from dev/user.clj.
* `:dependencies [...]` is the same as for the normal project, except it is meant to provide development tools/dependencies that are not actually part of the project.
* `:resource-paths ["local-resources"]` exposes a place where non-code files are included classpath/jar. These files will go to :target-path

### :profiles/dev Profile

:profiles/dev has no customization


Useful commands for Clojars

    lein show-profiles
    lein show-profiles project/dev
    

Available profiles for Clojars

    base
    debug
    default
    dev
    leiningen/default
    leiningen/test
    offline
    profiles/dev
    profiles/test
    project/dev
    project/test
    test
    uberjar
    update
    user
    
# Lein suggests
:user profile is for access to deps or plugins during development for any project

:dev profile is used to specify project specific development tooling. Put things here if they are required for builds or tests, rather than just convenience tooling.

:system is similar to user, except it applies to system-wide instead of a single user.

:base profile is used for deps necessary for basic repl functionality. It is part of leiningen itself.

:provided profile is for deps that should be available during jar creation, but won't be included in the build and are assumed to be available where the jar will be deployed.

:default :leiningen/default -> [:base :system :user :provided :dev]

:test and :repl used when test or repl commands are issued.

For more info, see [profile readme](https://github.com/technomancy/leiningen/blob/stable/doc/PROFILES.md)

# Commands
    lein run
    lein help sample
    lein help profiles
    lein show profiles
    lein show profiles base
    lein with-profiles ...
    lein release
    
