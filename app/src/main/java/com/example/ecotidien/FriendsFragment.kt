package com.example.ecotidien

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ecotidien.adapter.FriendsAdapter
import com.example.ecotidien.data.FriendsDataSource

/**
 * A simple [Fragment] subclass.
 * Use the [FriendsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FriendsFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val friendLayout = inflater.inflate(R.layout.fragment_friends, container, false)
        val dataset = FriendsDataSource().getAllFriends()
        val recyclerView = friendLayout.findViewById<RecyclerView>(R.id.friends_recycler_view)
        recyclerView.adapter = FriendsAdapter(dataset)
         return friendLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}