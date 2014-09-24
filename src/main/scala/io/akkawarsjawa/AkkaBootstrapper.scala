package io.akkawarsjawa

import akka.actor.{Props, ActorSystem}
import io.akkawarsjawa.core.{LogReceiverActor, LogAggregatorActor}

trait AkkaBootstrapper {

   implicit lazy val system = ActorSystem("akka-warsjawa")
   private implicit val _ = system.dispatcher

   val logAggregator = system.actorOf(Props(classOf[LogAggregatorActor]), "logAggregator")
   val logReceiver = system.actorOf(Props(classOf[LogReceiverActor], logAggregator), "logReceiver")
}
