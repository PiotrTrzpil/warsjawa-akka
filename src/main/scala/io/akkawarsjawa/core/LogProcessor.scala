package io.akkawarsjawa.core

import akka.actor.Actor
import io.akkawarsjawa.core.LogReceiverActor.{JsonLogMessage, LineLogMessage}

class LogProcessor extends Actor{
   override def receive = {
      case LineLogMessage(appName, lineContent) =>
         context.parent ! JsonLogMessage(appName,
              Map("mesage" -> lineContent))
   }
}
