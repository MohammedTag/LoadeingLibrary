package com.mohammed.taj.pintristpinboard.presentaion_layer.adapters

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mohammed.taj.pintristpinboard.R
import com.mohammed.taj.pintristpinboard.data_layer.models.Feeds


/**
 * Created by Mohammed Sayed Taguldeen on 02,October,2019
 * Cairo, Egypt.
 */

class HomeAdapter(
    data: MutableList<Feeds>?
) :
    BaseQuickAdapter<Feeds, BaseViewHolder>(R.layout.home_feeds_adapter_item, data) {

    override fun convert(helper: BaseViewHolder?, item: Feeds?) {
        if (/*helper != null &&*/ item != null) {
            with(helper) {
                /*this?.getView<CustomeImageView>(R.id.userImage)?.loadImage(item.userModel.profileIamge)*/
                this?.setText(R.id.userName,item.userModel.userName)
            }
        }
    }
}