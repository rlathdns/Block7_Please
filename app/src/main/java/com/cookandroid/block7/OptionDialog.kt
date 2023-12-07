package com.cookandroid.block7

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class OptionDialog : AppCompatActivity() {

    private lateinit var mplayerBackground: MediaPlayer
    private lateinit var mplayerButtonClick: MediaPlayer
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.option_dialog)

        val bgmControl = findViewById<SeekBar>(R.id.background_music_control)
        val clickControl = findViewById<SeekBar>(R.id.ingame_music_control)
        val backgroundMusicSwitch = findViewById<Switch>(R.id.background_music_switch)
        val buttonClickMusicSwitch = findViewById<Switch>(R.id.button_click_switch)
        val exitOptionButton = findViewById<Button>(R.id.exit_option_button)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Initialize media players
        mplayerBackground = MediaPlayer.create(this, R.raw.background_music)
        mplayerButtonClick = MediaPlayer.create(this, R.raw.button_click_music)

        // 배경음악을 켜거나 끄는 버튼
        backgroundMusicSwitch.isChecked = sharedPreferences.getBoolean(KEY_BGM_SWITCH, true)
        backgroundMusicSwitch.setOnCheckedChangeListener { _, isChecked ->
            mplayerButtonClick.start()

            if (isChecked) {
                if (!mplayerBackground.isPlaying) {
                    mplayerBackground.start()
                }
            } else {
                if (mplayerBackground.isPlaying) {
                    mplayerBackground.pause()
                }
            }

            // 스위치 상태 저장
            saveSwitchState(KEY_BGM_SWITCH, isChecked)
        }

        // 효과음을 켜거나 끄는 버튼
        buttonClickMusicSwitch.isChecked = sharedPreferences.getBoolean(KEY_BUTTON_CLICK_SWITCH, true)
        buttonClickMusicSwitch.setOnCheckedChangeListener { _, isChecked ->
            mplayerButtonClick.start()

            if (isChecked) {
                if (!mplayerButtonClick.isPlaying) {
                    mplayerButtonClick.start()
                }
            } else {
                if (mplayerButtonClick.isPlaying) {
                    mplayerButtonClick.pause()
                }
            }

            // 스위치 상태 저장
            saveSwitchState(KEY_BUTTON_CLICK_SWITCH, isChecked)
        }

        bgmControl.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val volume = progress.toFloat() / bgmControl.max
                mplayerBackground.setVolume(volume, volume)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        clickControl.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val volume = progress.toFloat() / bgmControl.max
                mplayerButtonClick.setVolume(volume, volume)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        exitOptionButton.setOnClickListener {
            mplayerButtonClick.start()
            finish()
        }
    }

    private fun saveSwitchState(key: String, isChecked: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, isChecked)
        editor.apply()
    }

    companion object {
        const val KEY_BGM_SWITCH = "bgm_switch"
        const val KEY_BUTTON_CLICK_SWITCH = "button_click_switch"
    }
}