package org.simple.clinic.user.loginwithotp

import io.reactivex.Single
import org.simple.clinic.login.LoginResult

interface LoginWithOtp {

  fun loginWithOtp(phoneNumber: String, pin: String, otp: String): Single<LoginResult>
}

