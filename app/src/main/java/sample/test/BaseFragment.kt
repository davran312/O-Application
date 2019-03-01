package sample.test

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_album.*
import sample.test.di.modules.ViewModelFactory
import sample.test.utils.FragmentTransaction
import sample.test.utils.FragmentTransactionManager
import javax.inject.Inject

abstract  class BaseFragment : DaggerFragment(){

    @Inject
    lateinit var mViewModelFactory:ViewModelFactory
    lateinit var mProgressObserver: Observer<ProgressStatus>
    lateinit var mMessageObserver: Observer<String>
    lateinit var mTransactionManager: FragmentTransactionManager
    lateinit var mMainViewModel:MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setupTransactionManager()
        initObservers()
        initViewModel()
        return inflater.inflate(getLayoutResId(),null)
    }

    private fun initViewModel() {
        mMainViewModel = mViewModelFactory.create(MainViewModel::class.java)
        mMainViewModel.getMessages().observe(this,mMessageObserver)
        mMainViewModel.getProgressStatus().observe(this,mProgressObserver)
    }

    private fun initObservers() {
        mProgressObserver = Observer {
            showProgress(it)
        }
        mMessageObserver = Observer {
            toast(it!!)
        }

    }
    private fun showProgress(it: ProgressStatus?) {

        swipeRefresh.isRefreshing = when(it){
            ProgressStatus.SUCCESS -> false
            ProgressStatus.LOADING -> true
            else ->false

        }
    }

    protected fun toast(it: String) {
        Snackbar.make(rootView,it, Snackbar.LENGTH_SHORT).show()
    }


    abstract fun getLayoutResId(): Int

    private fun setupTransactionManager() {
        mTransactionManager = FragmentTransaction(activity!!.supportFragmentManager)
    }
    fun getTransactionManager():FragmentTransactionManager = mTransactionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}