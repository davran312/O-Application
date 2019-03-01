package sample.test.utils

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

class FragmentTransaction(var fm:FragmentManager) : FragmentTransactionManager{


    override fun replaceFragment(fragment: Fragment, container: Int, addToStack: Boolean) {
        val transaction = fm.beginTransaction()
        if(addToStack) transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.replace(container,fragment)
        transaction.commitAllowingStateLoss()

    }

    override fun replaceFragmentWithArguments(fragment: Fragment, container: Int, arguments: Bundle, addToStack: Boolean) {
        fragment.arguments = arguments
        val transaction = fm.beginTransaction()
        if(addToStack) transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.replace(container,fragment)
        transaction.commitAllowingStateLoss()
    }

    override fun addFragment(fragment: Fragment, container: Int) {
        fm.beginTransaction().
                addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .add(container,fragment)
            .commit()
    }

    override fun addFragmentWithArguments(fragment: Fragment, container: Int, arguments: Bundle) {
        fragment.arguments = arguments
        addFragment(fragment,container)
    }

    override fun removeFragment(fragment: Fragment) {
        fm.popBackStackImmediate()
    }

    override fun clearBackStack() {
        fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun getLastFragment(id:Int): Fragment? = fm.findFragmentById(id)

}