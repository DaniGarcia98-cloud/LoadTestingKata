package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class PostProduct extends Simulation {

  val httpProtocol = http
    .baseUrl("https://fakestoreapi.com")
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  val headers = Map("Content-Type" -> "application/json")

  val scn = scenario("Load Test - PostProduct")
    .exec(
      http("POST Request - Create Product")
        .post("/products")
        .headers(headers)
        .body(RawFileBody("PostProduct.json")) // Carga el JSON desde resources
        .asJson
        .check(status.is(200)) // Código de éxito para creación es 201
    )

  setUp(
    scn.inject(atOnceUsers(1)) // Puedes aumentar el número de usuarios si lo deseas
  ).protocols(httpProtocol)
}
