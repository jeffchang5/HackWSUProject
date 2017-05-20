package jeffreychang.xyz.wholesomefoods.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import jeffreychang.xyz.wholesomefoods.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.verbose

class LoginFragment : BaseFragment(), View.OnClickListener {

    interface ChangeFragmentListener {
        fun changeFragment(fragment: Fragment): Unit
    }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: android.view.LayoutInflater?, container: android.view.ViewGroup?,
                              savedInstanceState: android.os.Bundle?): android.view.View? {
        return inflater!!.inflate(jeffreychang.xyz.wholesomefoods.R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        email_sign_in_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if ((email.text.toString().equals("a")) || password.text.toString().equals("b"))
            (activity as ChangeFragmentListener).changeFragment(QuestionFragment.newInstance())

        else toast("Login credentials are invalid.")

    }
    companion object {
        fun newInstance(): LoginFragment{
            return LoginFragment()
        }
    }


}
