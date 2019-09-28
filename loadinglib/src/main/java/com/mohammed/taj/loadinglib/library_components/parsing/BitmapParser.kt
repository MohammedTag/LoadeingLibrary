package com.mohammed.taj.loadinglib.library_components.parsing

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.InputStream


/**
 * Created by Mohammed Sayed Taguldeen on 28,September,2019
 * Cairo, Egypt.
 */
class BitmapParser:BaseParser<Bitmap> {
    override fun parse(inputStream: InputStream): Bitmap = BitmapFactory.decodeStream(inputStream)
}