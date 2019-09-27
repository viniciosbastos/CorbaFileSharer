package server

import org.omg.CORBA.ORB
import org.omg.CosNaming.NameComponent
import org.omg.CosNaming.NamingContextHelper
import org.omg.PortableServer.POAHelper
import util.ApplicationInitializer

class Server: ApplicationInitializer{

    override fun init() {
        val orb: ORB = ORB.init(arrayOf("-ORBInitialPort", "9999", "-ORBInitialHost", "localhost"), null)
        val rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"))
        val naming = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"))

        naming.rebind(arrayOf(NameComponent("Calculadora", "Exemplo")), rootPoa.servant_to_reference(CalculadoraImpl()))

        rootPoa.the_POAManager().activate()
        orb.run()
    }
}