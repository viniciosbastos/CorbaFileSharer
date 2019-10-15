package client

import client.services.BaseCorbaCfg
import client.ui.MainStage
import javafx.application.Application

fun main(args: Array<String>) {
    BaseCorbaCfg.create(args)
    Application.launch(MainStage::class.java)
}
