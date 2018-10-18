package org.simple.clinic.util

data class BoundedInt(val lower: Int, val upper: Int) {
  init {
    if (upper < lower) {
      throw AssertionError("Upper bound [$upper] should be >= lower bound [$lower]")
    }
  }
}
