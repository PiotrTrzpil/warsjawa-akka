package io.akkawarsjawa.core

import akka.actor.{ActorRef, Props, Actor}
import java.util.UUID
import com.codahale.metrics.{MetricRegistry, Meter}
import akka.event.Logging

object LogReceiverActor {
   case class SendMessage(to: String, message: String)
   case class JsonLogMessage(appName: String, json: Map[String, String])
   case class LineLogMessage(appName: String, line: String)
   case class FileLogMessage(appName: String, bytes: Array[Byte])
}

class LogReceiverActor(aggregator: ActorRef) extends Actor {

   import LogReceiverActor._

   val logger = Logging(context.system, this)

   override def preStart() = {
      logger.debug("Starting LogReceiverActor")
   }

   def receive: Receive = {
      case m @ JsonLogMessage(appName, jsonMap) =>
         logger.debug(s"Received json for app: $appName")
         aggregator ! m
      case LineLogMessage(appName, line) =>
         logger.debug(s"Received single line for app: $appName")
      case FileLogMessage(appName, bytes) =>
         logger.debug(s"Received file for app: $appName")
   }
}
