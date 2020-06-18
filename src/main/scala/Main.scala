package ticker

import akka.actor.ActorSystem
import akka.grpc.scaladsl.{ ServiceHandler, ServerReflection }
import akka.http.scaladsl._
import akka.stream.SystemMaterializer

//#main
object Main extends App {
    implicit val system = ActorSystem("ticker")

    Http().bindAndHandleAsync(
       TickerServiceHandler(new TickerServiceImpl),
        "127.0.0.1",
        8080
    )
}
//#main

// To enable server reflection, use a composite handler like this:
//
//        ServiceHandler.concatOrNotFound(
//            TickerServiceHandler.partial(new TickerServiceImpl),
//            ServerReflection.partial(List(TickerService))
//        ),
