package com.example.workswhale

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowInsetsControllerCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.workswhale.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var menuIcon = R.drawable.ic_main_view_type_grid_btn
    private var menuType = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.apply {
            // 상태바의 아이콘과 배경색 변경
            statusBarColor = Color.WHITE
            WindowInsetsControllerCompat(this, this.decorView).isAppearanceLightStatusBars = true
        }

        val adapter = ViewPagerAdapter(this)
        binding.viewPagerMain.adapter = adapter

        TabLayoutMediator(binding.tabLayoutBottom, binding.viewPagerMain) { tab, position ->
            when (position) {
                0 -> tab.text = "연락처"
                1 -> tab.text = "마이 페이지"
            }
        }.attach()

        binding.ivMainMenu.setOnClickListener {
            when (menuType) {
                0 -> { // 그리드 교체
                    menuIcon = R.drawable.ic_main_view_type_list_btn
                    menuType = 1
                    binding.ivMainMenu.setImageResource(menuIcon)
                }
                1 -> { // 리스트 교체
                    menuIcon = R.drawable.ic_main_view_type_grid_btn
                    menuType = 0
                    binding.ivMainMenu.setImageResource(menuIcon)
                }
                else -> {
                    val editMyPageDialog = EditMyProfileDialog()
                    editMyPageDialog.show(
                        supportFragmentManager, "EditMyProfileDialog"
                    )
                    adapter.editInfo("누구", "010-1111-1111", "whoami@anonymous.com")
                }
            }
        }

        binding.viewPagerMain.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binding.ivMainMenu.setImageResource(menuIcon)
                        menuType -= 2
                    }
                    else -> {
                        binding.ivMainMenu.setImageResource(R.drawable.ic_main_edit_user_info_btn)
                        menuType += 2
                    }
                }
            }
        })
    }
}