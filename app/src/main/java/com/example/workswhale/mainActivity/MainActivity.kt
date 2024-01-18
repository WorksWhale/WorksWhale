package com.example.workswhale.mainActivity

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.view.WindowInsetsControllerCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.workswhale.Contact
import com.example.workswhale.contactDetailFragment.ContactDetailFragment
import com.example.workswhale.contactListFragment.ContactListFragment
import com.example.workswhale.R
import com.example.workswhale.contactDetailFragment.UpdateLike
import com.example.workswhale.databinding.ActivityMainBinding
import com.example.workswhale.editMyProfileDialog.EditMyProfileDialog
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), ContactListFragment.FragmentDataListener, UpdateLike {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var menuIcon = R.drawable.ic_main_view_type_grid_btn
    private var menuType = 2
    lateinit var detailFragment: ContactDetailFragment
    val adapter = ViewPagerAdapter(this)

    interface onBackPressedListener {
        fun onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.apply {
            // 상태바의 아이콘과 배경색 변경
            statusBarColor = Color.WHITE
            WindowInsetsControllerCompat(this, this.decorView).isAppearanceLightStatusBars = true
        }

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
                    val userInfo = adapter.getInfo()
                    val userProfileImage = adapter.getImageInfo()
                    val editMyPageDialog = EditMyProfileDialog(userInfo, userProfileImage)
                    editMyPageDialog.okClick = object: EditMyProfileDialog.OkClick {
                        override fun onClick(profileImage: Drawable, name: String, phoneNumber: String, email: String) {
                            adapter.editInfo(profileImage, name, phoneNumber, email)
                        }
                    }
                    editMyPageDialog.show(
                        supportFragmentManager, "EditMyProfileDialog"
                    )
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

    override fun onDataReceived(data: Contact.Person, position: Int) {
        supportFragmentManager.commit {
            detailFragment = ContactDetailFragment.newInstance(data, position)
            replace(R.id.frameLayout, detailFragment)
            setReorderingAllowed(true)
            addToBackStack("")
            Log.d(TAG, "onDataReceived: $data")
        }
    }

    override fun update(position: Int) {
        adapter.updateLike(position)
    }

//    override fun onBackPressed(){
//        //아래와 같은 코드를 추가하도록 한다
//        //해당 엑티비티에서 띄운 프래그먼트에서 뒤로가기를 누르게 되면 프래그먼트에서 구현한 onBackPressed 함수가 실행되게 된다.
//        val fragmentList = supportFragmentManager.fragments
//        for (fragment in fragmentList) {
//            if (fragment is onBackPressedListener) {
//                (fragment as onBackPressedListener).onBackPressed()
//                return
//            }
//        }
//    }

    override fun onStart() {
        super.onStart()
        Log.e("life", "a3_detail_onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("life", "a3_detail_onResume")
    }


    override fun onPause() {
        super.onPause()
        Log.e("life", "a3_detail_onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("life", "a3_detail_onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.e("life", "a3_detail_onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("life", "a3_detail_onDestroy")
    }
}