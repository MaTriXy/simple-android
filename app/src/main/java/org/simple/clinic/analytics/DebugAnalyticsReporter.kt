package org.simple.clinic.analytics

class DebugAnalyticsReporter : AnalyticsReporter {

  override fun setUserIdentity(id: String) {
    // No-op.
  }

  override fun createEvent(event: String, props: Map<String, Any>) {
    // No-op.
  }

  override fun setProperty(key: String, value: Any) {
    // No-op.
  }
}
