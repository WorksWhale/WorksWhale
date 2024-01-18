package com.example.workswhale.mainActivity

import android.Manifest
import android.content.ContentValues.TAG
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
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
    // 하단의 뒤로가기 버튼을 눌렀을 때 종료 확인 다이얼로그가 뜨는 콜백 함수
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val builder = AlertDialog.Builder(this@MainActivity,
                R.style.MyAlertDialogStyle
            )
            builder.setTitle("WorksWhale")
            builder.setMessage("앱을 종료하시겠습니까?")
            builder.setIcon(R.drawable.ic_logo_white)
            builder.setCancelable(false)
            val listener = DialogInterface.OnClickListener { dialog, which ->
                finish()
            }
            builder.setPositiveButton("종료", listener)
            builder.setNegativeButton("취소", null)
            builder.show()
        }
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var detailFragment: ContactDetailFragment
    val adapter = ViewPagerAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        this.onBackPressedDispatcher.addCallback(this, callback)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 0)
        }

        window.apply {
            // 상태바의 아이콘과 배경색 변경
            statusBarColor = Color.WHITE
            WindowInsetsControllerCompat(this, this.decorView).isAppearanceLightStatusBars = true
        }
        with(binding){
            viewPagerMain.adapter = adapter

            TabLayoutMediator(tabLayoutMainBottom, viewPagerMain) { tab, position ->
                when (position) {
                    0 -> tab.text = "연락처"
                    1 -> tab.text = "마이 페이지"
                }
            }.attach()

            ivMainMenu.setOnClickListener {
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

            viewPagerMain.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 -> {
                            ivMainMenu.isVisible = false
                        }
                        else -> {
                            ivMainMenu.isVisible = true
                        }
                    }
                }
            })
        }

    }

    override fun onDataReceived(data: Contact.Person) {
        supportFragmentManager.commit {
            detailFragment = ContactDetailFragment.newInstance(data)
            replace(R.id.frame_layout_main, detailFragment)
            setReorderingAllowed(true)
            addToBackStack("")
            Log.d(TAG, "onDataReceived: $data")
        }
    }

    override fun update(position: Int) {
        adapter.updateLike(position)
    }
}