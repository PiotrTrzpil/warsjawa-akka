package io.akkawarsjawa.core

import akka.actor.{ActorLogging, Actor}
import io.akkawarsjawa.core.LogReceiverActor.JsonLogMessage
import scala.util.{Success, Failure}

class MongoSaver extends Actor with ActorLogging{



   override def receive = {
      case JsonLogMessage(appName, jsonMap) =>
         //saving it
         log.info("Message saved.")
   }


}
