package client.interactors

interface IRemoteFilesInteractor {
    fun updateRemote()
    fun updateLocal(): Array<String>
    fun downloadFile(fileName: String)
}