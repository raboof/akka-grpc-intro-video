package ticker

import scala.util.Random

import akka.NotUsed
import akka.stream.scaladsl.Source

//#impl
class TickerServiceImpl extends TickerService {
  val random = Random

  override def monitorSymbol(in: TickerSymbol): Source[StockValue, NotUsed] =
    Source.fromIterator(() => new Iterator[StockValue]() {
      override def hasNext = true
      override def next(): StockValue = StockValue(in.name, random.nextInt(500))
    })
}
//#impl
