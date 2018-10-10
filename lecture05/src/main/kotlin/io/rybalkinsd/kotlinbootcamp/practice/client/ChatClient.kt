package io.rybalkinsd.kotlinbootcamp.practice.client

import com.kohttp.dsl.httpGet
import com.kohttp.dsl.httpPost
import com.kohttp.ext.eager
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

object ChatClient {
    // Change to server url
    private const val HOST = "54.224.37.210" // "localhost" "54.224.37.210"
    private const val PORT = 8080

    /**
     * POST /chat/login?name=my_name
     */
    fun login(name: String) = httpPost {
        host = HOST
        port = PORT
        path = "/chat/login"
        body {
            form {
                "name" to name
            }
        }
    }.eager().also { println(it) }

    /**
     * GET /chat/history
     */
    fun viewHistory(): Response = httpGet {
        host = HOST
        port = PORT
        path = "/chat/history"
    }

    /**
     * POST /chat/say
     *
     * Body: "name=my_name&msg='my_message'"
     */
    fun say(name: String, msg: String): Response = httpPost {
        host = HOST
        port = PORT
        path = "/chat/say"
        body {
            form {
                "name" to name
                "msg" to msg
            }
        }
    }

    /**
     * GET /chat/online
     */
    fun viewOnline() = httpGet {
        host = HOST
        port = PORT
        path = "/chat/online"
    }.eager().also { println(it) }

    /**
     * DELETE /chat/logout?name=my_name
     */
    fun logout(name: String): Response {
        val apply = Request.Builder().apply {
            url("http://$HOST:$PORT//chat/logout?name=$name")
            delete()
        }.build()
        return OkHttpClient.Builder().build().newCall(apply).execute()
    }
}
