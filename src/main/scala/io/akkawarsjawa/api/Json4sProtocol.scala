package io.akkawarsjawa.api

import spray.httpx.Json4sSupport
import org.json4s._

object Json4sProtocol extends Json4sSupport {
   implicit def json4sFormats: Formats = DefaultFormats
}

