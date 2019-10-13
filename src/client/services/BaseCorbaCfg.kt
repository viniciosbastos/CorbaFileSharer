package client.services

import org.omg.CORBA.ORB
import org.omg.CosNaming.NamingContext
import org.omg.CosNaming.NamingContextHelper
import org.omg.PortableServer.POA
import org.omg.PortableServer.POAHelper

object BaseCorbaCfg {
    val orb: ORB
    val rootPoa: POA
    val naming: NamingContext

    init {
        orb = ORB.init(arrayOf("-ORBInitialPort", "9999", "-ORBInitialHost", "localhost"), null)
        rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"))
        naming = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"))
    }
}