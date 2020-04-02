package ticker

import scala.util.Random
import scala.concurrent.duration._

import akka.NotUsed
import akka.stream.scaladsl.Source

//#impl
class TickerServiceImpl extends TickerService {
  val random = Random

  override def monitorSymbol(in: Symbol): Source[Value, NotUsed] =
    Source.fromIterator(() => new Iterator[Value]() {
      override def hasNext = true
      override def next(): Value = Value(in.name, random.nextInt)
    })
}
//#impl
