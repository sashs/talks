package de.scoding.ropyourway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import de.scoding.ropyourway.databinding.ActivityMainBinding
import de.scoding.ropyourway.network.service.MessageService
import de.scoding.ropyourway.network.service.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val ms = RetrofitHelper.getInstance().create(MessageService::class.java)
            GlobalScope.launch {
                val result = ms.getMessages()
                if (result != null){
                    val msg = result.body()?.get(0)

                    val message = msg?.message
                    val length = msg?.length

                    Log.d("ropyourway", message ?: "")
                    parseMessage(Base64.decode(message, Base64.DEFAULT), length ?: 0)
                }
            }
        }

    }

    /**
     * A native method that is implemented by the 'ropyourway' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    external fun parseMessage(message: ByteArray, length: Int): String

    companion object {
        // Used to load the 'ropyourway' library on application startup.
        init {
            System.loadLibrary("ropyourway")
        }
    }
}