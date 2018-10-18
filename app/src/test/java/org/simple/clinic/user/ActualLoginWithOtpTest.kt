package org.simple.clinic.user

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.simple.clinic.login.LoginApiV1
import org.simple.clinic.login.LoginRequest
import org.simple.clinic.login.UserPayload
import org.simple.clinic.user.loginwithotp.ActualLoginWithOtp

class ActualLoginWithOtpTest {

  lateinit var loginWithOtp: ActualLoginWithOtp

  lateinit var loginApi: LoginApiV1

  @Before
  fun setUp() {
    loginApi = mock()

    loginWithOtp = ActualLoginWithOtp()
  }

  @Test
  fun `request should be made when logging in with otp`() {
    loginWithOtp.loginWithOtp(phoneNumber = "1234567890", pin = "1111", otp = "000000").blockingGet()

    verify(loginApi).login(LoginRequest(user = UserPayload(phoneNumber = "1234567890", pin = "1111", otp = "000000")))
  }

  
}
