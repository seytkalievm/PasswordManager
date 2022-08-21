package com.seytkalievm.passwordmanager.presentation.session.passwords.edit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seytkalievm.passwordmanager.R

class EditPasswordFragment : Fragment() {

    companion object {
        fun newInstance() = EditPasswordFragment()
    }

    private lateinit var viewModel: EditPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_password, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditPasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}