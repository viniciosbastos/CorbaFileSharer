package shared.FileSharerServer;


/**
* FileSharerServer/FileSharerOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from FileSharerIDL.idl
* Quarta-feira, 9 de Outubro de 2019 16h49min44s BRT
*/

public interface FileSharerOperations 
{
  void updateRemoteFiles (String[] files);
  String[] getFilesFromRemote ();
} // interface FileSharerOperations
