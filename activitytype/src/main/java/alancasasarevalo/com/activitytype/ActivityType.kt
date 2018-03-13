package alancasasarevalo.com.activitytype

import java.io.Serializable


enum class ActivityType(val type: String): Serializable {
    SHOP("shop"),
    ACTIVITY("activity")
}