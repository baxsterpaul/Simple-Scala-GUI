package Baxster.Carwash.view

import Baxster.Carwash.MainApp
import Baxster.Carwash.model.Person
import Baxster.Carwash.util.DateUtil
import scalafx.scene.control.{Alert, Label, TableColumn, TextField}
import scalafxml.core.macros.sfxml
import scalafx.stage.Stage
import scalafx.Includes._
import DateUtil._
import scalafx.event.ActionEvent
import scalafx.scene.control.TextInputControl




@sfxml
 class PersonEditDialogController(

                                   private val  firstNameField : TextField,
                                   private val   lastNameField : TextField,
                                   private val     brandField : TextField,
                                   private val mobileField : TextField,
                                   private val       plateField : TextField,
                                   private val   dateField : TextField

                                 )  {
  var         dialogStage : Stage  = null
  private var _person     : Person = null
  var         okClicked            = false

  def person = _person //geter
  def person_=(x : Person) { //setter
    _person = x

    firstNameField.text = _person.firstName.value
    lastNameField.text  = _person.lastName.value
    brandField.text    = _person.brand.value
    mobileField.text= _person.mobile.value.toString
    plateField.text      = _person.plate.value
    dateField.text = _person.date.value.asString

    dateField.setPromptText(DateUtil.DATE_PATTERN);
  }

def handleOk(action :ActionEvent){

    if (isInputValid()) {
      _person.firstName.value = firstNameField.text()
      _person.lastName.value  = lastNameField.text()
      _person.brand.value    = brandField.text()
      _person.plate.value      = plateField.text()
      _person.mobile.value = mobileField.getText()
      _person.date.value       = dateField.text.value.parseLocalDate;

      okClicked = true;
      dialogStage.close()
    }
  }



def handleCancel(action :ActionEvent) {
    dialogStage.close();
  }
  def nullChecking (x : String) = x == null || x.length == 0

  def isInputValid() : Boolean = {
    var errorMessage = ""

    if (nullChecking(firstNameField.text.value))
      errorMessage += "Invalid First Name!\n"
    if (nullChecking(lastNameField.text.value))
      errorMessage += "Invalid Last Name!\n"
    if (nullChecking(brandField.text.value))
      errorMessage += "Invalid brand!\n"
    if (nullChecking(mobileField.text.value))
      errorMessage += "Invalid mobile number!\n"
    else {
      try {

        Integer.parseInt(mobileField.getText());
      } catch {
        case e : NumberFormatException =>
          errorMessage += "Mobile number must be an Integer!\n"
      }
    }

    val platePattern = "[a-zA-Z0-9]+"
    if (nullChecking(plateField.text.value))
      errorMessage += "No valid plate!\n"
    else if (!plateField.text.value.matches(platePattern))
      errorMessage += "Plate must only contain letters and numbers!\n"
    if (nullChecking(dateField.text.value))
      errorMessage += "No valid date!\n"
    else {
      if (!dateField.text.value.isValid) {
        errorMessage += s"No valid date. Use the format ${DateUtil.DATE_PATTERN}!\n";
      }
    }

    if (errorMessage.length() == 0) {
      return true;
    } else {
      // Show the error message.
      val alert = new Alert(Alert.AlertType.Error){
        initOwner(dialogStage)
        title = "Invalid Fields"
        headerText = "Please correct invalid fields"
        contentText = errorMessage
      }.showAndWait()

      return false;
    }
  }
}