package com.mohammed.taj.pintristpinboard.data_layer.models


/**
 * Created by Mohammed Sayed Taguldeen on 01,October,2019
 * Cairo, Egypt.
 */

data class UserBean(
    var id: String,
    var userName: String,
    var profileImage: String)
{
    class TestBuilder {
        companion object {
            fun buildUserModel() =
                UserBean(id="12",userName = "TestUser",profileImage = "random Image")

            fun buildList() =
                listOf(buildUserModel())
        }
    }

}