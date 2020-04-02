@@@section { data-background-color="#15a8ce" data-background-image="./front-bg.svg" }

### ![](./akka-reverse.svg) Introduction: Akka gRPC

#### Arnout Engelen

![lightbend-title](images/lightbend-color-reverse.svg)

@@@

@@@section

1. Introduction
1. Building a server

https://github.com/raboof/akka-grpc-intro-video

@@@

@@@section

## What is gRPC?

@@@ 

@@@section

## What is gRPC?

### SOAP
### REST
### gRPC

@@@

@@@section

## What is gRPC?

### SOAP - XML
### REST - JSON
### gRPC - Protobuf

@@@

@@@section

<div>
<img src="images/lightbend-full-color.svg" width=500px>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      <img src="images/Google_2015_logo.svg">
</div>

![CNCF](images/cncf-horizontal-color.png)

@@@

@@@section

# Demo

## 'stock ticker' API

receiving a stream of stock ticker values for a symbol

@@@

@@@section

gRPC is typically used contract-first:

@@snip[ticker.proto](/src/main/protobuf/ticker.proto)

@@@

@@@section

Akka gRPC 

Add the plugin to your build:

@@snip[plugins.sbt](/project/plugins.sbt) { #plugin }

And build the project:

```
$ sbt compile
```

To generate API classes:

```
$ find target/scala-2.13/src_managed | grep TickerService
target/scala-2.13/src_managed/main/ticker/TickerServiceHandler.scala
target/scala-2.13/src_managed/main/ticker/TickerService.scala
```

@@@

@@@section

The generated API looks like this:

```scala
trait TickerService {
  
  /**
   * Monitor a symbol
   */
  def monitorSymbol(in: Symbol): Source[Value, NotUsed]
  
}
```

@@@

@@@section

We implement it:

@@snip[TickerServiceImpl.scala](/src/main/scala/TickerServiceImpl.scala) { #impl }

@@@

@@@section

And embed it in a simple [Akka HTTP](https://doc.akka.io/docs/akka-http) server:

@@snip[Main.scala](/src/main/scala/Main.scala) { #main }

@@@

@@@section

You can now connect to the service:

```
$ grpc_cli call localhost:8080 \
    ticker.TickerService.MonitorSymbol "name: 'foo'" \
    --proto_path akka-grpc-intro-video/src/main/protobuf \
    --protofiles ticker.proto
Received initial metadata from server:
date : Thu, 02 Apr 2020 11:16:51 GMT
server : akka-http/10.1.11
name: "foo"
value: -1916871094

name: "foo"
value: -1593780393

name: "foo"
value: -2089568964

...
```

@@@

@@@section

TODO various links to lightbend, academy, docs etc etc

@@@
