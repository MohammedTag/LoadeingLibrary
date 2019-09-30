package com.mohammed.taj.loadinglib.custome_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.mohammed.taj.loadinglib.R
import com.mohammed.taj.loadinglib.library_components.Caching.BitmapResponseCache
import com.mohammed.taj.loadinglib.library_components.parsing.BitmapParser
import com.mohammed.taj.loadinglib.library_components.requests.Request
import com.mohammed.taj.loadinglib.library_components.requests.RequestCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Mohammed Sayed Taguldeen on 28,September,2019
 * Cairo, Egypt.
 */
class CustomeImageView : RelativeLayout {
    private var mInflater: LayoutInflater
    private lateinit var v: View
    private lateinit var loaderImageView: ImageView
    private var bitmapParser = BitmapParser()

    constructor(context: Context) : super(context) {
        mInflater = LayoutInflater.from(context)
        init()

    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        mInflater = LayoutInflater.from(context)
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mInflater = LayoutInflater.from(context)
        init()
    }

    fun init() {
        v = mInflater.inflate(R.layout.customeimageview, this, true)
        loaderImageView = v.findViewById(R.id.imageView) as ImageView
    }

    suspend fun loadImage(url: String) {

        var bitmap = BitmapResponseCache.instance.getResponseFromCache(url)
        if (bitmap == null) {
            CoroutineScope(IO).launch {
                bitmap = RequestCall(Request(url = url, parser = bitmapParser)).getData()
                bitmap?.let {
                    BitmapResponseCache.instance.addResponseToCache(url, it)
                }
            }

            withContext(Main) {
                loaderImageView.setImageBitmap(bitmap)
            }
        } else {
            loaderImageView.setImageBitmap(bitmap)
        }
    }
}



