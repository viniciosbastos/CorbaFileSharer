package client.interactors

interface IRemoteFilesInteractor {
    fun updateRemote()
    fun getFilesFromServer(search: String = ""): Array<String>
    fun downloadFile(fileName: String)
}