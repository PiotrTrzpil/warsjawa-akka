package io.akkawarsjawa.core

import akka.actor.{ActorLogging, Actor}
import io.akkawarsjawa.core.LogReceiverActor.JsonLogMessage
import reactivemongo.bson._
import scala.util.{Success, Failure}

class MongoSaver extends Actor with ActorLogging{

   import reactivemongo.api._
   import scala.concurrent.ExecutionContext.Implicits.global
   import collections.default.BSONCollectionProducer

   val driver = new MongoDriver
   val connection = driver.connection(List("localhost"))
   val db = connection("logs")

   override def receive = {
      case JsonLogMessage(appName, jsonMap) =>
//         val collection = db.collection(appName)
//         val doc: BSONDocument = BSONDocument(jsonMap.mapValues(BSONString))
//         collection.insert(doc).onComplete {
//            case Success(lastError) => log.debug("Successfully inserted log for app: " + appName)
//            case Failure(e) => log.error("Error while inserting log", e)
//         }
   }


}
