[Heroku Signup](https://signup.heroku.com/dc)
[Heroky Toolbelt](https://devcenter.heroku.com/articles/getting-started-with-clojure#set-up)

# Commands
        heroku login
        heroku create
        git push heroku master

# Deployed to
        https://lit-bayou-33786.herokuapp.com/

# Week7/Day2

## Summary

I was able to get compojure working with the [Quote Generator](https://github.com/tomjkidd/quote-generator), so the basics of creating a web application are available to me.

Recently, I was able to contribute to clojars, and noticed that they are using yesql to handle working with sqlite. One of my goals is to come up with a simple database and perform the basic CRUD operations using this approach for the web application.

## Server plan

### What would a useful database for me be?

Places that Jenna and I visited while I'm here in London.

### What data would be necessary?

type Location =
{ Id : Int
, Latitude : Number
, Longitude : Number
, Name : String
, Address : String
, Tags : List String
, Rating : Int
}

### What services would be necessary?

* Add a new location
* Remove a location
* Get a list of locations
* Update the { Name, Tags, Rating } for a location

[Sqlite Ref](https://sqlite.org/lang.html)

### Dependencies

[yesql "0.5.2"]
[org.xerial/sqlite-jdbc "3.7.2"]
[org.clojure/java.jdbc "0.4.2"]

## Client plan

I haven't used ClojureScript to this point, so I think I'll just stick with the basics presented in Chapter 7 and try to use them to create a way to perform the CRUD stuff.
