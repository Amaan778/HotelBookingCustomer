package com.app.hotelbookingcustomer.OnBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.app.hotelbookingcustomer.R

class OnBoardingFragment : Fragment() {

    private var position: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_on_boarding, container, false)
        val image=view.findViewById<ImageView>(R.id.image)
        val text=view.findViewById<TextView>(R.id.text)

        when(position){
            0->{
                image.setImageResource(R.drawable.bookmarkboarding)
                text.text="Seamlessly Organize, Bookmark ."
            }
            1->{
                image.setImageResource(R.drawable.explorehotelboarding)
                text.text="Explore, Compare, and Find the Perfect Hotel"
            }
            2->{
                image.setImageResource(R.drawable.bookhoteladvance)
                text.text="Schedule Your Hotel Stays in advance"
            }
        }

        return view
    }

    companion object {
        private const val ARG_POSITION = "position"

        fun newInstance(position: Int): OnBoardingFragment {
            val fragment = OnBoardingFragment()
            val args = Bundle()
            args.putInt(ARG_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }
}