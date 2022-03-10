package com.example.testapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    lateinit var remoteViewModel: RemoteViewModel
    lateinit var adapter: RemoteImageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMembers()
        setUpViews(view)
        fetchDoggoImagesLiveData()
    }

    private fun fetchDoggoImagesLiveData() {
        remoteViewModel.fetchImagesLiveData().observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        })
    }

    private fun initMembers() {
        remoteViewModel = defaultViewModelProviderFactory.create(RemoteViewModel::class.java)
        adapter = RemoteImageAdapter()
    }

    private fun setUpViews(view: View) {
        view.findViewById<RecyclerView>(R.id.rvImages).adapter = adapter
    }

}