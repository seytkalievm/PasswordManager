package com.seytkalievm.passwordmanager.presentation.session.passwords

import android.app.AlertDialog
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.seytkalievm.passwordmanager.R
import com.seytkalievm.passwordmanager.databinding.DialogWebCredentialsBinding
import com.seytkalievm.passwordmanager.domain.model.WebsiteCredentials
import com.seytkalievm.passwordmanager.domain.use_case.website_credentials.DeleteCredentialsUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class PasswordDialogFragment (
    private val websiteCredentials: WebsiteCredentials
    ): DialogFragment() {

    private lateinit var binding: DialogWebCredentialsBinding

    @Inject
    lateinit var deleteCredentialsUseCase: DeleteCredentialsUseCase

    @Inject
    lateinit var clipboard: ClipboardManager

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        binding = DialogWebCredentialsBinding.inflate(layoutInflater)

        binding.apply {
            credentialsUsernameEt.setText(websiteCredentials.username)
            credentialsPasswordEt.setText(websiteCredentials.password)
            credentialsCopyLoginBtn.setOnClickListener { copyUsername() }
            credentialsCopyPasswordBtn.setOnClickListener { copyPassword() }
        }
        Log.i("PasswordDialogFragment", "onCreateDialog")

        fun delete(){
            GlobalScope.launch(Dispatchers.IO){
                deleteCredentialsUseCase(websiteCredentials)
            }
        }

        fun edit(){
            findNavController().navigate(R.id.action_passwordsFragment_to_editPasswordFragment)
        }

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setTitle(websiteCredentials.link)
            .setPositiveButton("Edit"){ _,_ -> edit()}
            .setNegativeButton("Delete"){_,_ -> delete()}
            .create()
    }


    private fun copyUsername(){
        val clip: ClipData = ClipData.newPlainText("username", websiteCredentials.username)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
    }

    private fun copyPassword(){
        val clip: ClipData = ClipData.newPlainText("password", websiteCredentials.password)

        clip.description.extras = PersistableBundle().apply {
            putBoolean("android.content.extra.IS_SENSITIVE", true)
        }

        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
    }


}