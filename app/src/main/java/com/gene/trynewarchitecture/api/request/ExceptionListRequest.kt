package com.gene.trynewarchitecture.api.request

data class ExceptionListRequest (
        /**
         * type: "DR" 代表正物流，"RR"代表逆物流
         */
        val type: String
)