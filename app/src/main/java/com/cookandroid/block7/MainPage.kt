package com.cookandroid.block7

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainPage : BaseActivity() {



    private lateinit var mplayerBackground: MediaPlayer
    private lateinit var mplayerButtonClick: MediaPlayer
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Initialize media players
        mplayerBackground = MediaPlayer.create(this, R.raw.background_music)
        mplayerButtonClick = MediaPlayer.create(this, R.raw.button_click_music)

        mplayerBackground.isLooping = true // 반복 재생 설정
        mplayerBackground.start()



        // 게임 시작 버튼에 클릭 리스너 추가
        val startGameFrame = findViewById<FrameLayout>(R.id.startGame)
        startGameFrame.setOnClickListener {
            onStartGameClick(it)
        }

        // 옵션 이미지에 클릭 리스너 추가
        val optionFrame = findViewById<FrameLayout>(R.id.option_game)
        optionFrame.setOnClickListener {
            onOptionClick(it)
        }

        // 종료 이미지에 클릭 리스너 추가
        val exitFrame = findViewById<FrameLayout>(R.id.Cancel)
        exitFrame.setOnClickListener {
            onQuitClick(it)
        }

    }

    fun onStartGameClick(view: View) {
        // 여기에 클릭 시 실행하고자 하는 동작 추가
        mplayerButtonClick.start()
        startGameWindow() // 게임 시작 화면과 연결되는 코드
    }

    fun onAchievementsClick(view: View){

        mplayerButtonClick.start()
        startAchiveWindow()
    }


    // 옵션 이미지에 클릭 리스너 추가
    fun onOptionClick (view: View){

        mplayerButtonClick.start()
        optionWindow()
    }

    // 종료 이미지에 클릭 리스너 추가
    fun onQuitClick(view: View){
        mplayerButtonClick.start()
        finish()
    }
    fun onMoreClick(view: View){
        mplayerButtonClick.start()

        moreWindow()
    }

    override fun onDestroy() {
        // 액티비티가 파괴될 때 미디어 플레이어를 해제
        mplayerBackground.release()
        super.onDestroy()
    }



    fun startGameWindow() {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    fun optionWindow() {
        val intent = Intent(this, OptionDialog::class.java)
        startActivity(intent)
    }
    fun startAchiveWindow(){
        val intent = Intent(this, AchievementsDialog::class.java)
        startActivity(intent)
    }
    fun moreWindow(){
        val intent = Intent(this, MoreDialog::class.java)
        startActivity(intent)
    }
}

