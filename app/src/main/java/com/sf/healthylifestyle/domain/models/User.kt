package com.sf.healthylifestyle.domain.models

data class User(
    var uuid: String,
    val name: String,
    var eMail: String,
    var avatar: String?
)