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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lzm.wanandroidwithlittleblackboxstyle.R
import com.lzm.wanandroidwithlittleblackboxstyle.utils.ToastUtil
import com.lzm.wanandroidwithlittleblackboxstyle.viewmodel.AccountViewModel
import org.slf4j.LoggerFactory

class DataFragment() : Fragment() {

    private val logger: org.slf4j.Logger = LoggerFactory.getLogger(DataFragment::class.java)
    private lateinit var view: View
    private val accountViewModel:AccountViewModel by lazy {
        ViewModelProvider(this).get(AccountViewModel::class.java)
    }

    private val dialogView by lazy { layoutInflater.inflate(R.layout.login_dialog, null) }
    private val loginDialog by lazy { AlertDialog.Builder(requireContext())
        .setView(dialogView)
        .setTitle("登录界面")
        .create() }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_data, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginButton = view.findViewById<Button>(R.id.login)
        loginButton.setOnClickListener {
            showLoginDialog()
        }


        val lougoutButton = view.findViewById<Button>(R.id.logout)
        lougoutButton.setOnClickListener {
            accountViewModel.logout()
        }

        accountViewModel.loginInfo.observe(viewLifecycleOwner, Observer {
                loginInfo ->
            if(loginInfo.errorCode==0){
                loginDialog.dismiss()
                ToastUtil.showCustomToast(requireContext(), "登录成功")
                loginButton.visibility = View.GONE
                view.findViewById<Button>(R.id.register).visibility = View.GONE
                view.findViewById<Button>(R.id.logout).visibility = View.VISIBLE
            }else{
                ToastUtil.showCustomToast(requireContext(), "登录失败")
            }
        })



        accountViewModel.logoutInfo.observe(viewLifecycleOwner, Observer {
                logoutInfo ->
            if(logoutInfo.errorCode==0){
                ToastUtil.showCustomToast(requireContext(), "登出成功")
                loginButton.visibility = View.VISIBLE
                view.findViewById<Button>(R.id.register).visibility = View.VISIBLE
                view.findViewById<Button>(R.id.logout).visibility = View.GONE
            }else{
                ToastUtil.showCustomToast(requireContext(), "登出失败")
            }
        })

    }




    private fun showLoginDialog() {
        dialogView.findViewById<Button>(R.id.loginButton).setOnClickListener {
            val username = dialogView.findViewById<EditText>(R.id.usernameEditText).text.toString()
            val password = dialogView.findViewById<EditText>(R.id.passwordEditText).text.toString()
            if (username.isEmpty() || password.isEmpty()) {
                ToastUtil.showCustomToast(requireContext(),"用户名或密码不能为空")
            } else {
                accountViewModel.login(username,password)
            }
        }

        dialogView.findViewById<Button>(R.id.cancelButton).setOnClickListener {
            loginDialog.dismiss()
        }

        //设置密码栏状态
        val passwordEditText: EditText = dialogView.findViewById(R.id.passwordEditText)
        val showHidePasswordButton: ImageButton = dialogView.findViewById(R.id.showHidePasswordButton)
        // 初始时密码框为隐藏密码状态
        passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
        // 使用扩展函数处理密码可见性切换逻辑
        showHidePasswordButton.togglePasswordVisibility(passwordEditText)

        loginDialog.show()
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


    override fun onDestroy() {
        super.onDestroy()
    }

}