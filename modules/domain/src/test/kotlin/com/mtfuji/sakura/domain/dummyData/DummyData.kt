package com.mtfuji.sakura.domain.dummyData

import com.mtfuji.sakura.domain.models.ProductModel

const val CATEGORY_FRUIT = "Fruit"
const val CATEGORY_VEGETABLE = "Vegetable"
const val CATEGORY_CRISP = "Crisp"
const val CATEGORY_SANDWICH = "Sandwich"

val appleProductModel = ProductModel(
    id = "12345",
    name = "Apple",
    price = 0.75,
    description = "Pink Lady",
    imageUrl = "misc",
    category = CATEGORY_FRUIT
)

val bananaProductModel = ProductModel(
    id = "23451",
    name = "Banana",
    price = 1.25,
    description = "Pack of 5, Organic",
    imageUrl = "misc",
    category = CATEGORY_FRUIT
)

val carrotProductModel = ProductModel(
    id = "34512",
    name = "Carrot",
    price = 0.25,
    description = "single, Organic",
    imageUrl = "misc",
    category = CATEGORY_VEGETABLE
)

val seaSaltStrollerProductModel = ProductModel(
    id = "45123",
    name = "Sea Salt Stroller",
    price = 1.0,
    description = "Artificial Goodness",
    imageUrl = "misc",
    category = CATEGORY_CRISP
)

val baconLettuceTomatoProductModel = ProductModel(
    id = "51234",
    name = "Bacon Lettuce Tomato Sandwich",
    price = 2.5,
    description = "Tis a Sandwich.  Yum",
    imageUrl = "misc",
    category = CATEGORY_SANDWICH
)