package com.mohammed.taj.pintristpinboard.presentaion_layer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mohammed.taj.pintristpinboard.R
import com.mohammed.taj.pintristpinboard.presentaion_layer.fragments.FeedsFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FeedsFragment.newInstance())
            .commitNow()

    }


}





