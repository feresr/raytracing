package com.feresr.raytracing

class Camera {

    private val horizontal = Vector(4.0f, 0.0f, 0.0f)
    private val lowerLeftCorner = Vector(-2.0f, -1.0f, -1.0f)
    private val vertical = Vector(0.0f, 2.0f, 0.0f)
    private val origin = Vector(0.0f, 0.0f, 0.0f)

    fun getRay(u: Float, v: Float): Ray {
        return Ray(
                origin,
                lowerLeftCorner + horizontal * u + vertical * v - origin
        )
    }
}