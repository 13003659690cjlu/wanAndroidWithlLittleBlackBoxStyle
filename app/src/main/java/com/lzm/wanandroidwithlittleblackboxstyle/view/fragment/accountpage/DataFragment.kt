package com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.accountpage

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.lzm.wanandroidwithlittleblackboxstyle.R
import com.lzm.wanandroidwithlittleblackboxstyle.viewmodel.BaseViewModel
import org.slf4j.LoggerFactory

class DataFragment(val vM: BaseViewModel) : Fragment() {

    private val logger: org.slf4j.Logger = LoggerFactory.getLogger(DataFragment::class.java)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_data, container, false)

        val loginButton = view.findViewById<Button>(R.id.login)

        loginButton.setOnClickListener {
            showLoginDialog()
        }

        return view
    }

    private fun showLoginDialog() {
        val dialogView = layoutInflater.inflate(R.layout.login_dialog, null)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("登录界面")
            .create()

        dialogView.findViewById<Button>(R.id.loginButton).setOnClickListener {

        }

        dialogView.findViewById<Button>(R.id.cancelButton).setOnClickListener {
            dialog.dismiss()
        }

        //设置密码栏状态
        val passwordEditText: EditText = dialogView.findViewById(R.id.passwordEditText)
        val showHidePasswordButton: ImageButton = dialogView.findViewById(R.id.showHidePasswordButton)
        // 初始时密码框为隐藏密码状态
        passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
        // 使用扩展函数处理密码可见性切换逻辑
        showHidePasswordButton.togglePasswordVisibility(passwordEditText)


        dialog.show()
    }



    //按钮密码是否可见
    fun ImageButton.togglePasswordVisibility(passwordEditText: EditText) {
        this.setOnClickListener {
            // 切换密码框可见性
            if (passwordEditText.transformationMethod == PasswordTransformationMethod.getInstance()) {
                // 当前为隐藏密码状态，切换为显示密码状态
                passwordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                this.setImageResource(R.drawable.ic_visibility_on)
            } else {
                // 当前为显示密码状态，切换为隐藏密码状态
                passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                this.setImageResource(R.drawable.ic_visibility_off)
            }
        }
    }
}