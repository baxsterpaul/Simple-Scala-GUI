package Baxster.Carwash.view

import Baxster.Carwash.MainApp
import scalafx.scene.control.TextField
import scalafxml.core.macros.sfxml

@sfxml
class WelcomeController() {

  def getStarted(): Unit = {
    MainApp.showPersonOverview()
  }
}
