package com.mohammed.taj.loadinglib.library_components.requests

import com.mohammed.taj.loadinglib.library_components.parsing.BaseParser


/**
 * Created by Mohammed Sayed Taguldeen on 28,September,2019
 * Cairo, Egypt.
 */
class Request<T>(var method: String = GET, var url: String, var parser: BaseParser<T>) {
    companion object {
        const val GET = "GET"
    }
}