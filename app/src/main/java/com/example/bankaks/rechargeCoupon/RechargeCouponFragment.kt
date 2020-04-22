package com.example.bankaks.rechargeCoupon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.bankaks.BaseFragment
import com.example.bankaks.MainViewModel
import com.example.bankaks.databinding.FragmentRechargeCouponBinding
import com.example.bankaks.util.viewLifecycle


/**
 * A simple [Fragment] subclass.
 */
class RechargeCouponFragment : BaseFragment() {

    private var binding: FragmentRechargeCouponBinding by viewLifecycle()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controllerComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRechargeCouponBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(requireView())
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java)

        viewModel.coupon.observe(viewLifecycleOwner, Observer {
            binding.rechargeCoupon.text = it
        })

        binding.done.setOnClickListener {
            navController.navigate(RechargeCouponFragmentDirections.actionRechargeCouponFragmentToUserDetailFragment())
        }

    }

}
