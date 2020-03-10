package ru.droidcat.civilizationfaq

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.gson.GsonBuilder
import java.io.*
import java.util.ArrayList

class MainContainer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_container)
        if(savedInstanceState==null) {
            val navcontroller = Navigation.findNavController(this, R.id.nav_host_fragment)
            val listitems = fillData()
            val args = Bundle()
            args.putParcelableArrayList("TechList", listitems)
            navcontroller.setGraph(R.navigation.nav_graph, args)
        }
    }

    private fun fillData(): ArrayList<Tech> {
        val listitems: ArrayList<Tech> = ArrayList()
        val gsonBuilder = GsonBuilder().serializeNulls()
        val gson = gsonBuilder.create()
        val files: Array<String> = applicationContext.fileList()
        for (file in files) {
            Log.i("App files", file)
            if (file == "tech.json") {
                Log.i("JSON", readFromFile())
                val techList: List<Tech> =
                    gson.fromJson(
                        readFromFile(), Array<Tech>::class.java
                    ).toList()
                Log.i("List", techList.toString())

                for(tech in techList) {
                    if (tech.name != null) {
                        listitems.add(tech)
                    }
                }

            }
        }

        return listitems
    }

    private fun readFromFile(): String {
        var str = ""
        try {
            val inputStream: InputStream? = applicationContext.openFileInput("tech.json")
            if(inputStream != null) {
                val inputStreamReader =
                    InputStreamReader(inputStream)
                val bufferedReader =
                    BufferedReader(inputStreamReader)
                var receiveString: String?
                val stringBuilder = java.lang.StringBuilder()

                receiveString = bufferedReader.readLine()
                while (receiveString != null) {
                    stringBuilder.append(receiveString)
                    receiveString = bufferedReader.readLine()
                }
                inputStream.close()
                str = stringBuilder.toString()
            }

        } catch (e: FileNotFoundException) {
            Log.e("File reader", "File not found: $e")
        } catch (e: IOException) {
            Log.e("File reader", "Can not read file: $e")
        }
        return str
    }
}