package com.feresr.raytracing

import kotlin.math.sqrt


class Sphere(private val center: Vector, private val radius: Float, private val material: Material) : Hittable {
    override fun hit(ray: Ray, tmin: Float, tmax: Float): Hittable.HitRecord? {
        val oc: Vector = ray.origin - center
        val a = ray.direction * ray.direction
        val b = oc * ray.direction
        val c = (oc * oc) - (radius * radius)
        val discriminant = (b * b) - (a * c)

        if (discriminant <= 0) return null

        var temp = (-b - sqrt(discriminant)) / a
        var p = ray.pointAt(temp)
        if (temp > tmin && temp < tmax) {
            return Hittable.HitRecord(
                    temp,
                    p,
                    (p - center) / radius,
                    material
            )
        }

        temp = (-b + sqrt(discriminant)) / a
        p = ray.pointAt(temp)
        if (temp > tmin && temp < tmax) {
            return Hittable.HitRecord(
                    temp,
                    p,
                    (p - center) / radius,
                    material
            )
        }

        return null
    }
}