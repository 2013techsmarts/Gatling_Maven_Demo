package org.smarttechie

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import com.typesafe.config._
class SampleRESTEndPointPrefSimulation extends Simulation {
	    val conf = ConfigFactory.load()
			val baseUrl = conf.getString("baseUrl")
			val httpProtocol = http.baseURL(baseUrl) //sample comment
			val restEndPoint = "/posts"

			val restEndpointScenario = scenario("Posts_Pref_Simulation")
			.exec(http("request_1")
					.get(restEndPoint))
			setUp(restEndpointScenario.inject(rampUsers(1000) over (10 seconds))).protocols(httpProtocol)
}