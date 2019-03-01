package sample.test.utils

import android.os.Bundle
import android.support.v4.app.Fragment

interface FragmentTransactionManager {

    fun replaceFragment(fragment: Fragment,container:Int,addToStack:Boolean)

    fun replaceFragmentWithArguments(fragment: Fragment,container: Int,arguments:Bundle,addToStack: Boolean)

    fun addFragment(fragment: Fragment,container: Int)

    fun addFragmentWithArguments(fragment: Fragment,container: Int, arguments: Bundle)

    fun removeFragment(fragment: Fragment)

    fun clearBackStack()

    fun getLastFragment(id:Int): Fragment?
}