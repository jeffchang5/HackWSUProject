package jeffreychang.xyz.wholesomefoods.login

import android.support.v4.app.Fragment
import jeffreychang.xyz.wholesomefoods.R
import jeffreychang.xyz.wholesomefoods.login.LoginFragment.ChangeFragmentListener


class LoginActivity : android.support.v7.app.AppCompatActivity(), ChangeFragmentListener {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(jeffreychang.xyz.wholesomefoods.R.layout.activity_login)
        changeFragment(fragment = LoginFragment.newInstance())
    }

     override fun changeFragment(fragment: Fragment) {
         supportFragmentManager
             .beginTransaction()
             .replace(R.id.loginFragmentContainer, fragment)
             .commit()
     }


}

