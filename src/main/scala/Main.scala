package ticker

import akka.actor.ActorSystem
import akka.grpc.scaladsl.{ ServiceHandler, ServerReflection }
import akka.http.scaladsl._
import akka.stream.SystemMaterializer

//#main
object Main extends App {
    implicit val system = ActorSystem("ticker")
    implicit val mat = SystemMaterializer(system).materializer

    Http().bindAndHandleAsync(
//        TickerServiceHandler(new TickerServiceImpl),
        ServiceHandler.concatOrNotFound(
            TickerServiceHandler.partial(new TickerServiceImpl),
            ServerReflection.partial(List(TickerService))
        ),
        "127.0.0.1",
        8080
    )
}
//#main
//        ServiceHandler.concatOrNotFound(
//            TickerServiceHandler.partial(new TickerServiceImpl),
//            ServerReflection.partial(List(TickerService))
//        ),
