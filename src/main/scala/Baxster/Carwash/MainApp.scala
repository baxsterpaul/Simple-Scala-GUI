package Baxster.Carwash

import Baxster.Carwash.model.Person
import Baxster.Carwash.view.PersonEditDialogController
import MainApp.stage
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes.{getClass, _}
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafx.collections.ObservableBuffer
import scalafx.stage.{Modality, Stage}

object MainApp extends JFXApp {

  val personData = new ObservableBuffer[Person]()

  /**
   * Constructor
   */
  personData += new Person("Ahmad", "Ali", "Proton", "ABC1234", "0123456789")
  personData += new Person("Siti", "Zainab", "Perodua", "XYZ5678", "0198765432")
  personData += new Person("Mohd", "Razak", "Toyota", "DEF2345", "0167890123")
  personData += new Person("Aisyah", "Ismail", "Honda", "GHI3456", "0145678901")
  personData += new Person("Ravi", "Krishnan", "Nissan", "JKL4567", "0178901234")
  personData += new Person("Nurul", "Abdullah", "Proton", "MNO5678", "0189012345")
  personData += new Person("Hassan", "Ibrahim", "Perodua", "PQR6789", "0112345678")
  personData += new Person("Farah", "Hussein", "Toyota", "STU7890", "0134567890")
  personData += new Person("Ahmed", "Ismail", "Honda", "VWX8901", "0156789012")
  personData += new Person("Zara", "Abdul", "Nissan", "YZA9012", "0178901234")
  personData += new Person("Muthu", "Raj", "Proton", "BCD0123", "0145678901")
  personData += new Person("Saraswathi", "Kumar", "Perodua", "EFG1234", "0190123456")
  personData += new Person("Rahman", "Chong", "Toyota", "HIJ2345", "0167890123")
  personData += new Person("Leela", "Liew", "Honda", "KLM3456", "0134567890")
  personData += new Person("Zulkifli", "Lim", "Nissan", "NOP4567", "0156789012")
  personData += new Person("Rohani", "Tan", "Proton", "QRS5678", "0189012345")
  personData += new Person("Faisal", "Wong", "Perodua", "TUV6789", "0123456789")
  personData += new Person("Anis", "Lee", "Toyota", "WXY7890", "0145678901")
  personData += new Person("Arif", "Ng", "Honda", "ZAB9012", "0167890123")
  personData += new Person("Zaini", "Tan", "Nissan", "CDE0123", "0189012345")
  personData += new Person("Siti", "Kaur", "Proton", "FGH1234", "0123456789")
  personData += new Person("Hakim", "Singh", "Perodua", "IJK2345", "0145678901")
  personData += new Person("Nadia", "Raj", "Toyota", "LMN3456", "0167890123")
  personData += new Person("Amir", "Krishnan", "Honda", "OPQ4567", "0189012345")
  personData += new Person("Maya", "Lim", "Nissan", "RST5678", "0123456789")
  personData += new Person("Rizwan", "Tan", "Proton", "UVW6789", "0145678901")
  personData += new Person("Sara", "Chin", "Perodua", "XYZ7890", "0167890123")
  personData += new Person("Amin", "Wong", "Toyota", "ABC9012", "0189012345")
  personData += new Person("Zahara", "Ng", "Honda", "DEF0123", "0123456789")
  personData += new Person("Faisal", "Tan", "Nissan", "GHI1234", "0145678901")
  personData += new Person("Zaini", "Singh", "Proton", "JKL2345", "0167890123")
  personData += new Person("Nadia", "Kaur", "Perodua", "MNO3456", "0189012345")
  personData += new Person("Ahmed", "Raj", "Toyota", "PQR4567", "0123456789")
  personData += new Person("Siti", "Krishnan", "Honda", "STU5678", "0145678901")
  personData += new Person("Mohd", "Lim", "Nissan", "VWX6789", "0167890123")
  // transform path of RootLayout.fxml to URI for resource location.
  val rootResource = getClass.getResource("view/RootLayout.fxml")
  // initialize the loader object.
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  // Load root layout from fxml file.
  loader.load();
  // retrieve the root component BorderPane from the FXML
  val roots = loader.getRoot[jfxs.layout.BorderPane]
  // initialize stage
  stage = new PrimaryStage {
    title = "Car Wash"
    scene = new Scene {
      root = roots
    }
  }
  // actions for display person overview window
  def showPersonOverview() = {
    val resource = getClass.getResource("view/PersonOverview.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showWelcome() = {
    val resource = getClass.getResource("view/Welcome.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }
  // call to display PersonOverview when app start


  def showPersonEditDialog(person: Person): Boolean = {
  /** person: Person, person is parse that needed to show, once recieved > boolean.
   * Boolean as user either shows or cancel.*/
  {
    val resource = getClass.getResourceAsStream("view/PersonEditDialog.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots2 = loader.getRoot[jfxs.Parent]

    val control = loader.getController[PersonEditDialogController#Controller] // # is the selector. based on macro paradise plugin.

    val dialog = new Stage() {// custom window, new stage
      initModality(Modality.APPLICATION_MODAL) // modality refer API, companion object.
      initOwner(stage) // (owner) primary window.
      scene = new Scene {
        root = roots2
      }
    }
    /** to initialise controller, window object needed.  */

    control.dialogStage = dialog // set to window created
    control.person = person //person thats shown
    dialog.showAndWait() // once closed return last value. okClicked
    control.okClicked
  }
  }

  showWelcome()
}

