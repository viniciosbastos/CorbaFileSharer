package shared.FileSharer;


/**
* FileSharer/_ServerStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from FileSharerIDL.idl
* Domingo, 13 de Outubro de 2019 21h52min07s BRT
*/

public class _ServerStub extends org.omg.CORBA.portable.ObjectImpl implements Server
{

  public void updateRemoteFiles (String clientUsername, String[] files)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("updateRemoteFiles", false);
                $out.write_string (clientUsername);
                StringArrayHelper.write ($out, files);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                updateRemoteFiles (clientUsername, files        );
            } finally {
                _releaseReply ($in);
            }
  } // updateRemoteFiles

  public String[] getFilesFromRemote (String clientUsername, String search)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getFilesFromRemote", true);
                $out.write_string (clientUsername);
                $out.write_string (search);
                $in = _invoke ($out);
                String $result[] = StringArrayHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getFilesFromRemote (clientUsername, search        );
            } finally {
                _releaseReply ($in);
            }
  } // getFilesFromRemote

  public String getOwnerOfFile (String fileName)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getOwnerOfFile", true);
                $out.write_string (fileName);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getOwnerOfFile (fileName        );
            } finally {
                _releaseReply ($in);
            }
  } // getOwnerOfFile

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:FileSharer/Server:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _ServerStub
