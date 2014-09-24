package io.akkawarsjawa.core

import akka.actor.Actor
import com.codahale.metrics.Meter
import io.akkawarsjawa.{Metrics}
import io.akkawarsjawa.core.LogReceiverActor.JsonLogMessage

object LogAggregatorActor {

}

class LogAggregatorActor extends Actor{

   val aggregated : Meter = Metrics.metrics.meter("aggregated")

   def receive = {
      case JsonLogMessage(appName, jsonMap) =>
         aggregated.mark()
   }
}
