function PrepareUpdateModals(rowId)
{

 if($("#ActivitiesTab")[0].parentElement.classList[0] == "active")
 {
    $("#lblUpdateHead").text("עדכן פעילויות");
    PrepareUpdateModal(rowId);
 }

  if($("#HabitsTab")[0].parentElement.classList[0] == "active")
  {
  $("#lblUpdateHead").text("עדכן ביום יום");
  $("#lblType").text(rowId.alt);
    PrepareUpdateModal(rowId);
  }

  if($("#MedicineTab")[0].parentElement.classList[0] == "active")
   {
   $("#lblUpdateHead").text("עדכן תרופות");
   var details = rowId.parentNode.parentNode.cells

   $("#txtUpdateMedicineSerial").val(details[2].innerText);
   $("#txtUpdateMedicineName").val(details[3].innerText);
   $("#txtUpdateMedicineLimitation").val(details[4].innerText);
   $("#txtUpdateMedicineInfo").val(details[5].innerText);
   $("#txtUpdateMedicineSerial").prop("readonly", true);
    UpdateMedicineModal.style.display = "block"
   }

   if($("#SleepTab")[0].parentElement.classList[0] == "active")
 {
   if($("#lblHead").text() == 'איכות שינה')
   {}
     // UpdateSleepQualityToDB();
    if($("#lblHead").text() == 'הפרעות שינה')
    {}
      //UpdateSleepDisorderToDB();

 }

   if($("#MoodTab")[0].parentElement.classList[0] == "active")
 {
     //UpdateMoodToDB();
 }

   if($("#LinksTab")[0].parentElement.classList[0] == "active")
 {
    $("#lblUpdateHead").text("עדכן לינקים");
    PrepareUpdateModal(rowId);
 }
  if($("#PatientTab")[0].parentElement.classList[0] == "active")
 {
 btnAddNewActivity1
  $("#btnAddNewActivity1").val('עדכן');
  var rowCells = rowId.parentNode.parentNode.cells;
   $("#txtIDPatient").val(rowCells[3].innerText);
   $("#txtPrivateNamePatient").val(rowCells[4].innerText);
   $("#txtLastPatient").val(rowCells[5].innerText);
   $("#txtAgePatient").val(rowCells[6].innerText);
   $("#txtStatusPatient").val(rowCells[7].innerText);

   $("#lblHead").text('מטופלים');
    document.getElementById("divForPatient").style.display='block';
    document.getElementById("ModalBody").style.display='none';
     document.getElementById("txtNameGeneral").style.display='none';
    document.getElementById("divForLinksUrl").style.display='none';
    modal.style.display = "block";
 }
}

function UpdateDataToDB(rowId)
{

 if($("#ActivitiesTab")[0].parentElement.classList[0] == "active")
 {
   UpdateActivityToDB();
 }

  if($("#HabitsTab")[0].parentElement.classList[0] == "active")
  {
   UpdateHabitToDB(rowId.alt);
  }

  if($("#MedicineTab")[0].parentElement.classList[0] == "active")
   {
    UpdateMedicineToDB();
   }

   if($("#SleepTab")[0].parentElement.classList[0] == "active")
 {
   if($("#lblHead").text() == 'איכות שינה')
   {}
     // UpdateSleepQualityToDB();
    if($("#lblHead").text() == 'הפרעות שינה')
    {}
      //UpdateSleepDisorderToDB();

 }

   if($("#MoodTab")[0].parentElement.classList[0] == "active")
 {
     //UpdateMoodToDB();
 }

   if($("#LinksTab")[0].parentElement.classList[0] == "active")
 {
   UpdateLinkToDB();
 }
   if($("#PatientTab")[0].parentElement.classList[0] == "active")
 {
   UpdatePatients();
 }

}

