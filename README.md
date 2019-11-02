# coins1920-group5-code
A ticket board fetcher for Condor.

## How to build it
Install a [Java SE Development Kit](https://www.oracle.com/technetwork/java/javase/downloads/index.html) as well
as [Apache Maven](https://maven.apache.org/). Clone or download this repository, and build by running:
```bash
$ mvn install
```

## How to run it
Export your API key (e.g. "y1o2u3r4e5k6e7y8") and OAuth token string (e.g. "o0a9u8t7h6") as environment variables
and execute the *.jar file you've just compiled:
```bash
$ export TRELLO_API_KEY=y1o2u3r4e5k6e7y8
$ export TRELLO_OAUTH_KEY=o0a9u8t7h6
$ java -jar target/condor-ticketboard-fetcher.jar trello myboardid /my/output/folder/
```
