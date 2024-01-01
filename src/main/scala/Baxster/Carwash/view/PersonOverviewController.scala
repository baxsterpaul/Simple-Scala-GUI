package Baxster.Carwash.view

import Baxster.Carwash.MainApp
import Baxster.Carwash.MainApp.stage
import Baxster.Carwash.model.Person
import Baxster.Carwash.util.DateUtil._
import scalafx.scene.control.{Alert, Label, TableColumn, TableView, TextField}
import scalafxml.core.macros.sfxml
import scalafx.beans.property.StringProperty
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.Alert.AlertType


@sfxml
 class PersonOverviewController(

 private val personTable : TableView[Person],
 private val firstNameColumn : TableColumn[Person, String],
 private val lastNameColumn : TableColumn[Person, String],
 private val firstNameLabel : Label,
 private val lastNameLabel : Label,
 private val brandLabel : Label,
 private val mobileLabel : Label,
 private val plateLabel :  Label,
 private val  dateLabel : Label,
 private val searchTextField: TextField,


                              )  {
  // initialize Table View display contents model
  personTable.items = MainApp.personData
  // initialize columns's cell values
  firstNameColumn.cellValueFactory = {
    _.value.firstName
  }
  lastNameColumn.cellValueFactory = {
    _.value.plate
  }


  private def showPersonDetails(person: Option[Person]) = {
    person match {
      case Some(person) =>
        // Fill the labels with info from the person object.
        firstNameLabel.text <== person.firstName
        lastNameLabel.text <== person.lastName
        brandLabel.text <== person.brand
        plateLabel.text <== person.plate;
        mobileLabel.text = person.mobile.value.toString
        dateLabel.text = person.date.value.asString
      case None =>
        // Person is null, remove all the text.
        firstNameLabel.text.unbind()
        lastNameLabel.text.unbind()
        brandLabel.text.unbind()
        plateLabel.text.unbind()
        firstNameLabel.text = ""
        lastNameLabel.text = ""
        brandLabel.text = ""
        mobileLabel.text = ""
        plateLabel.text = ""
        dateLabel.text = ""
    }
  }

  showPersonDetails(None)

  personTable.selectionModel.value.selectedItem.onChange(
    (x, y, z) => {
      showPersonDetails(Option(z))
    }
  )




 def handleDeletePerson(action: ActionEvent) = {
    val selectedIndex = personTable.selectionModel().selectedIndex.value
    if (selectedIndex >= 0) {
      personTable.items().remove(selectedIndex);
    } else {
      // Nothing selected.
      val alert = new Alert(AlertType.Warning) {
        initOwner(MainApp.stage)
        title = "Selection Error"
        headerText = "No person Selected"
        contentText = "A person must be selected."
      }.showAndWait()
    }
  }


 def handleNewPerson(action: ActionEvent) = {
    val person = new Person("", "", "", "", "" )
    val okClicked = MainApp.showPersonEditDialog(person);
    if (okClicked) {
      MainApp.personData += person
    }
  }

 def handleEditPerson(action: ActionEvent) = {
    val selectedPerson = personTable.selectionModel().selectedItem.value
    if (selectedPerson != null) {
      val okClicked = MainApp.showPersonEditDialog(selectedPerson)

      if (okClicked) showPersonDetails(Some(selectedPerson))

    } else {
      // Nothing selected.
      val alert = new Alert(Alert.AlertType.Warning) {
        initOwner(MainApp.stage)
        title = "Selection Error"
        headerText = "No person pelected"
        contentText = "A person must be selected."
      }.showAndWait()
    }
  }

def handleSearch(action: ActionEvent): Unit = {
    val searchText = searchTextField.text.value.toLowerCase()

    val filteredPersons = MainApp.personData.filter { person =>
      person.firstName.value.toLowerCase.contains(searchText) ||
        person.lastName.value.toLowerCase.contains(searchText) ||
        person.brand.value.toLowerCase.contains(searchText) ||
        person.plate.value.toLowerCase.contains(searchText) ||
        person.mobile.value.toLowerCase.contains(searchText)
    }

    personTable.items = filteredPersons
  }

 def resetSearch(): Unit = {
    searchTextField.clear()
    personTable.items = MainApp.personData
  }

  def handleClose(): Unit = {
    MainApp.stage.close()
  }
}

