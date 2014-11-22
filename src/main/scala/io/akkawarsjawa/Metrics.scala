package io.akkawarsjawa

import com.codahale.metrics.{ConsoleReporter, MetricRegistry}
import java.util.concurrent.TimeUnit

object Metrics {
   val metrics = new MetricRegistry()

   val reporter = ConsoleReporter.forRegistry(Metrics.metrics)
     .convertRatesTo(TimeUnit.SECONDS)
     .convertDurationsTo(TimeUnit.MILLISECONDS)
     .build()
 //  reporter.start(1, TimeUnit.SECONDS)
}
