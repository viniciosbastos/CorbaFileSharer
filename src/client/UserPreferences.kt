package client

class UserPreferences {
    var sharedFolder: String = ""
    var username: String = ""

    fun isValid(): Boolean {
        return (sharedFolder != "" && username != "")
    }

    companion object {
        private var INSTANCE: UserPreferences? = null
        fun get(): UserPreferences {
            if (INSTANCE == null)
                INSTANCE = UserPreferences()
            return INSTANCE!!
        }
    }
}