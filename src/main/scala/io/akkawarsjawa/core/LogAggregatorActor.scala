package io.akkawarsjawa.core

import akka.actor.{Props, Actor}
import com.codahale.metrics.Meter
import io.akkawarsjawa.{Metrics}
import io.akkawarsjawa.core.LogReceiverActor.JsonLogMessage

object LogAggregatorActor {

}

class LogAggregatorActor extends Actor{

   val aggregated : Meter = Metrics.metrics.meter("aggregated")

   val saver = context.actorOf(Props[MongoSaver])


   def receive = {
      case m @ JsonLogMessage(appName, jsonMap) =>
         aggregated.mark()
         saver ! m
   }
}
