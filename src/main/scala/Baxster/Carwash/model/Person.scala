package Baxster.Carwash.model

import scalafx.beans.property.{StringProperty, IntegerProperty, ObjectProperty}
import java.time.LocalDate;

class Person ( firstNameS : String, lastNameS : String, brandS: String, plateS : String, mobileS : String){
  var firstName  = new StringProperty(firstNameS)
  var lastName   = new StringProperty(lastNameS)
  var brand     = new StringProperty(brandS)
  var mobile = new StringProperty(mobileS)
  var plate       = new StringProperty(plateS)
  var date       = ObjectProperty[LocalDate](LocalDate.of(2023, 8, 28))
}


