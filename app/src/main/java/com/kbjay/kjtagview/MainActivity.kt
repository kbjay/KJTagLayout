package com.kbjay.kjtagview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var provinces = arrayListOf<String>()
        provinces.add("北京")
        provinces.add("天津")
        provinces.add("河北")
        provinces.add("辽宁")
        provinces.add("吉林")
        provinces.add("黑龙江")
        provinces.add("山东")
        provinces.add("江苏")
        provinces.add("上海")
        provinces.add("浙江")
        provinces.add("安徽")
        provinces.add("福建")
        provinces.add("江西")
        provinces.add("广东")
        provinces.add("广西")
        provinces.add("海南")
        provinces.add("河南")
        provinces.add("湖南")
        provinces.add("湖北")
        provinces.add("山西")
        provinces.add("内蒙古")
        provinces.add("宁夏")
        provinces.add("青海")
        provinces.add("陕西")
        provinces.add("甘肃")
        provinces.add("新疆")
        provinces.add("四川")
        provinces.add("贵州")

        tagLayout.children.forEachIndexed { index, view ->
            (view as TextView).apply {
                text = provinces[index]
                textSize = when ((Math.random() * 10).toInt()) {
                    1, 2 -> 18f
                    3, 4 -> 16f
                    5, 6 -> 14f
                    7, 8 -> 20f
                    else -> 24f
                }
            }
        }
    }
}