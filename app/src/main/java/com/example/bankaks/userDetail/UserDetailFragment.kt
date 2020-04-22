package com.example.bankaks.userDetail

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.bankaks.BaseFragment
import com.example.bankaks.MainViewModel
import com.example.bankaks.R
import com.example.bankaks.databinding.FragmentUserDetailBinding
import com.example.bankaks.model.Recharge
import com.example.bankaks.util.KeyboardUtil
import com.example.bankaks.util.showToast
import com.example.bankaks.util.viewLifecycle
import com.momentsnap.android.EventObserver


/**
 * A simple [Fragment] subclass.
 */
class UserDetailFragment : BaseFragment() {

    private var binding: FragmentUserDetailBinding by viewLifecycle()
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
        binding = FragmentUserDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireView())
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.name.text = "${it.firstName} ${it.lastName}"
            binding.sales.text = "${it.salesAmount} ${it.currency} "
            val content = SpannableString("View Transaction")
            content.setSpan(UnderlineSpan(), 0, content.length, 0)
            binding.transaction.text = content
        })

        binding.generate.setOnClickListener {

            KeyboardUtil.hideSoftKeyboard(requireActivity())

            binding.loader.visibility = View.VISIBLE

            var amount = ""
            when (binding.chipGroup.checkedChipId) {
                R.id.chip10 -> amount = "10"
                R.id.chip25 -> amount = "25"
                R.id.chip40 -> amount = "40"
            }

            val recharge =
                Recharge(amount = amount, mobileNumber = binding.phoneNumber.text.toString())

            viewModel.getRechargeCoupon(recharge)

        }

        viewModel.rechargeCoupon.observe(viewLifecycleOwner, EventObserver {
            binding.loader.visibility = View.GONE
            navController.navigate(UserDetailFragmentDirections.actionUserDetailFragmentToRechargeCouponFragment())
        })

        viewModel.error.observe(viewLifecycleOwner, EventObserver {
            binding.loader.visibility = View.GONE
            showToast(it)
        })

    }

}
