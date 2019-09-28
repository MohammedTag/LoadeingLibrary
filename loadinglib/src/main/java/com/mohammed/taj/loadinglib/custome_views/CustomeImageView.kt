package com.mohammed.taj.loadinglib.custome_views

import android.content.Context
import android.graphics.Bitmap
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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
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

    fun loadImage(url: String) {
        var bitmap=BitmapResponseCache.instance.getResponseFromCache(url)
        if(bitmap==null){
            RequestCall(Request(url = url, parser = bitmapParser)).getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { bitmap: Bitmap ->
                    BitmapResponseCache.instance.addResponseToCache(url,bitmap)
                    loaderImageView.setImageBitmap(bitmap)
                }
        }else{
            loaderImageView.setImageBitmap(bitmap)

        }
    }
}



