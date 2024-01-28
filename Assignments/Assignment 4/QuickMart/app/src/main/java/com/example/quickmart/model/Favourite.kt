package com.example.quickmart.model

import java.util.UUID

class Favourite(
    var productId: Int = 0,
    var id: String = UUID.randomUUID().toString()
)