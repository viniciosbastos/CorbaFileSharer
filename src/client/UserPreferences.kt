package client

class UserPreferences {
    var sharedFolder: String = ""
    var username: String = ""

    companion object {
        private var INSTANCE: UserPreferences? = null
        fun get(): UserPreferences {
            if (INSTANCE == null)
                INSTANCE = UserPreferences()
            return INSTANCE!!
        }
    }
}