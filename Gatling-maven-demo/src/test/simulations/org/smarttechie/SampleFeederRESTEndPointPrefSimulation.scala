package org.smarttechie

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import com.typesafe.config._
class SampleFeederRESTEndPointPrefSimulation extends Simulation {
	    val conf = ConfigFactory.load()
	    val postsFeed = csv("posts.csv").circular //CSV Feeder

			val baseUrl = conf.getString("baseUrl")
			val httpProtocol = http.baseURL(baseUrl) //sample comment
			val restEndPoint = "/posts/${post_id}" // The value of the post_id filed from the feed will go here.

			val restEndpointScenario = scenario("Posts_Pref_Feed_Simulation")
			.feed(postsFeed) //pass the feed for scenario
			.exec(http("request_1")
			.get(restEndPoint))
			setUp(restEndpointScenario.inject(rampUsers(1000) over (10 seconds))).protocols(httpProtocol)
}