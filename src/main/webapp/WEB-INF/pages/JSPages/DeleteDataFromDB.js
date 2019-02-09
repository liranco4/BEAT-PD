
//////////////////////////////////////*************DELETE Item From Data-Base************///////////////////////////////
var BaseURL = "http://localhost:8080/BEAT-PD/Admin/Delete";

function DeleteSleepDisorderItem(rowId)
{
   if (confirm("Are you sure you want to delete?") == true) {
        // OK

        var RowToDelete = rowId;
        var sdToDelete = new SleepDisorder();
        sdToDelete.sleepDisorderName = RowToDelete.parentNode.parentNode.cells[2].innerText;
        var myJSON = JSON.stringify(sdToDelete);

$.ajax({
            url: BaseURL+"/SleepDisorder",
            cache: false,
            type: "DELETE",
            data: myJSON,
            contentType: "application/json;charset=utf-8",
            complete: function() {
      }
        });

        location.reload();


    } else {
        //Cancel
        //DoNothing
    }


}

function DeleteSleepQualityItem(rowId)
{
   if (confirm("Are you sure you want to delete?") == true) {
        // OK

        var RowToDelete = rowId;
        var sqToDelete = new SleepQuality();
        sqToDelete.sleepQualityName = RowToDelete.parentNode.parentNode.cells[2].innerText;
        var myJSON = JSON.stringify(sqToDelete);

$.ajax({
            url: BaseURL+"/SleepQuality",
            cache: false,
            type: "DELETE",
            data: myJSON,
            contentType: "application/json;charset=utf-8",
            complete: function() {
      }
        });

        location.reload();


    } else {
        //Cancel
        //DoNothing
    }


}


//Delete General
function DeleteItem(rowId)
{

 if($("#ActivitiesTab")[0].parentElement.classList[0] == "active")
 {
   var txt;
    if (confirm("Are you sure you want to delete?") == true) {
        // OK

        var RowToDelete = rowId;
        var acToDelete = new Activity();
        acToDelete.activityName = RowToDelete.parentNode.parentNode.cells[2].innerText;
        var myJSON = JSON.stringify(acToDelete);

$.ajax({
            url: BaseURL+"/Activity",
            cache: false,
            type: "DELETE",
            data: myJSON,
            contentType: "application/json;charset=utf-8",
            complete: function() {
      }
        });

        location.reload();


    } else {
        //Cancel
        //DoNothing
    }

 }
  if($("#HabitsTab")[0].parentElement.classList[0] == "active")
 {

   var txt;
    if (confirm("Are you sure you want to delete?") == true) {
        // OK

        var RowToDelete = rowId;
        var haToDelete = new Habit();
        haToDelete.habitName = RowToDelete.parentNode.parentNode.cells[2].innerText;
        var myJSON = JSON.stringify(haToDelete);

$.ajax({
            url: BaseURL+"/Habit",
            cache: false,
            type: "DELETE",
            data: myJSON,
            contentType: "application/json;charset=utf-8",
            complete: function() {
      }
        });

        location.reload();


    } else {
        //Cancel
        //DoNothing
    }

 }

  if($("#MedicineTab")[0].parentElement.classList[0] == "active")
 {

   var txt;
    if (confirm("Are you sure you want to delete?") == true) {
        // OK

        var RowToDelete = rowId;
        var medicineToDelete = new Medicine();
        medicineToDelete.medicineSerialNumber = RowToDelete.parentNode.parentNode.cells[2].innerText;
        var myJSON = JSON.stringify(medicineToDelete);

$.ajax({
            url: " BaseURL"+"/Medicine",
            cache: false,
            type: "DELETE",
            data: myJSON,
            contentType: "application/json;charset=utf-8",
            complete: function(data) {
           if(data.status != 200 ) alert('Error in getting data from DB:' + data);
      }
        });




    } else {
        //Cancel
        //DoNothing
    }
 location.reload();
 }

   if($("#MoodTab")[0].parentElement.classList[0] == "active")
 {
   $("#lblHead").text('מצב-רוח');
   var txt;
    if (confirm("Are you sure you want to delete?") == true) {
        // OK

        var RowToDelete = rowId;
        var mcToDelete = new MoodCondition();
        mcToDelete.moodConditionName = RowToDelete.parentNode.parentNode.cells[1].innerText;
        var myJSON = JSON.stringify(mcToDelete);

$.ajax({
            url: BaseURL+"/MoodCondition",
            cache: false,
            type: "DELETE",
            data: myJSON,
            contentType: "application/json;charset=utf-8",
            complete: function(data) {
                if(data.status != 200 ) alert('Error in getting data from DB:' + data);
            }
        });

        location.reload();


    } else {
        //Cancel
        //DoNothing
    }

 }

   if($("#LinksTab")[0].parentElement.classList[0] == "active")
 {
   $("#lblHead").text('לינקים');
   var txt;
    if (confirm("Are you sure you want to delete?") == true) {
        // OK

        var RowToDelete = rowId;
        var lnkToDelete = new Link();
        lnkToDelete.linkHeadLine = RowToDelete.parentNode.parentNode.cells[2].innerText;
        var myJSON = JSON.stringify(lnkToDelete);

$.ajax({
            url: BaseURL+"/Link",
            cache: false,
            type: "DELETE",
            data: myJSON,
            contentType: "application/json;charset=utf-8",
            complete: function() {
      }
        });

        location.reload();


    } else {
        //Cancel
        //DoNothing
    }

 }

    if($("#PatientTab")[0].parentElement.classList[0] == "active")
  {
    $("#lblHead").text('מטופלים');
    var txt;
     if (confirm("Are you sure you want to delete?") == true) {
         // OK

         var RowToDelete = rowId;
         var patientToDelete = new Patient();
         patientToDelete.patientID = RowToDelete.parentNode.parentNode.cells[3].innerText;
           patientToDelete.patientFirstName = RowToDelete.parentNode.parentNode.cells[4].innerText;
             patientToDelete.patientLastName = RowToDelete.parentNode.parentNode.cells[5].innerText;
                   patientToDelete.patientAge = RowToDelete.parentNode.parentNode.cells[6].innerText;
                     patientToDelete.patientStatus = RowToDelete.parentNode.parentNode.cells[7].innerText;
         var myJSON = JSON.stringify(patientToDelete);

 $.ajax({
             url: BaseURL+"/Patient",
             cache: false,
             type: "DELETE",
             data: myJSON,
             contentType: "application/json;charset=utf-8",
             complete: function() {
       }
         });

         location.reload();


     } else {
         //Cancel
         //DoNothing
     }

  }


  if($("#AdminUsersTab")[0].parentElement.classList[0] == "active")
  {
    $("#lblHead").text('מנהל מערכת');
    var txt;
     if (confirm("Are you sure you want to delete?") == true) {
         // OK

         var RowToDelete = rowId;
         var userToDelete = new User();
         userToDelete.id = RowToDelete.parentNode.parentNode.cells[2].innerText;
           userToDelete.name = RowToDelete.parentNode.parentNode.cells[3].innerText;
             userToDelete.lastLogin = RowToDelete.parentNode.parentNode.cells[4].innerText;
                   userToDelete.role = "Admin";

         var myJSON = JSON.stringify(userToDelete);

 $.ajax({
             url: BaseURL+"/User",
             cache: false,
             type: "DELETE",
             data: myJSON,
             contentType: "application/json;charset=utf-8",
             complete: function() {
       }
         });

         location.reload();


     } else {
         //Cancel
         //DoNothing
     }

  }

}
///////////////////////////////*************************************//////////////////////////////////////////////////