package com.sw.wordgarden.presentation.ui.garden

import android.util.Log
import com.sw.wordgarden.R

object GetFlowerImg {
    fun getFlowerImg(num:Int, stage:Int) : Int {
        val flowerImg = arrayOf(
            arrayOf(R.drawable.tree_apple1_1_,R.drawable.tree_apple2_1_, R.drawable.tree_apple3_1_, R.drawable.tree_apple4_1_ ),
            arrayOf(R.drawable.tree_cherry_blossom1_2_, R.drawable.tree_cherry_blossom2_2_, R.drawable.tree_cherry_blossom3_2_, R.drawable.tree_cherry_blossom4_2_),
            arrayOf(R.drawable.tree_tulip1_3_,R.drawable.tree_tulip2_3_, R.drawable.tree_tulip3_3_, R.drawable.tree_tulip4_3_ ),
            arrayOf(R.drawable.tree_sun_flower1_4_,R.drawable.tree_sun_flower2_4_, R.drawable.tree_sun_flower3_4_, R.drawable.tree_sun_flower4_4_ ),
            arrayOf(R.drawable.tree_money_tree1_5_,R.drawable.tree_money_tree2_5_, R.drawable.tree_money_tree3_5_, R.drawable.tree_money_tree4_5_ ),
            arrayOf(R.drawable.tree_luminescence_seed1_6_,R.drawable.tree_luminescence_seed2_6_, R.drawable.tree_luminescence_seed3_6_, R.drawable.tree_luminescence_seed4_6_ ),
            arrayOf(R.drawable.tree_cactus1_7_,R.drawable.tree_cactus2_7_, R.drawable.tree_cactus3_7_, R.drawable.tree_cactus4_7_ ),
            arrayOf(R.drawable.tree_bamboo1_8_,R.drawable.tree_bamboo2_8_, R.drawable.tree_bamboo3_8_, R.drawable.tree_bamboo4_8_ ),
            arrayOf(R.drawable.tree_star_tree1_9_,R.drawable.tree_star_tree2_9_, R.drawable.tree_star_tree3_9_, R.drawable.tree_star_tree4_9_ ),
            arrayOf(R.drawable.tree_mushroom1_10_,R.drawable.tree_mushroom2_10_, R.drawable.tree_mushroom3_10_, R.drawable.tree_mushroom4_10_ ),
            arrayOf(R.drawable.tree_cloud_tree1_11_,R.drawable.tree_cloud_tree2_11_, R.drawable.tree_cloud_tree3_11_, R.drawable.tree_cloud_tree4_11_ ),
            arrayOf(R.drawable.tree_watermelon_tree1_12_,R.drawable.tree_watermelon_tree2_12_, R.drawable.tree_watermelon_tree3_12_, R.drawable.tree_watermelon_tree4_12_ )
        )
        return flowerImg[num-1][stage-1]
    }

    fun getFlowerImgList(num:Int, stage:Int) : List<Int> {
        val flowerImg = arrayOf(
            arrayOf(R.drawable.tree_apple1_1_,R.drawable.tree_apple2_1_, R.drawable.tree_apple3_1_, R.drawable.tree_apple4_1_ ),
            arrayOf(R.drawable.tree_cherry_blossom1_2_, R.drawable.tree_cherry_blossom2_2_, R.drawable.tree_cherry_blossom3_2_, R.drawable.tree_cherry_blossom4_2_),
            arrayOf(R.drawable.tree_tulip1_3_,R.drawable.tree_tulip2_3_, R.drawable.tree_tulip3_3_, R.drawable.tree_tulip4_3_ ),
            arrayOf(R.drawable.tree_sun_flower1_4_,R.drawable.tree_sun_flower2_4_, R.drawable.tree_sun_flower3_4_, R.drawable.tree_sun_flower4_4_ ),
            arrayOf(R.drawable.tree_money_tree1_5_,R.drawable.tree_money_tree2_5_, R.drawable.tree_money_tree3_5_, R.drawable.tree_money_tree4_5_ ),
            arrayOf(R.drawable.tree_luminescence_seed1_6_,R.drawable.tree_luminescence_seed2_6_, R.drawable.tree_luminescence_seed3_6_, R.drawable.tree_luminescence_seed4_6_ ),
            arrayOf(R.drawable.tree_cactus1_7_,R.drawable.tree_cactus2_7_, R.drawable.tree_cactus3_7_, R.drawable.tree_cactus4_7_ ),
            arrayOf(R.drawable.tree_bamboo1_8_,R.drawable.tree_bamboo2_8_, R.drawable.tree_bamboo3_8_, R.drawable.tree_bamboo4_8_ ),
            arrayOf(R.drawable.tree_star_tree1_9_,R.drawable.tree_star_tree2_9_, R.drawable.tree_star_tree3_9_, R.drawable.tree_star_tree4_9_ ),
            arrayOf(R.drawable.tree_mushroom1_10_,R.drawable.tree_mushroom2_10_, R.drawable.tree_mushroom3_10_, R.drawable.tree_mushroom4_10_ ),
            arrayOf(R.drawable.tree_cloud_tree1_11_,R.drawable.tree_cloud_tree2_11_, R.drawable.tree_cloud_tree3_11_, R.drawable.tree_cloud_tree4_11_ ),
            arrayOf(R.drawable.tree_watermelon_tree1_12_,R.drawable.tree_watermelon_tree2_12_, R.drawable.tree_watermelon_tree3_12_, R.drawable.tree_watermelon_tree4_12_ )
        )

        return flowerImg[num-1].slice(0..<stage)
    }
    fun getFlowerName(position:Int):Int {
        val nameList = listOf(
            R.string.apple,
            R.string.cherry_blossom,
            R.string.tulip,
            R.string.sun_flower,
            R.string.money_tree,
            R.string.luminescence_seed,
            R.string.cactus,
            R.string.bamboo,
            R.string.star_tree,
            R.string.mushroom,
            R.string.cloud_tree,
            R.string.watermelon_tree
        )
        return nameList[position-1]
    }

}