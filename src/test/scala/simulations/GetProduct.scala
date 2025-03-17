package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

class GetProduct extends Simulation {

  val httpProtocol = http
    .baseUrl("https://fakestoreapi.com")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  val scn = scenario("Load Test - GetProduct")
    .exec(
      http("GET")
        .get("/products")
        .check(status.is(200))
    )

  setUp(
    scn.inject(
      atOnceUsers(10), // Inicio con 10 usuarios
    )
  ).protocols(httpProtocol)
}
