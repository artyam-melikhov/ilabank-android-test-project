package com.d8corp.d8amelihovstest

import android.os.Bundle
import android.view.View
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.d8corp.d8amelihovstest.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivityXML : AppCompatActivity() {

    val dataset = ArrayList<List<ListItem>>()
    var currentPosition = 0

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }

        binding.list.layoutManager = LinearLayoutManager(this)
        val recyclerAdapter = RecyclerAdapter(dataset.get(0))
        binding.list.adapter = recyclerAdapter

        binding.pager.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
            page.alpha = 0.25f + (1 - kotlin.math.abs(position))
        }
        binding.pager.setPageTransformer(pageTransformer)
        val itemDecoration = HorizontalMarginItemDecoration(
            this,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.pager.addItemDecoration(itemDecoration)
        val adapter = PagerAdapter(this, dataset.size)
        binding.pager.adapter = adapter
        binding.pager.registerOnPageChangeCallback(object : OnPageChangeCallback() {

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                currentPosition = position
                recyclerAdapter.updateData(dataset[position])
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

        binding.search.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val items = dataset[currentPosition]
                recyclerAdapter.updateData(
                    if(query.isNullOrEmpty())
                        items
                    else
                        items.filter { it.title.contains(query)  }
                )

                return false
            }
        })

        binding.fab.setOnClickListener({ v ->
            BottomDialog(recyclerAdapter.items, currentPosition + 1).show(supportFragmentManager, "btm_dlg_splash_init_err")
        })
    }

    fun initData() {
        dataset.clear()

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
            ListItem(title = "orange", subTitle = "sub orange"),
            ListItem(title = "apple", subTitle = "sub apple"),
            ListItem(title = "banana", subTitle = "sub banana"),
            ListItem(title = "apple", subTitle = "sub orange"),
            ListItem(title = "blueberry", subTitle = "sub blueberry")
        ))
    }
}