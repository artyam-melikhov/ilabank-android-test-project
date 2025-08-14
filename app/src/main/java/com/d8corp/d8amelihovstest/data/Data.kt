package com.d8corp.d8amelihovstest.data

import com.d8corp.d8amelihovstest.ListItem

object Data {

    val dataset = ArrayList<List<ListItem>>()

    init {
        dataset.add(mutableListOf(
            ListItem(title = "apple", subTitle = "sub apple"),
            ListItem(title = "banana", subTitle = "sub banana"),
            ListItem(title = "orange", subTitle = "sub orange"),
            ListItem(title = "blueberry", subTitle = "sub blueberry"),
            ListItem(title = "apple", subTitle = "sub apple"),
            ListItem(title = "banana", subTitle = "sub banana"),
            ListItem(title = "orange", subTitle = "sub orange"),
            ListItem(title = "blueberry", subTitle = "sub blueberry"),
            ListItem(title = "apple", subTitle = "sub apple"),
            ListItem(title = "banana", subTitle = "sub banana"),
            ListItem(title = "orange", subTitle = "sub orange"),
            ListItem(title = "blueberry", subTitle = "sub blueberry"),
            ListItem(title = "apple", subTitle = "sub apple"),
            ListItem(title = "banana", subTitle = "sub banana"),
            ListItem(title = "orange", subTitle = "sub orange"),
            ListItem(title = "blueberry", subTitle = "sub blueberry")
        ))

        dataset.add(mutableListOf(
            ListItem(title = "blueberry", subTitle = "sub blueberry"),
            ListItem(title = "apple", subTitle = "sub apple"),
            ListItem(title = "orange", subTitle = "sub orange"),
            ListItem(title = "apple", subTitle = "sub apple"),
            ListItem(title = "banana", subTitle = "sub banana"),
            ListItem(title = "apple", subTitle = "sub orange"),
            ListItem(title = "blueberry", subTitle = "sub blueberry"),
            ListItem(title = "apple", subTitle = "sub apple"),
            ListItem(title = "orange", subTitle = "sub orange"),
            ListItem(title = "apple", subTitle = "sub apple"),
            ListItem(title = "banana", subTitle = "sub banana"),
            ListItem(title = "apple", subTitle = "sub orange"),
            ListItem(title = "blueberry", subTitle = "sub blueberry"),
            ListItem(title = "apple", subTitle = "sub apple"),
            ListItem(title = "orange", subTitle = "sub orange"),
            ListItem(title = "apple", subTitle = "sub apple"),
            ListItem(title = "banana", subTitle = "sub banana"),
            ListItem(title = "apple", subTitle = "sub orange"),
            ListItem(title = "blueberry", subTitle = "sub blueberry"),
            ListItem(title = "apple", subTitle = "sub apple"),
            ListItem(title = "banana", subTitle = "sub banana"),
            ListItem(title = "apple", subTitle = "sub orange")
        ))
    }
}