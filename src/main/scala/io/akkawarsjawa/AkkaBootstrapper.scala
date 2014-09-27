package io.akkawarsjawa

import akka.actor.{ActorRef, Props, ActorSystem}
import io.akkawarsjawa.core.{LogReceiverActor, LogAggregatorActor}
import akka.routing.FromConfig

trait AkkaBootstrapper {

   implicit lazy val system = ActorSystem("akka-warsjawa")
   private implicit val _ = system.dispatcher

   val logAggregator : ActorRef = system.actorOf(
      FromConfig.props(Props(classOf[LogAggregatorActor])),
      "logAggregator")
   val logReceiver = system.actorOf(
      Props(classOf[LogReceiverActor], logAggregator), "logReceiver")
}
