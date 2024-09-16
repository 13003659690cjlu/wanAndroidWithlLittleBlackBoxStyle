package com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.squarepage

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lzm.wanandroidwithlittleblackboxstyle.R
import org.slf4j.LoggerFactory


class ColumnFragment : Fragment() {
    private val logger: org.slf4j.Logger = LoggerFactory.getLogger("ColumnPage")
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        logger.info("绑定")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logger.info("绑定")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        logger.info("初始化ColumnFragmetn")
        return inflater.inflate(R.layout.fragment_column, container, false)
    }

}