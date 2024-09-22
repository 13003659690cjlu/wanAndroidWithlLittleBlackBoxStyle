package com.lzm.wanandroidwithlittleblackboxstyle.view.fragment.accountpage

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.lzm.wanandroidwithlittleblackboxstyle.R
import com.lzm.wanandroidwithlittleblackboxstyle.viewmodel.AccountViewModel
import com.lzm.wanandroidwithlittleblackboxstyle.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory

class DataFragment(var vM: BaseViewModel) : Fragment() {

    private val logger: org.slf4j.Logger = LoggerFactory.getLogger(DataFragment::class.java)
    private lateinit var view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_data, container, false)

        val loginButton = view.findViewById<Button>(R.id.login)
        loginButton.setOnClickListener {
            showLoginDialog()
        }


        val lougoutButton = view.findViewById<Button>(R.id.logout)
        lougoutButton.setOnClickListener {
            logout()
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
            val username = dialogView.findViewById<EditText>(R.id.usernameEditText).text.toString()
            val password = dialogView.findViewById<EditText>(R.id.passwordEditText).text.toString()
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "用户名或密码不能为空", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    val loginData = (vM as AccountViewModel).login(username, password)
                    //ui操作放到主线程
                    Handler(Looper.getMainLooper()).post {
                        if ("登录成功".equals(loginData)) {
                            Toast.makeText(requireContext(), "登录成功", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                            view.findViewById<Button>(R.id.login).visibility = View.GONE
                            view.findViewById<Button>(R.id.register).visibility = View.GONE
                            view.findViewById<Button>(R.id.logout).visibility = View.VISIBLE
                        } else {
                            Toast.makeText(requireContext(), loginData, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
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



    fun logout(){
        lifecycleScope.launch {
            val logoutData = (vM as AccountViewModel).logout()
            Handler(Looper.getMainLooper()).post {
                if ("登出成功".equals(logoutData)) {
                    Toast.makeText(requireContext(), "登出成功", Toast.LENGTH_SHORT).show()
                    view.findViewById<Button>(R.id.login).visibility = View.VISIBLE
                    view.findViewById<Button>(R.id.register).visibility = View.VISIBLE
                    view.findViewById<Button>(R.id.logout).visibility = View.GONE
                } else {
                    Toast.makeText(requireContext(), logoutData, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}