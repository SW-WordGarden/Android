package com.sw.wordgarden.presentation.ui.garden

import android.graphics.drawable.Drawable
import com.sw.wordgarden.R

object GetFlowerImg {
    fun getFlowerImg(stage:Int, value:Int) : Int {
        return when(stage){
            1 -> {
                when(value){
                    0 -> R.drawable.tree_apple1_1_
                    1 -> R.drawable.tree_apple2_1_
                    2 -> R.drawable.tree_apple3_1_
                    else -> R.drawable.tree_apple4_1_
                }
            }
            2 -> {
                when(value){
                    0 -> R.drawable.tree_cherry_blossom1_2_
                    1 -> R.drawable.tree_cherry_blossom2_2_
                    2 -> R.drawable.tree_cherry_blossom3_2_
                    else -> R.drawable.tree_cherry_blossom4_2_
                }
            }
            3 -> {
                when(value){
                    0 -> R.drawable.tree_tulip1_3_
                    1 -> R.drawable.tree_tulip2_3_
                    2 -> R.drawable.tree_tulip3_3_
                    else -> R.drawable.tree_tulip4_3_
                }
            }
            4 -> {
                when(value){
                    0 -> R.drawable.tree_sun_flower1_4_
                    1 -> R.drawable.tree_sun_flower2_4_
                    2 -> R.drawable.tree_sun_flower3_4_
                    else -> R.drawable.tree_sun_flower4_4_
                }
            }
            5 -> {
                when(value){
                    0 -> R.drawable.tree_money_tree1_5_
                    1 -> R.drawable.tree_money_tree2_5_
                    2 -> R.drawable.tree_money_tree3_5_
                    else -> R.drawable.tree_money_tree4_5_
                }
            }
            6 -> {
                when(value){
                    0 -> R.drawable.tree_luminescence_seed1_6_
                    1 -> R.drawable.tree_luminescence_seed2_6_
                    2 -> R.drawable.tree_luminescence_seed3_6_
                    else -> R.drawable.tree_luminescence_seed4_6_
                }
            }
            7 -> {
                when(value){
                    0 -> R.drawable.tree_cactus1_7_
                    1 -> R.drawable.tree_cactus2_7_
                    2 -> R.drawable.tree_cactus3_7_
                    else -> R.drawable.tree_cactus4_7_
                }
            }
            8 -> {
                when(value){
                    0 -> R.drawable.tree_bamboo1_8_
                    1 -> R.drawable.tree_bamboo2_8_
                    2 -> R.drawable.tree_bamboo3_8_
                    else -> R.drawable.tree_bamboo4_8_
                }
            }
            9 -> {
                when(value){
                    0 -> R.drawable.tree_star_tree1_9_
                    1 -> R.drawable.tree_star_tree2_9_
                    2 -> R.drawable.tree_star_tree3_9_
                    else -> R.drawable.tree_star_tree4_9_
                }
            }
            10 -> {
                when(value){
                    0 -> R.drawable.tree_mushroom1_10_
                    1 -> R.drawable.tree_mushroom2_10_
                    2 -> R.drawable.tree_mushroom3_10_
                    else -> R.drawable.tree_mushroom4_10_
                }
            }
            11 -> {
                when(value){
                    0 -> R.drawable.tree_cloud_tree1_11_
                    1 -> R.drawable.tree_cloud_tree2_11_
                    2 -> R.drawable.tree_cloud_tree3_11_
                    else -> R.drawable.tree_cloud_tree4_11_
                }
            }
            else -> {
                when(value){
                    0 -> R.drawable.tree_watermelon_tree1_12_
                    1 -> R.drawable.tree_watermelon_tree2_12_
                    2 -> R.drawable.tree_watermelon_tree3_12_
                    else -> R.drawable.tree_watermelon_tree4_12_
                }
            }
        }
    }
}