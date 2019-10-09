package client

import org.omg.CORBA.ORB
import org.omg.CosNaming.NameComponent
import org.omg.CosNaming.NamingContextHelper
import corba.IBaseProtocol
import shared.FileSharerServer.FileSharerHelper

class Client: IBaseProtocol{
    override fun init() {
        val orb: ORB = ORB.init(arrayOf("-ORBInitialPort", "9999", "-ORBInitialHost", "localhost"), null)
        val naming = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"))

        val obj = naming.resolve(arrayOf(NameComponent("FileSharer", "FileSharerServer")))
        val calc = FileSharerHelper.narrow(obj)

//        println("5+3=${calc.soma(5F, 3F)}")
//        println("10/2=${calc.divisao(10F, 2F)}")
    }

}