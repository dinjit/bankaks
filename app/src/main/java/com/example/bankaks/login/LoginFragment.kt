package com.example.bankaks.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.bankaks.BaseFragment
import com.example.bankaks.MainViewModel
import com.example.bankaks.databinding.FragmentLoginBinding
import com.example.bankaks.model.Creds
import com.example.bankaks.util.showToast
import com.example.bankaks.util.viewLifecycle
import com.momentsnap.android.EventObserver

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseFragment() {

    private var binding: FragmentLoginBinding by viewLifecycle()
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
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireView())

        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java)

        binding.login.setOnClickListener {

            viewModel.login(
                Creds(
                    email = binding.email.text.toString(),
                    password = binding.password.text.toString()
                )
            )
        }

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToUserDetailFragment())
        })

        viewModel.error.observe(viewLifecycleOwner, EventObserver {
            showToast(it)
        })

    }

}
