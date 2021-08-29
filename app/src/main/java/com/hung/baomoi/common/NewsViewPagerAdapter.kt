package com.hung.baomoi.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hung.baomoi.news.breakingnews.BreakingNewsFragment
import com.hung.baomoi.news.breakingnews.covid.CovidNewsFragment

class NewsViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 10

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BreakingNewsFragment()

            1 -> CovidNewsFragment()

//            2 -> SportsNewsFragment()
//            3 -> LawNewsFragment()
//
//            4 -> ShowbizNewsFragment()
//
//            5 -> CarNewsFragment()
//            6 -> TechNewsFragment()
//
//            7 -> FoodNewsFragment()

            else -> BreakingNewsFragment()
        }
    }
}
