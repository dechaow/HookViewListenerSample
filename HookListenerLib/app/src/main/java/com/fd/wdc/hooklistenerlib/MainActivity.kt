package com.fd.wdc.hooklistenerlib

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.fd.wdc.hooklistenerlib.proxytest.HookUtils
import com.fd.wdc.hooklistenerlib.proxytest.KHookUtils
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Title:
 * Description:
 * Created DateTime:2017/8/1
 * @author DeChao.Wang
 */
class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_button.setOnClickListener {
            v: View? ->
            //hook
            HookUtils.hookOnClick(v)
            // kotlin write
            KHookUtils.hookOnClick(v!!)
            //下面可以继续写自己的逻辑
            // but it don't i want
        }

    }
}