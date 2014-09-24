package io.akkawarsjawa.api

import spray.http.StatusCodes._
import spray.http._
import spray.routing._
import util.control.NonFatal
import akka.actor.{ActorLogging, Actor}
import spray.util.LoggingContext


class RoutedHttpService(route: Route) extends Actor with HttpService with ActorLogging {

   case class ErrorResponseException(responseStatus: StatusCode, response: Option[HttpEntity]) extends Exception

   implicit def actorRefFactory = context

  implicit val handler = ExceptionHandler {
    case NonFatal(ErrorResponseException(statusCode, entity)) => ctx =>
      ctx.complete(statusCode, entity)

    case NonFatal(e) => ctx => {
      log.error(e, InternalServerError.defaultMessage)
      ctx.complete(InternalServerError)
    }
  }


  def receive: Receive =
    runRoute(route)(handler, RejectionHandler.Default, context, RoutingSettings.default, LoggingContext.fromActorRefFactory)


}

