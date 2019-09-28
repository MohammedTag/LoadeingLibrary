package com.mohammed.taj.loadinglib.library_components.parsing

import java.io.InputStream


/**
 * Created by Mohammed Sayed Taguldeen on 28,September,2019
 * Cairo, Egypt.
 */
interface BaseParser<T> {
    fun parse(inputStream: InputStream): T
}