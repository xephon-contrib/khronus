/*
 * =========================================================================================
 * Copyright © 2014 the metrik project <https://github.com/hotels-tech/metrik>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 * =========================================================================================
 */

package com.despegar.metrik.web.service.influx

import spray.http.HttpHeaders._
import spray.http.HttpMethods._
import spray.http._
import spray.routing._

trait CORSSupport extends Directives {
  this: HttpService ⇒

  private val CORSHeaders = List(
    `Access-Control-Allow-Methods`(GET, POST, PUT, DELETE, OPTIONS),
    `Access-Control-Allow-Headers`("Origin, X-Requested-With, Content-Type, Accept, Accept-Encoding, Accept-Language, Host, Referer, User-Agent"),
    `Access-Control-Allow-Credentials`(true))

  def respondWithCORS(routes: ⇒ Route) = {
    val originHeader = `Access-Control-Allow-Origin`(AllOrigins)

    respondWithHeaders(originHeader :: CORSHeaders) {
      routes ~ options { complete(StatusCodes.OK) }
    }
  }
}