FairLib
=======

Java lib with set of classes to work with Betfair API


=== BUILD ===

FairLib project depends on the following projects:
1. Betfair-Provider: https://github.com/komarevsky/betfair-provider
2. XLogger: https://github.com/komarevsky/xlogger
For the moment these projects are not located in any maven-repo,
so before building of FairLib please download and build it manually:

    cd
    mkdir freebetbot
    cd freebetbot
    git clone https://github.com/komarevsky/betfair-provider betfair-provider
    git clone https://github.com/komarevsky/xlogger xlogger
    cd betfair-provider
    mvn clean install   # or follow README instructions if different
    cd ../xlogger
    mvn clean install   # or follow README instructions if different

Then build FairLib :
    mvn clean install
NOTE: FairLib requires Java 7


=== AUTHOR ===

Siarhei Skavarodkin
email: komarevsky (at) gmail (dot) com
