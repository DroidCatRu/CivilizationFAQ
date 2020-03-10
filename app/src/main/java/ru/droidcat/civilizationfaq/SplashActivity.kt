package ru.droidcat.civilizationfaq

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.FileOutputStream

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val queue = Volley.newRequestQueue(this)
        val url = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                Log.i("Connection", "JSON downloaded")
                writeToFile(response.toString())
                startActivity(Intent(this, MainContainer::class.java))
                finish()
            },
            Response.ErrorListener { error ->
                Log.i("Connection", error.toString())
                startActivity(Intent(this, MainContainer::class.java))
                finish()
            }
        )

        queue.add(stringRequest)
    }

    private fun writeToFile(str: String) {
        val outputStream: FileOutputStream
        try {
            outputStream =
                applicationContext.openFileOutput("tech.json", Context.MODE_PRIVATE)
            outputStream.write(str.toByteArray())
            outputStream.close()
        }
        catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}