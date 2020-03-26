package io.simplelogin.android.module.alias.search

import android.animation.AnimatorInflater
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.simplelogin.android.R
import io.simplelogin.android.databinding.FragmentAliasSearchBinding
import io.simplelogin.android.module.alias.AliasListAdapter
import io.simplelogin.android.module.home.HomeActivity
import io.simplelogin.android.utils.baseclass.BaseFragment
import io.simplelogin.android.utils.extension.*
import io.simplelogin.android.utils.model.Alias

class AliasSearchFragment : BaseFragment(), HomeActivity.OnBackPressed {
    private lateinit var binding: FragmentAliasSearchBinding
    private lateinit var viewModel: AliasSearchViewModel
    private lateinit var adapter: AliasSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAliasSearchBinding.inflate(inflater)
        binding.backImageView.setOnClickListener {
            activity?.dismissKeyboard()
            findNavController().navigateUp()
        }

        setLoading(false)
        binding.messageTextView.visibility = View.GONE

        setUpSearchTextInputLayout()
        setUpViewModel()
        setUpRecyclerView()

        return binding.root
    }

    override fun onResume() {
        // Animate slide search bar
        super.onResume()
        val slideInFromLeftAnimator =
            AnimatorInflater.loadAnimator(context, R.animator.slide_in_from_left)
        slideInFromLeftAnimator.setTarget(binding.toolbarRootRelativeLayout)
        slideInFromLeftAnimator.start()

        // On configuration change
        if (viewModel.aliases.isEmpty()) {
            // Show keyboard
            binding.searchTextInputLayout.editText?.requestFocus()
            activity?.showKeyboard()
        } else {
            // Bind last search term back into editText
            binding.searchTextInputLayout.editText?.setText(viewModel.term)
            // Reload recyclerView
            viewModel.forceUpdateResults()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpSearchTextInputLayout() {
        binding.searchTextInputLayout.editText?.setOnKeyListener { _, keyCode, event ->
            // Must check event.action == KeyEvent.ACTION_DOWN
            // because a key is called 2 times: once for action down & once for action up
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                val term = binding.searchTextInputLayout.editText?.text.toString()
                if (term.count() < 2) {
                    context?.toastShortly("Minimum 2 characters required")
                } else {
                    binding.messageTextView.text = "No result for \"$term\""
                    setLoading(true)
                    activity?.dismissKeyboard()
                    viewModel.search(term)
                }
            }
            // False means the listener doesn't consume the event
            false
        }
    }

    private fun setUpViewModel() {
        val tempViewModel: AliasSearchViewModel by viewModels {
            context?.let {
                AliasSearchViewModelFactory(it)
            } ?: throw IllegalStateException("Context is null")
        }
        viewModel = tempViewModel

        viewModel.eventUpdateResults.observe(viewLifecycleOwner, Observer { updatedResults ->
            if (updatedResults) {
                activity?.runOnUiThread {
                    setLoading(false)

                    if (viewModel.aliases.isEmpty()) {
                        binding.messageTextView.visibility = View.VISIBLE
                    } else {
                        binding.messageTextView.visibility = View.GONE
                    }

                    adapter.submitList(viewModel.aliases)
                    viewModel.onHandleUpdateResultsComplete()
                }
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            if (error != null) {
                activity?.run {
                    toastError(error)
                    viewModel.onHandleErrorComplete()
                }
            }
        })
    }

    private fun setUpRecyclerView() {
        adapter = AliasSearchAdapter(object : AliasListAdapter.ClickListener {
            override fun onClick(alias: Alias) {
                findNavController().navigate(
                    AliasSearchFragmentDirections.actionAliasSearchFragmentToAliasActivityListFragment(
                        alias
                    )
                )
            }

            override fun onSwitch(alias: Alias, position: Int) {

            }

            override fun onCopy(alias: Alias) {
                val email = alias.email
                copyToClipboard(email, email)
                context?.toastShortly("Copied \"$email\"")
            }

            override fun onSendEmail(alias: Alias) {
                findNavController().navigate(
                    AliasSearchFragmentDirections.actionAliasSearchFragmentToContactListFragment(
                        alias
                    )
                )
            }

            override fun onDelete(alias: Alias, position: Int) {

            }
        })

        binding.recyclerView.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = linearLayoutManager

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                activity?.dismissKeyboard()
                if ((linearLayoutManager.findLastCompletelyVisibleItemPosition() == viewModel.aliases.size - 1) && viewModel.moreToLoad) {
                    viewModel.search()
                }
            }
        })
    }

    private fun setLoading(loading: Boolean) {
        if (loading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    // HomeActivity.OnBackPressed
    override fun onBackPressed() {
        activity?.dismissKeyboard()
        findNavController().navigateUp()
    }
}