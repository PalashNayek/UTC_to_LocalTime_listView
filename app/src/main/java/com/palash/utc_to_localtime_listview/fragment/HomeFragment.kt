package com.palash.utc_to_localtime_listview.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.palash.utc_to_localtime_listview.R
import com.palash.utc_to_localtime_listview.adapter.ProductAdapter
import com.palash.utc_to_localtime_listview.databinding.FragmentHomeBinding
import com.palash.utc_to_localtime_listview.models.ProgrammingModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var recyclerView: RecyclerView? = null
    private val adapter = ProductAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.adapter = adapter

        //add data
        val p1 = ProgrammingModel(1, "P", "Palash", "2020-03-02T00:00:00.000Z")
        val p2 = ProgrammingModel(2, "P", "Piyali", "2019-07-20T20:00:00.000Z")
        val p3 = ProgrammingModel(3, "A", "Angshu", "2022-10-25T00:10:00.000Z")
        adapter.submitList(listOf(p1, p2, p3))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed(kotlinx.coroutines.Runnable {
            //add data
            val p5 = ProgrammingModel(5, "P", "Prasanta", "2020-03-02T00:00:00.000Z")
            val p4 = ProgrammingModel(4, "P", "Prasenjit", "2019-07-20T20:00:00.000Z")
            val p3 = ProgrammingModel(3, "A", "Angshu", "2022-10-25T00:10:00.000Z")
            adapter.submitList(listOf(p5, p4, p3))
        },5000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}