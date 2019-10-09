package server.db

data class Entry (val clientUsername: String,
                  val filesList: List<String>)