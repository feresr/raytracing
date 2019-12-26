package com.feresr.raytracing

class HittableList : ArrayList<Hittable>(), Hittable {

    override fun hit(ray: Ray, tmin: Float, tmax: Float): Hittable.HitRecord? {
        var closestSoFar = tmax
        var temprec: Hittable.HitRecord? = null
        this.forEach {
            val record = it.hit(ray, tmin, closestSoFar)
            if (record != null) {
                closestSoFar = record.t
                temprec = record
            }
        }

        return temprec
    }
}