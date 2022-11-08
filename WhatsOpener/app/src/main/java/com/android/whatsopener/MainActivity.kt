package com.android.whatsopener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var number:String="0"
        if(intent.action==Intent.ACTION_PROCESS_TEXT)
        {
           number= intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }
        if(number.isDigitsOnly())
        {
            startWhatsapp(number)
        }
        else
        {
            Toast.makeText(this, "Enter the Valid Number", Toast.LENGTH_SHORT).show()
        }

    }
    private fun startWhatsapp(number:String)
    {
        val intent=Intent()
        intent.action=Intent.ACTION_VIEW
        intent.setPackage("com.whatsapp")
        val number2:String = if(number[0]=='+') {
            number.substring(1)
        } else if(number.length==10) {
            "+91+$number"
        } else {
            number
        }
        intent.data= Uri.parse("https://wa.me/ $number2")
        if(packageManager.hasSystemFeature("com.whatsapp")!=null)
        {
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Please Install WhatsApp", Toast.LENGTH_SHORT).show()
        }
        finish()
    }
}