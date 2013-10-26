FairLib
=======

Java lib with set of classes to work with Betfair API
_ _ _

Build
-----

FairLib project depends on the following projects:
*   [Betfair-Provider](https://github.com/komarevsky/betfair-provider)
*   [XLogger](https://github.com/komarevsky/xlogger)

For the moment these projects are not located in any maven-repo, so before building of FairLib please download and build it manually:

        cd
        mkdir freebetbot
        cd freebetbot
        git clone https://github.com/komarevsky/betfair-provider betfair-provider
        git clone https://github.com/komarevsky/xlogger xlogger
        cd betfair-provider
        mvn clean install   # or follow README instructions if different
        cd ../xlogger
        mvn clean install   # or follow README instructions if different

Then build FairLib:

        mvn clean install

**Note**: FairLib requires Java 7
_ _ _

Author
------

Siarhei Skavarodkin

email: komarevsky (at) gmail (dot) com
