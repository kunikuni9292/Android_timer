package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

val handler = Handler(Looper.getMainLooper())
/**  handler用　時間カウントする周期　1秒　1000ミリ秒 **/
val oneSecond: Long = 1000
var minute = 0
var second = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickStartBtn(v: View){
        runTime.run()
        Startbutton.isEnabled = false
        PauseButton.isEnabled = true
        ResetButton.isEnabled = true
    }

    fun onClickPauseBtn(v: View){
        handler.removeCallbacks(runTime)
        Startbutton.isEnabled = true
        PauseButton.isEnabled = false
        ResetButton.isEnabled = true
    }

    fun onClickResetBtn(v: View){
        handler.removeCallbacks(runTime)
        minute = 0
        second = 0
        textView.text = "00:00"
        Startbutton.isEnabled = true
        PauseButton.isEnabled = false
        ResetButton.isEnabled = false
    }
    val runTime: Runnable = Runnable {
        run {
            handler.postDelayed(runTime, oneSecond)
            //1秒加算
            second += 1
            //60秒経ったら1分加算して秒を１からスタートさせる
            if (second == 60) {
                minute += 1
                second = 0
            }
            //ストップウォッチに入れるテキストを用意
            var tmpM = ""
            var tmpS = ""
            //秒
            if (second < 10) {
                tmpS = "0$second"
            } else if (second >= 10) {
                tmpS = "second"
            }
            //分
            if (minute < 10) {
                tmpM = "0$minute"
            } else if (minute >= 10) {
                tmpM = "minute"
            }
            textView.text = "$tmpM:$tmpS"
        }
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runTime)
    }
}