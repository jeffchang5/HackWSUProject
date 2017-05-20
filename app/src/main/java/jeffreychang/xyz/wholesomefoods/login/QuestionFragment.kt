package jeffreychang.xyz.wholesomefoods.login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import jeffreychang.xyz.wholesomefoods.R


class QuestionFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_question, container, false)
    }
    companion object {
        fun newInstance(): QuestionFragment {
            return QuestionFragment()
        }
    }


}
