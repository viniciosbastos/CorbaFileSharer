package shared.FileSharerServer;


/**
* FileSharerServer/FileSharerOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from FileSharerIDL.idl
* Quarta-feira, 9 de Outubro de 2019 21h47min02s BRT
*/

public interface FileSharerOperations 
{
  void updateRemoteFiles (String clientUsername, String[] files);
  String[] getFilesFromRemote (String clientUsername);
  String getOwnerOfFile (String fileName);
} // interface FileSharerOperations
