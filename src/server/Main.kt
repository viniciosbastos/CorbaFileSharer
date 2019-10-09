package server

import server.ui.MainStage
import javafx.application.Application
import org.omg.CORBA.ORB
import org.omg.CosNaming.NameComponent
import org.omg.CosNaming.NamingContextHelper
import org.omg.PortableServer.POAHelper

fun init() {
    val orb: ORB = ORB.init(arrayOf("-ORBInitialPort", "9999", "-ORBInitialHost", "localhost"), null)
    val rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"))
    val naming = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"))

    naming.rebind(arrayOf(NameComponent("FileSharer", "FileSharerServer")), rootPoa.servant_to_reference(
        FileSharerImpl()
    ))

    rootPoa.the_POAManager().activate()
}
fun main(args: Array<String>) {
    init()
    Application.launch(MainStage::class.java)
}
