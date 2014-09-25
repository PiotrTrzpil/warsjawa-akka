package io.akkawarsjawa.core

import akka.actor.{ActorLogging, ActorRef, Props, Actor}
import java.util.UUID
import com.codahale.metrics.{MetricRegistry, Meter}
import akka.event.Logging

object LogReceiverActor {
   case class SendMessage(to: String, message: String)
   case class JsonLogMessage(appName: String, json: Map[String, String])
   case class LineLogMessage(appName: String, line: String)
   case class FileLogMessage(appName: String, bytes: Array[Byte])
}

class LogReceiverActor(aggregator: ActorRef) extends Actor with ActorLogging {

   import LogReceiverActor._

   override def preStart() = {
      log.debug("Starting LogReceiverActor")
   }

   def receive: Receive = {
      case m @ JsonLogMessage(appName, jsonMap) =>
         log.debug(s"Received json for app: $appName")
         aggregator ! m
      case LineLogMessage(appName, line) =>
         log.debug(s"Received single line for app: $appName")
      case FileLogMessage(appName, bytes) =>
         log.debug(s"Received file for app: $appName")
   }
}
