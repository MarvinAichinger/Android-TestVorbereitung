package com.example.myapplication

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.myapplication.databinding.FragmentSettingsBinding
import com.example.myapplication.model.SettingsForm

class SettingsFragment : Fragment() {

    var settings = SettingsForm(
        "", "",
        "", ""
    )

    val args: SettingsFragmentArgs by navArgs()
    lateinit var binding: FragmentSettingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (args.settings != null) {
            settings = args.settings!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //view?.findNavController()?.navigate(R.id.action_settingsFragment_to_editFragment)
        binding = FragmentSettingsBinding.inflate(inflater)
        setupMenu()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.name.text = settings.name
        binding.server.text = settings.server
        binding.username.text = settings.username
        binding.email.text = settings.email

        binding.changeButton.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToEditFragment(settings))
        }
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.helpFragment -> {
                        return NavigationUI.onNavDestinationSelected(menuItem,findNavController())
                    }
                }
                return false
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    companion object {

    }
}