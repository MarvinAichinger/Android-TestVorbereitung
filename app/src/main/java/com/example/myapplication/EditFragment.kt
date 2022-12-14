package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentEditBinding
import com.example.myapplication.model.SettingsForm

class EditFragment : Fragment() {

    lateinit var binding: FragmentEditBinding

    val args: EditFragmentArgs by navArgs()

    lateinit var settings: SettingsForm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settings = args.settings
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_edit, container, false)
        binding = FragmentEditBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inputEmail.setText(settings.email)
        binding.inputName.setText(settings.name)
        binding.inputServer.setText(settings.server)
        binding.inputUsername.setText(settings.username)

        binding.submit.setOnClickListener {
            settings.name = binding.inputName.text.toString()
            settings.email = binding.inputEmail.text.toString()
            settings.username = binding.inputUsername.text.toString()
            settings.server = binding.inputServer.text.toString()

            //save
            val preferences = context?.applicationContext?.getSharedPreferences(MainActivity.PREFERENCE_FILENAME, Context.MODE_PRIVATE)?.edit()
            preferences?.putString(MainActivity.EMAIL_KEY, settings.email)
            preferences?.putString(MainActivity.USERNAME_KEY, settings.username)
            preferences?.putString(MainActivity.SERVER_KEY, settings.server)
            preferences?.putString(MainActivity.NAME_KEY, settings.name)
            preferences?.apply()

            findNavController().navigate(EditFragmentDirections.actionEditFragmentToSettingsFragment(settings))
        }
    }

    companion object {



    }

}