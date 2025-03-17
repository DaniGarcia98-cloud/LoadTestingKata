package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class PutProduct extends Simulation {

  val httpProtocol = http
    .baseUrl("https://fakestoreapi.com")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  val headers = Map("Content-Type" -> "application/json")

  val scn = scenario("Load Test - PutProduct")
    .exec(
      http("PUT Request - Update Product")
        .put("/products/1")
        .headers(headers)
        .body(RawFileBody("PostProduct.json")) // Ruta corregida
        .asJson
        .check(status.is(200))
    )

  setUp(
    scn.inject(rampUsers(10).during(10)) // Mejora en la simulación
  ).protocols(httpProtocol)
}
