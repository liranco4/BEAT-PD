function UpdateItemsToDB(rowId)
{

 if($("#ActivitiesTab")[0].parentElement.classList[0] == "active")
 {

         var ActivityNameToUpdate =  rowId.parentNode.parentNode.cells[2].innerText;
  $("#txtUpdateName").val(ActivityNameToUpdate);


   // ddlSubMenu.append(rowId.parentNode.parentNode.cells[3].innerHTML);
  $("#ddlUpdateSubMenu").append(rowId.parentNode.parentNode.childNodes[3].children[0]);


        // $("#ddlMainGeneral option").each(function () {
         //$("#ddlUpdateSubMenu").push($(this).text());
         //});


    document.getElementById("ModalUpdateBody").style.display='block';
     UpdateModal.style.display = "block";

  // UpdateActivityToDB(rowId);
 }

  if($("#HabitsTab")[0].parentElement.classList[0] == "active")
  {
   //UpdateHabitToDB();
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

//update Activity
function UpdateActivityToDB(rowId)
{

              var RowToDelete = rowId;
              var acToUpdate= new Activity();
              acToUpdate.activityName = RowToDelete.parentNode.parentNode.cells[2].innerText;


         var SubMenuArr = [];
        $("#ddlSubMenu option").each(function () {
        SubMenuArr.push($(this).text());
        });

   var sm = [new subMenu()];
   for(var i = 0; i< SubMenuArr.length ;i++ )
  {
    sm[i] = new subMenu();
    sm[i].subMenu = SubMenuArr[i];
   }

             acToUpdate.subMenus = sm;

              var myJSON = JSON.stringify(acToUpdate);

      $.ajax({
                  url: "http://localhost:8080/BEAT-PD/Admin/Update/Activity",
                  cache: false,
                  type: "DELETE",
                  data: myJSON,
                  contentType: "application/json;charset=utf-8",
                  complete: function() {
            }
              });

              location.reload();
}