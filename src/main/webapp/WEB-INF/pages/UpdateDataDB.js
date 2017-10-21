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
   var details = rowId.parentNode.parentNode.cells

   $("#txtUpdateMedicineSerial").val(details[2].innerText);
   $("#txtUpdateMedicineName").val(details[3].innerText);
   $("#txtUpdateMedicineLimitation").val(details[4].innerText);
   $("#txtUpdateMedicineInfo").val(details[5].innerText);
  //$("#txtMedicineName").prop("readonly", true);
      //document.getElementById("ModalBody").style.display='block';
     UpdateMedicineModal.style.display = "block"
    //UpdateMedicineToDB();
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
    //UpdateLinkToDB();
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
    //UpdateMedicineToDB();
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
    //UpdateLinkToDB();
 }

}

function PrepareUpdateModal(rowId){
        $("#txtUpdateName").prop("readonly", true);
        var ActivityNameToUpdate =  rowId.parentNode.parentNode.cells[2].innerText;
        $("#txtUpdateName").val(ActivityNameToUpdate);
        $("#ddlUpdateSubMenu").append( rowId.parentNode.parentNode.childNodes[3].children[0].options );
        document.getElementById("ModalUpdateBody").style.display='block';
        UpdateModal.style.display = "block";
}
//update Activity
function UpdateActivityToDB()
{


   var acToUpdate= new Activity();
   acToUpdate.activityName = $("#txtUpdateName").val();

   var SubMenuArr = [];
   $("#ddlUpdateSubMenu option").each(function () {
   SubMenuArr.push($(this).text());
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
        SubMenuArr.push($(this).text());
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
                complete: function(data) {
                if(data.status != 200 ) alert('Error in updating data to DB:' + data);
                }
        });
location.reload();

}