package io.akkawarsjawa.api

import akka.actor.ActorRef
import spray.routing.Directives
import io.akkawarsjawa.core.LogReceiverActor
import spray.http.{MultipartFormData, BodyPart}
import com.codahale.metrics.{ConsoleReporter, Meter}
import java.util.concurrent.TimeUnit
import io.akkawarsjawa.Metrics

class LogReceiverService(messenger: ActorRef) extends Directives {

   import LogReceiverActor._

   val requests : Meter = Metrics.metrics.meter("requests")

   val routeLine =
     path("log-line" / Segment) { appName =>
       post {
          handleWith { line: String =>
                requests.mark()
                messenger ! LineLogMessage(appName, line)
                s"Got it: $line from app: $appName"
          }
       }
     }

   val routeFile =
      path("log-file" / Segment) { appName =>
         post {
            entity(as[MultipartFormData]) { formData =>
               complete {
                  requests.mark()
                  val details = formData.fields.map {
                     case BodyPart(entity, headers) =>
                        val data = entity.data.toByteArray
                        messenger ! FileLogMessage(appName, data)
                        data
                  }
                  "Uploaded files: "+details.size+"for app: "+appName
               }
            }
         }
      }

   import Json4sProtocol._
   val routeJson =
      path("log-json" / Segment) { appName =>
         post {
            handleWith {
               map: Map[String, String] =>
                  requests.mark()
                  messenger ! JsonLogMessage(appName, map)
                  s"Got it: $map from app: $appName"
            }
         }
      }
   
   
   val route = routeFile ~ routeLine ~ routeJson


}