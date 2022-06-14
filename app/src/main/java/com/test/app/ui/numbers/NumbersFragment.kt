package com.test.app.ui.numbers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.test.app.R
import com.test.app.databinding.FragmentNumbersBinding
import com.test.app.ui.common.showErrorBar
import com.test.app.ui.common.showToast
import com.test.app.ui.numbers.viewmodel.NumbersViewModel
import com.test.app.ui.numbers.viewmodel.NumbersViewState
import com.test.numberslib.data.cache.entity.Data
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NumbersFragment : Fragment() {

    private val numbersViewModel: NumbersViewModel by viewModels()
    private lateinit var binding: FragmentNumbersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentNumbersBinding.inflate(inflater, container, false).apply {
            binding = this
            lifecycleOwner = this@NumbersFragment
            vm = numbersViewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupListView()
        observeViewState()

        binding.addButton.setOnClickListener {
            handleAdd()
        }

        binding.deleteButton.setOnClickListener {
            handleDelete()
        }
    }

    private fun setupListView() {
        with(binding.numbersList) {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = NumbersAdapter()
        }
    }

    private fun observeViewState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                numbersViewModel.viewState.collect {
                    when (it) {
                        is NumbersViewState.Loading -> {
                            binding.isLoading = true
                        }
                        is NumbersViewState.Success -> {
                            handleSuccess(it.numbers)
                        }
                        is NumbersViewState.Error -> {
                            handleError(it.msg)
                        }
                    }
                }
            }
        }
    }

    private fun handleSuccess(numbers: List<Data>?) {
        binding.isLoading = false
        if (numbers.isNullOrEmpty()) {
            binding.isEmpty = true
        } else {
            binding.isEmpty = false
            (binding.numbersList.adapter as NumbersAdapter).submitList(numbers)
        }
    }

    private fun handleError(msg: String?) {
        binding.isLoading = false
        binding.isEmpty = true
        showErrorBar(msg = msg)
    }

    private fun handleDelete() {
        viewLifecycleOwner.lifecycleScope.launch {
            val entry = binding.entry.text.toString()

            if (numbersViewModel.isNumberValid(entry)) {
                val value = entry.toInt()
                if (numbersViewModel.doesNumberExists(value)) {
                    numbersViewModel.deleteNumber(value)
                } else {
                    showToast(getString(R.string.numbers_does_not_exists))
                }
            } else {
                showToast(getString(R.string.invalid_number))
            }
        }
    }

    private fun handleAdd() {
        val entry = binding.entry.text.toString()
        if (numbersViewModel.isNumberValid(entry)) {
            numbersViewModel.addNumber(entry.toInt())
        } else {
            showToast(getString(R.string.invalid_number))
        }
    }
}