function PrepareUpdateModal(rowId){
        var ppRow = rowId.parentNode.parentNode;
        $("#txtUpdateName").prop("readonly", true);
        var ActivityNameToUpdate =  ppRow.cells[2].innerText;
        $("#txtUpdateName").val(ActivityNameToUpdate);

        if(rowId.alt == "EditLinks"){
           document.getElementById("divForLinksUrlUpdate").style.display='block';
           document.getElementById("ModalUpdateBody").style.display='none';
          $("#txtLinkUrlUpdate").val(ppRow.cells[3].innerText);
        }
        else{
            document.getElementById("divForLinksUrlUpdate").style.display='none';
            document.getElementById("ModalUpdateBody").style.display='block';
            $("#ddlUpdateSubMenu").append(ppRow.childNodes[3].children[0].options );
        }
        UpdateModal.style.display = "block";
}

//update Activity
function UpdateActivityToDB()
{
   var acToUpdate= new Activity();
   acToUpdate.activityName = $("#txtUpdateName").val();

   var SubMenuArr = [];
   $("#ddlUpdateSubMenu option").each(function () {
   if($(this).text() != ""){
      SubMenuArr.push($(this).text());
    }
   });

   var sm = [new subMenu()];
   for(var i = 0; i< SubMenuArr.length ;i++)
   {
    sm[i] = new subMenu();
    sm[i].subMenu = SubMenuArr[i];
   }

      acToUpdate.subMenus = sm;
      var myJSON = JSON.stringify(acToUpdate);
      $.ajax({
                  url: "http://localhost:8080/BEAT-PD/Admin/Update/Activity",
                  cache: false,
                  type: "PUT",
                  data: myJSON,
                  contentType: "application/json;charset=utf-8",
                  complete: function(data) {
                     if(data.status != 200 ) alert('Error in updating data to DB:' + data);
                   }
              });

              location.reload();
}

//update Habit
function UpdateHabitToDB()
{
    var HabitName =  $("#txtUpdateName").val();
    var ac = new Habit();
    ac.habitName = HabitName;
    if($("#lblType")[0].innerText == 'Edit1')
        {ac.groupID = 0;}
      else
        { ac.groupID = 1;}
    var SubMenuArr = [];
    $("#ddlUpdateSubMenu option").each(function () {
        if($(this).text() != ""){
              SubMenuArr.push($(this).text());
            }
    });

    var sm = [new subMenu()];
       for(var i = 0; i< SubMenuArr.length ;i++ )
      {
        sm[i] = new subMenu();
        sm[i].subMenu = SubMenuArr[i];
       }


    ac.subMenus = sm;
    var myJSON = JSON.stringify(ac);
    var mydata2 = JSON.parse(myJSON);

    $.ajax({
                url: "http://localhost:8080/BEAT-PD/Admin/Update/Habit",
                cache: false,
                type: "PUT",
                data: myJSON,
                contentType: "application/json;charset=utf-8",
                complete: function(response) {
                if(response.status != 200 ) alert('Error in updating data to DB:' + response);
                }
        });
location.reload();

}


//update Medicine
function UpdateMedicineToDB()
{

   var medicineObject = new Medicine();
   medicineObject.medicineName = $("#txtUpdateMedicineName").val();
   medicineObject.medicineSerialNumber = $("#txtUpdateMedicineSerial").val();
   medicineObject.medicineLimitation = $("#txtUpdateMedicineLimitation").val();
   medicineObject.info = $("#txtUpdateMedicineInfo").val();

var myJSON = JSON.stringify(medicineObject);

$.ajax({
            url: "http://localhost:8080/BEAT-PD/Admin/Update/Medicine",
            cache: false,
            type: "PUT",
            data: myJSON,
            contentType: "application/json;charset=utf-8",
            complete: function(response){
            if(response.status != 200 ) alert('Error in updating data to DB:' + response);
            }
                   });
           location.reload();

           }

//update Link
function UpdateLinkToDB()
{
   var linkToUpdate= new Link();
   linkToUpdate.linkHeadLine = $("#txtUpdateName").val();
   linkToUpdate.linkURL = $("#txtLinkUrlUpdate").val();
   var myJSON = JSON.stringify(linkToUpdate);
      $.ajax({
                  url: "http://localhost:8080/BEAT-PD/Admin/Update/Link",
                  cache: false,
                  type: "PUT",
                  data: myJSON,
                  contentType: "application/json;charset=utf-8",
                  complete: function(data) {
                     if(data.status != 200 ) alert('Error in updating data to DB:' + data);
                   }
              });

              location.reload();
}


function UpdatePatients()
{}
