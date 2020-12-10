@file:Suppress("unused")

package com.cesarnorena.pokedex.data.repository.remote.client

object HttpStatus {

    // --- 2xx Success ---

    /** 200 OK (HTTP/1.0 - RFC 1945)  */
    const val OK = 200

    /** 201 Created (HTTP/1.0 - RFC 1945)  */
    const val CREATED = 201

    /** 202 Accepted (HTTP/1.0 - RFC 1945)  */
    const val ACCEPTED = 202

    /** 204 No Content (HTTP/1.0 - RFC 1945)  */
    const val NO_CONTENT = 204

    // --- 3xx Redirection ---

    /** 304 Not Modified (HTTP/1.0 - RFC 1945)  */
    const val NOT_MODIFIED = 304

    // --- 4xx Client Error ---

    /** 400 Bad Request (HTTP/1.1 - RFC 2616)  */
    const val BAD_REQUEST = 400

    /** 401 Unauthorized (HTTP/1.0 - RFC 1945)  */
    const val UNAUTHORIZED = 401

    /** 402 Payment Required  */
    const val PAYMENT_REQUIRED = 402

    /** 403 Forbidden (HTTP/1.0 - RFC 1945)  */
    const val FORBIDDEN = 403

    /** 404 Not Found (HTTP/1.0 - RFC 1945)  */
    const val NOT_FOUND = 404

    /** 406 Not acceptable (HTTP/1.1 - RFC 7231) */
    const val NOT_ACCEPTABLE = 406

    /** 408 Request Timeout (HTTP/1.1 - RFC 2616)  */
    const val REQUEST_TIMEOUT = 408

    /** 409 Conflict (HTTP/1.1 - RFC 2616)  */
    const val CONFLICT = 409

    /** 410 Gone */
    const val GONE = 410

    /** 413 Request entity too large */
    const val REQUEST_TOO_LARGE = 413

    // --- 5xx Server Error ---

    /** 500 Server Error (HTTP/1.0 - RFC 1945)  */
    const val INTERNAL_SERVER_ERROR = 500

    /** 503 Service Unavailable (HTTP/1.0 - RFC 1945)  */
    const val SERVICE_UNAVAILABLE = 503
}
