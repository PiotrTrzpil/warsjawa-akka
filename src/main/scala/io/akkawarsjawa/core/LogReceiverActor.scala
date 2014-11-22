package io.akkawarsjawa.core

import akka.actor._
import java.util.UUID
import com.codahale.metrics.{MetricRegistry, Meter}
import akka.event.Logging
import java.nio.charset.Charset
import akka.contrib.pattern.ReliableProxy

object LogReceiverActor {
   case class SendMessage(to: String, message: String)
   case class JsonLogMessage(appName: String, json: Map[String, String])
   case class LineLogMessage(appName: String, line: String)
   case class FileLogMessage(appName: String, bytes: Array[Byte])
}

class LogReceiverActor(aggregator: ActorRef) extends Actor with ActorLogging {

   import LogReceiverActor._
   import scala.concurrent.duration._
   val processor = context.actorOf(
      Props(classOf[LogProcessor]), "logProcessor")

   override def preStart() = {
      log.debug("Starting LogReceiverActor")
   }

   def receive: Receive = {
      case message @ JsonLogMessage(appName, jsonMap) =>
         log.debug(s"Received json for app: $appName")
         aggregator ! message
      case lineMessage @ LineLogMessage(appName, line) =>
         log.debug(s"Received single line for app: $appName")
         processor ! lineMessage
      case FileLogMessage(appName, bytes) =>
         log.debug(s"Received file for app: $appName")
         val content = new String(bytes, Charset.forName("UTF-8"))
         val lines = content.split("\n")
         for(line <- lines) {
            aggregator ! JsonLogMessage(appName,
               Map("message" -> line))
         }
   }
}
