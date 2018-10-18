package org.simple.clinic.user.loginwithotp

import io.reactivex.Single
import org.simple.clinic.login.LoginResult

class ActualLoginWithOtp : LoginWithOtp {
  override fun loginWithOtp(phoneNumber: String, pin: String, otp: String): Single<LoginResult> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
