package io.akkawarsjawa.core

import akka.actor.{Props, Actor}
import com.codahale.metrics.Meter
import io.akkawarsjawa.{Metrics}
import io.akkawarsjawa.core.LogReceiverActor.JsonLogMessage

object LogAggregatorActor {

}

class LogAggregatorActor extends Actor{

   val aggregated : Meter = Metrics.metrics.meter("aggregated")

   val saver = context.actorOf(Props[MongoSaver], "saver")


   def receive = {
      case message @ JsonLogMessage(appName, jsonMap) =>
         var j = 0
         for(i <- 1 to 50000) {
            j = j + 1
         }
         aggregated.mark()
         saver ! message
   }
}
