package com.feresr.raytracing


class Ray(
        val origin : Vector = Vector(),
        val direction : Vector = Vector()
) {
    fun pointAt(t : Float) : Vector {
        return origin + direction * t
    }
}