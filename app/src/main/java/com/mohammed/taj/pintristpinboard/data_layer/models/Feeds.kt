package com.mohammed.taj.pintristpinboard.data_layer.models


/**
 * Created by Mohammed Sayed Taguldeen on 01,October,2019
 * Cairo, Egypt.
 */
data class Feeds(
    var id: String, var width: Int, var height: Int = 0, var color: String,
    var likes: Int,
    var liked_by_user: Boolean,
    var userModel: UserBean){
    class TestBuilder {
        companion object {
            fun buildTestFeedModel() =
                Feeds(id="12",width = 1,height = 1,color = "#12123",liked_by_user = true,likes = 1000,userModel = UserBean.TestBuilder.buildUserModel())

            fun buildList() =
                listOf(buildTestFeedModel())
        }
    }
}