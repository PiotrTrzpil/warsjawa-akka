package io.akkawarsjawa

import akka.actor.Props
import akka.io.IO
import spray.can.Http
import io.akkawarsjawa.api.{RoutedHttpService, LogReceiverService}

trait SprayBoostrapper { this: AkkaBootstrapper =>

   val routes = new LogReceiverService(logReceiver).route

   val rootService = system.actorOf(
      Props(new RoutedHttpService(routes)))

   IO(Http)(system) ! Http.Bind(rootService, "0.0.0.0",
      port = 8080)
}
