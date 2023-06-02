package com.lift.up.mapper

import java.util.Objects

interface Mapper<T, F> {

    fun to(obj: T) : F

    fun from(obj: F) : T
}