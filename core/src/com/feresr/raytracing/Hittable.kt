package com.feresr.raytracing

interface Hittable {

    data class HitRecord(
            val t: Float,
            val p: Vector,
            val normal: Vector,
            val material: Material)

    fun hit(ray: Ray, tmin: Float, tmax: Float): HitRecord?
}