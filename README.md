FairLib
=======

Java lib with set of classes to work with Betfair API
_ _ _

Build
-----

FairLib project depends on the following project:
*   [Betfair-Provider](https://github.com/komarevsky/betfair-provider)
*   [Apache-Commons-Logging](http://commons.apache.org/proper/commons-logging)

For the moment Betfair-Provider project is not located in any maven-repo, so before building of FairLib please download and build it manually:

        cd
        mkdir freebetbot
        cd freebetbot
        git clone https://github.com/komarevsky/betfair-provider betfair-provider
        cd betfair-provider
        mvn clean install   # or follow README instructions if different

Then download FairLib:

        cd
        cd freebetbot
        git https://github.com/komarevsky/fairlib fairlib

Then build FairLib:

        mvn clean install

**Note**: FairLib requires Java 6
_ _ _

Author
------

Siarhei Skavarodkin

email: komarevsky (at) gmail (dot) com
