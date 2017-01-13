# Before the Day
## Domain background

If you are currently unfamiliar with the domain, we'd strongly suggest reading this blog on [Continuous Feedback](https://www.madetech.com/blog/continuous-feedback) to gain some background knowledge.

### History

* (Early 2015) Made Tech began using Continuous Feedback on a Trello board + Google Spreadsheets
* (Oct 2016) While on retreat in spain, the Made Tech team built a [rails app](https://continuousfeedback.io), that encapsulates the common use cases around continuous feedback.

## Core Use Cases

An "testing assumptions" analysis has been performed already for you, and is in brackets.

- View Next 1-2-1 (4)
- View Outstanding Feedback (2)
- View Completed 1-2-1's (1)
- Request Feedback (0)
- Complete 1-2-1 (-3)
- Give Feedback (-1)
- Schedule 1-2-1 (-1)
- View Team (-1)
- Modify Next 1-2-1 (-1)

## Getting setup with IntelliJ / Kotlin / Gradle

1. Download and install JDK 1.8 [from here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. Download and install IntelliJ IDEA **Community Edition** [from here](https://www.jetbrains.com/idea/download)
3. Open project directory within the git repo "mld-klean-architecture" with IntelliJ
4. In the import project window that opens tick "Auto Import"
5. Inside IntelliJ install the Spek plugin
    1. File > Settings > Plugins > Browse Repositories
    2. Search "Spek"
    3. Install
    4. Restart IntelliJ
6. Open "src > test > kotlin" in the left hand project navigator
7. Right click "io.continuousfeedback.core.test" 
8. and "Run 'Specs in io.continu..'"
9. If you get a non-zero number of passing tests, you're good to go! Hurray!

## Kotlin Help

The [kotlin reference](https://kotlinlang.org/docs/reference/) guide is really well written and the recommended guide for learning language features.

[Basic syntax](https://kotlinlang.org/docs/reference/basic-syntax.html) is a good place to start

For reference we're using [Spek](http://spekframework.org/) for testing, and [Expekt assertions](https://github.com/winterbe/expekt).

### Attention should be drawn to:

* [Variable definitions](https://kotlinlang.org/docs/reference/basic-syntax.html#defining-local-variables) are of both mutable and immutable types
* Kotlin has [typesafe nulls](https://kotlinlang.org/docs/reference/null-safety.html#nullable-types-and-non-null-types), also play close attention to the (?., ?, !!, ?:) operators
* [Kotlin constructors](https://kotlinlang.org/docs/reference/classes.html#constructors) will look weird initially, no pun intended
* You will need [interfaces](https://kotlinlang.org/docs/reference/interfaces.html#interfaces) in Clean Architecture
* [Returns work weirdly](https://kotlinlang.org/docs/reference/returns.html#returns-and-jumps), just like in Ruby blocks
* Kotlin [higher-order functions](https://kotlinlang.org/docs/reference/lambdas.html#higher-order-functions) look and feel similar to Ruby functions that take blocks
* Expekt uses [extension methods](https://kotlinlang.org/docs/reference/extensions.html) to provide monkey patching 
* Although you will need [generics](https://kotlinlang.org/docs/reference/generics.html#generics), you can probably stay away from variance.
* Use of [property getters/setters](https://kotlinlang.org/docs/reference/properties.html#getters-and-setters) for complex getters/setters, are preferred over java get/set methods
* It's also possible to use [private set](https://kotlinlang.org/docs/reference/properties.html#getters-and-setters) to hide an otherwise public property
* [Data classes](https://kotlinlang.org/docs/reference/data-classes.html) are great for housing data structures (hint, hint)
