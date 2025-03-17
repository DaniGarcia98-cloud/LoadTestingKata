package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class DeleteProduct extends Simulation {
  val httpProtocol = http
    .baseUrl("https://fakestoreapi.com")
    .acceptHeader("application/json")

  val scn = scenario("Load Test - DeleteProduct")
    .exec(
      http("DELETE")
        .delete("/products/1")
        .check(status.is(200))
    )

  setUp(
    scn.inject(atOnceUsers(5))
  ).protocols(httpProtocol)
}
