package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager.Factory
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton

class FacebookLoginActivity : AppCompatActivity() {
	private val callbackManager = Factory.create()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_facebook_login)
		val loginButton: LoginButton = findViewById(R.id.loginButton)
		loginButton.permissions = listOf("email", "user_posts")
		loginButton.authType = "rerequest"

		loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
			override fun onSuccess(result: LoginResult) {
				setResult(RESULT_OK)
				finish()
			}

			override fun onCancel() {
				setResult(RESULT_CANCELED)
				finish()
			}

			override fun onError(error: FacebookException) {
				Log.e("NIR", "failed to login to facebook...", error)
			}
		})
	}

	@Suppress("OVERRIDE_DEPRECATION")
	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		callbackManager.onActivityResult(requestCode, resultCode, data)
	}
}