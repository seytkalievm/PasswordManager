package com.seytkalievm.passwordmanager.presentation.session.passwords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.seytkalievm.passwordmanager.databinding.WebsiteCredentialsItemBinding
import com.seytkalievm.passwordmanager.domain.model.WebsiteCredentials

class WebsiteCredentialsRecyclerViewAdapter(private val onClickListener: OnClickListener):
    ListAdapter<WebsiteCredentials, WebsiteCredentialsRecyclerViewAdapter.CredentialsViewHolder>(
        DiffCallback
    ){

    inner class CredentialsViewHolder (private val binding: WebsiteCredentialsItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(credentials: WebsiteCredentials){
                binding.apply {
                    websiteName.text = credentials.link
                    websiteUsername.text = credentials.username
                }
            }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<WebsiteCredentials>(){
        override fun areItemsTheSame(
            oldItem: WebsiteCredentials,
            newItem: WebsiteCredentials
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: WebsiteCredentials,
            newItem: WebsiteCredentials
        ): Boolean {
            return oldItem.link == newItem.link && oldItem.username == newItem.username
                    && oldItem.password == newItem.password
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CredentialsViewHolder {
        return CredentialsViewHolder(
            WebsiteCredentialsItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CredentialsViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }


    class OnClickListener(private val clickListener: (credentials: WebsiteCredentials) -> Unit){
        fun onClick(credentials: WebsiteCredentials) = clickListener(credentials)
    }

}