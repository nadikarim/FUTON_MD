package com.capstone.futon.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.futon.R
import com.capstone.futon.data.model.ListItem
import com.capstone.futon.databinding.FragmentPlantBinding

class AnimalFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()
    private lateinit var adapter: ListAdapter
    private var  _binding: FragmentPlantBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<ListItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPlantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListAdapter()

        list.addAll(listPlant)
        viewModel.listAnimal()
        viewModel.animal.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        setRecyclerView()

    }

    private val listPlant: ArrayList<ListItem>
        get() {
            val dataName = resources.getStringArray(R.array.data_hewan_name)
            val dataDescription = resources.getStringArray(R.array.data_hewan_description)
            val dataPhoto = resources.getStringArray(R.array.data_hewan_photo)
            val listHero = ArrayList<ListItem>()
            for (i in dataName.indices) {
                val hero = ListItem(dataName[i],dataPhoto[i], dataDescription[i])
                listHero.add(hero)
            }
            return listHero
        }

    private fun setRecyclerView() {
        binding.rvPlant.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvPlant.setHasFixedSize(true)
        binding.rvPlant.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}