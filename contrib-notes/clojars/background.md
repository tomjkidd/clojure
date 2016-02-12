The provided [instructions](https://github.com/clojars/clojars-web/blob/453a90c2d280bbb36bc672b4630636f399804929/README.md) resulted in an error for me.

        user=> (migrate)
        Running migration: initial-schema
        Running migration: add-promoted-field

        BatchUpdateException batch entry 0: [SQLITE_ERROR] SQL error or missing database (no such table: jars)  org.sqlite.jdbc3.JDBC3Statement.executeBatch (JDBC3Statement.java:210)

To get more info, I ran it through lein

        lein run -m user/migrate

And got the following (abbreviated) results

        ...
        [B] at clojars.db.migrate$add_promoted_field.invokeStatic(migrate.clj:15)
            at clojars.db.migrate$add_promoted_field.invoke(migrate.clj:14)
            at clojure.lang.Var.invoke(Var.java:379)
            at clojars.db.migrate$run_and_record.invokeStatic(migrate.clj:39)
            at clojars.db.migrate$run_and_record.invoke(migrate.clj:37)
            at clojars.db.migrate$migrate$fn__9489.invoke(migrate.clj:68)
            at clojure.java.jdbc$db_transaction_STAR_.invokeStatic(jdbc.clj:595)
            at clojure.java.jdbc$db_transaction_STAR_.doInvoke(jdbc.clj:568)
            at clojure.lang.RestFn.invoke(RestFn.java:521)
            at clojure.java.jdbc$db_transaction_STAR_.invokeStatic(jdbc.clj:611)
            at clojure.java.jdbc$db_transaction_STAR_.doInvoke(jdbc.clj:568)
            at clojure.lang.RestFn.invoke(RestFn.java:425)
            at clojars.db.migrate$migrate.invokeStatic(migrate.clj:62)
            at clojars.db.migrate$migrate.invoke(migrate.clj:55)
        [A] at user$migrate.invokeStatic(user.clj:42)
            at user$migrate.invoke(user.clj:41)
            at clojure.lang.Var.invoke(Var.java:375)
            at user$eval11958.invokeStatic(form-init1159289827140649818.clj:1)
            at user$eval11958.invoke(form-init1159289827140649818.clj:1)
            at clojure.lang.Compiler.eval(Compiler.java:6927)
            at clojure.lang.Compiler.eval(Compiler.java:6917)
            at clojure.lang.Compiler.load(Compiler.java:7379)
        ...

[A] is a call to clojars.db.migrate/migrate

[B] filtered to clojars, this is the top of the stack trace, the add-promoted-field function. This function tries to add a column to the jars table through SQL.

I then tried to debug the issue with the following commands

        (require '[clojars.config :as config])
        config/config

That gave me this output

        user=> (pprint config/config)
        {:deletion-backup-dir "data/dev_deleted_items",
         :db
         {:classname "org.sqlite.JDBC",
          :subprotocol "sqlite",
          :subname "data/dev_db"},
         :mail
         {:hostname "localhost", :from "noreply@clojars.org", :ssl false},
         :stats-dir "data/stats",
         :bcrypt-work-factor 12,
         :port 8080,
         :base-url "http://localhost:8080",
         :nrepl-port 7991,
         :index-path "data/index",
         :repo "data/dev_repo",
         :yeller-environment "development",
         :bind "0.0.0.0"}

(migrate) calls the migrate function in clojar-web/dev/user.clj, which is actually defined in clojars.db.migrate in src/clojars/db/migrate.clj. The call is made with the :db accessor, which refers to this:

        {:classname "org.sqlite.JDBC",
         :subprotocol "sqlite",
         :subname "data/dev_db"}

Based on :db, the database should be in data/dev_db
Based on the stack trace above, I would expect that maybe the `jars` table does not exist. Using SQLit Manager with Firefox, this is indeed the case.

# How is the jars table created?
Did a search in the project for "CREATE TABLE"
resources/queries/clojars.sql has the statement to create the jars table.

# Where is this SQL statement called?
Did a search for queries/clojars.sql and found a reference in src/clojars/db/migrate.clj, line 8.
This is part of the initial-schema function.
This symbol is part of the `migrations` symbol, and should be the first SQL commands to run for line 66 in the migrate function.

# What does sql/db-do-commands do?
skipped for now
# What does sql/with-db-transaction do?
skipped for now
# What does doseq do? skipped for now
skipped for now
# What does run-and-record do? skipped for now
skipped for now

# What if I run resources/queries/clojars.sql first through SQLite Manager?

        D:\github-work\clojars-web>lein run -m user/migrate
        Running migration: initial-schema
        Running migration: add-promoted-field
        Running migration: add-jars-index
        Running migration: add-pgp-key
        Running migration: add-added-by
        Running migration: add-password-reset-code
        Running migration: add-password-reset-code-created-at

I am able to get the project to launch using the instructions after running this command.

# What is a maven repository?
I was unable to use rsync (windows does not provide it with Git Bash), so I had to try and find an alternative way to get the equivalent information.

[Maven intro to repositories](https://maven.apache.org/guides/introduction/introduction-to-repositories.html)
The Repository is like NPM for Java artifacts.

[Clojars Groups](https://maven.apache.org/guides/introduction/introduction-to-repositories.html)
Groups are used to identify a container for Projects.
Lein uses the form [groupId/artifactId "version"] for dependencies.
(defproject org.clojars.tomjkidd/projectName ...) for creating a dev project

# What does the `rsync -av --delete clojars.org::clojars copy-of-clojars` do?

-av | v -> --verbose, a -> --archive (equivalent to -rlptgoD)
    | r -> --recursive, l -> --links (copy symlinks as symlinks)
    | p -> --perms, t -> --times, g -> --group
    | o -> --owner, D -> --devices --specials
--delete | Delete extraneous files from dest dirs
clojars.org | HOST
clojars | SRC
copy-of-clojars | DEST

# Can I use ~/.m2/repository?
Git Bash

        cp -r ~/.m2/repository /d/github-work/clojars-web/data/dev_repo

Lein Bash

        lein run -m clojars.tools.setup-dev

This led to an exception being thrown.

            at clojure.lang.Compiler.load(Compiler.java:7391)
            at clojure.lang.Compiler.loadFile(Compiler.java:7317)
            at clojure.main$load_script.invokeStatic(main.clj:275)
            at clojure.main$init_opt.invokeStatic(main.clj:277)
            at clojure.main$init_opt.invoke(main.clj:277)
            at clojure.main$initialize.invokeStatic(main.clj:308)
            at clojure.main$null_opt.invokeStatic(main.clj:342)
            at clojure.main$null_opt.invoke(main.clj:339)
            at clojure.main$main.invokeStatic(main.clj:421)
            at clojure.main$main.doInvoke(main.clj:384)
            at clojure.lang.RestFn.invoke(RestFn.java:421)
            at clojure.lang.Var.invoke(Var.java:383)
            at clojure.lang.AFn.applyToHelper(AFn.java:156)
            at clojure.lang.Var.applyTo(Var.java:700)
            at clojure.main.main(main.java:37)
                Caused by: java.lang.NullPointerException
            at clojure.string$replace.invokeStatic(string.clj:101)
            at clojure.string$replace.invoke(string.clj:75)
            at clojars.dev.setup$import_repo$iter__12005__12009$fn__12010.invoke(setup.clj:75)
            at clojure.lang.LazySeq.sval(LazySeq.java:40)
            at clojure.lang.LazySeq.seq(LazySeq.java:49)
            at clojure.lang.RT.seq(RT.java:521)
            at clojure.core$seq__4357.invokeStatic(core.clj:137)
            at clojure.core$filter$fn__4812.invoke(core.clj:2700)
            at clojure.lang.LazySeq.sval(LazySeq.java:40)
            at clojure.lang.LazySeq.seq(LazySeq.java:49)
            at clojure.lang.RT.seq(RT.java:521)
            at clojure.core$seq__4357.invokeStatic(core.clj:137)
            at clojure.core.protocols$seq_reduce.invokeStatic(protocols.clj:24)
            at clojure.core.protocols$fn__6738.invokeStatic(protocols.clj:75)
            at clojure.core.protocols$fn__6738.invoke(protocols.clj:75)
            at clojure.core.protocols$fn__6684$G__6679__6697.invoke(protocols.clj:13)
            at clojure.core$reduce.invokeStatic(core.clj:6545)
            at clojure.core$reduce.invoke(core.clj:6527)
        [A] at clojars.dev.setup$import_repo.invokeStatic(setup.clj:89)
            at clojars.dev.setup$import_repo.invoke(setup.clj:60)
            at clojars.dev.setup$setup_dev_environment.invokeStatic(setup.clj:110)
            at clojars.dev.setup$setup_dev_environment.invoke(setup.clj:96)
            at clojars.tools.setup_dev$_main.invokeStatic(setup_dev.clj:8)
            at clojars.tools.setup_dev$_main.doInvoke(setup_dev.clj:6)
            at clojure.lang.RestFn.invoke(RestFn.java:397)
            at clojure.lang.Var.invoke(Var.java:375)
            at user$eval11958.invokeStatic(form-init8869485324395708507.clj:1)
            at user$eval11958.invoke(form-init8869485324395708507.clj:1)
            at clojure.lang.Compiler.eval(Compiler.java:6927)
            at clojure.lang.Compiler.eval(Compiler.java:6917)
            at clojure.lang.Compiler.load(Compiler.java:7379)
            ... 14 more

[A] is the top of the clojars part of the stack trace, located in src/clojars/dev/setup.clj, line 89 is part of the import-repo function.

Try it with a smaller subset, just lein-ring for now -> Still fails.

Figured out what the problem was after a while...

In the following regex, `/` is used as the file separator, but for windows it is `\`

        "/(.*)/([^/]*)$"

This small, but sneaky problem wasted about an hour of my life trying to figure out why the migrate kept failing. You can access the separator from Clojure with the following:

        (java.io.File/separator)

After overcoming this issue, I tried again to follow the instructions, but another error has surfaced. This time it's when I try to access http://localhost:8080 after the (go) command in the repl. I get the following error.

         No implementation of method: :-report-error of protocol: #'clojars.errors/ErrorReporter found for class: clojars.errors.StdOutReporter

It does appear to be defined in clojars.errors.StdOutReporter

        ...
        [clj-stacktrace.repl :refer [pst]]
        ...
        (defrecord StdOutReporter []
          ErrorReporter
          (-report-error [_ e _ id]
            (println "ERROR ID:" id)
            (pst e)))
        ...

The def for pst

        (defn pst
          "Print to *out* a pretty stack trace for a (parsed) exception, by default *e."
          [& [e]]
          (pst-on *out* false (or e *e)))

And it's about this point that I ran out of steam.
