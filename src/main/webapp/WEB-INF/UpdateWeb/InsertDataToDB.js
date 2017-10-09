
//////////////////////////////////***********Insert New Item To Data-Base***********////////////////////////////////////////

function AddNewItemsToDB()
{

 if($("#ActivitiesTab")[0].parentElement.classList[0] == "active")
 {
   AddNewActivityToDB();
 }

  if($("#HabitsTab")[0].parentElement.classList[0] == "active")
  {
   AddNewHabitToDB();
  }

  if($("#MedicineTab")[0].parentElement.classList[0] == "active")
   {
    AddNewMedicineToDB();
   }

   if($("#SleepTab")[0].parentElement.classList[0] == "active")
 {
   if($("#lblHead").text() == 'איכות שינה')
      InsertNewSleepQualityToDB();
    if($("#lblHead").text() == 'הפרעות שינה')
      InsertNewSleepDisorderToDB();

 }

   if($("#MoodTab")[0].parentElement.classList[0] == "active")
 {
     InsertMoodToDB();
 }

   if($("#LinksTab")[0].parentElement.classList[0] == "active")
 {
    insertNewLinkToDB();
 }




}

//insert NewActivity
function AddNewActivityToDB()
{

var ActivityName =  $("#txtName").val();
var ac = new Activity();
ac.activityName = ActivityName;

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

ac.subMenus = sm;
var myJSON = JSON.stringify(ac);

$.ajax({
            url: "http://localhost:8080/BEAT-PD/Admin/Add/Activity",
            cache: false,
            type: "POST",
            data: myJSON,
            contentType: "application/json;charset=utf-8",
            complete: function() {
}
        });

 location.reload();
}



//insert NewHabit
function AddNewHabitToDB()
{
var HabitName =  $("#txtName").val();
var ac = new Habit();
ac.habitName = HabitName;

  if($("#lblType").text() == 'Up')
  {
      ac.groupID = "0";
  }
  else
  {
      ac.groupID = "1";
  }


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


ac.subMenus = sm;
var myJSON = JSON.stringify(ac);
var mydata2 = JSON.parse(myJSON);

$.ajax({
            url: "http://localhost:8080/BEAT-PD/Admin/Add/Habit",
            cache: false,
            type: "POST",
            data: myJSON,
            contentType: "application/json;charset=utf-8",
            complete: function() {
}
        });
      //  AddHabitUp();
location.reload();
}

//insert NewMedicine
function AddNewMedicineToDB()
{
 /*
    this.medicineSerialNumber = String;
    this.medicineName = String;
    this.medicineLimitation = String;
    this.info = String;
    */


   var medicineObject = new Medicine();
   medicineObject.medicineName = $("#txtMedicineName").val();
     medicineObject.medicineSerialNumber = $("#txtMedicineSerial").val();
       medicineObject.medicineLimitation = $("#txtMedicineLimitation").val();
         medicineObject.info = $("#txtMedicineInfo").val();


var myJSON = JSON.stringify(medicineObject);
//var mydata2 = JSON.parse(myJSON);

$.ajax({
            url: "http://localhost:8080/BEAT-PD/Admin/Add/Medicine",
            cache: false,
            type: "POST",
            data: myJSON,
            contentType: "application/json;charset=utf-8",
            complete: function() {
}
        });

location.reload();
}


//insert SleepQuality
function InsertNewSleepQualityToDB()
{


   var SleepQualityObject = new SleepQuality();
   SleepQualityObject.sleepQualityName = $("#txtName").val();

var myJSON = JSON.stringify(SleepQualityObject);
//var mydata2 = JSON.parse(myJSON);

$.ajax({
            url: "http://localhost:8080/BEAT-PD/Admin/Add/SleepQuality",
            cache: false,
            type: "POST",
            data: myJSON,
            contentType: "application/json;charset=utf-8",
            complete: function() {
}
        });

location.reload();

}

//insert
function InsertNewSleepDisorderToDB()
{


   var SleepDisorderObject = new SleepDisorder();
   SleepDisorderObject.sleepDisorderName = $("#txtName").val();
var myJSON = JSON.stringify(SleepDisorderObject);
//var mydata2 = JSON.parse(myJSON);

$.ajax({
            url: "http://localhost:8080/BEAT-PD/Admin/Add/SleepDisorder",
            cache: false,
            type: "POST",
            data: myJSON,
            contentType: "application/json;charset=utf-8",
            complete: function() {
}
        });

location.reload();


}

function InsertMoodToDB()
{

   var MoodConditionObject = new MoodCondition();
   MoodConditionObject.moodConditionName = $("#txtName").val();
   var myJSON = JSON.stringify(MoodConditionObject);
//var mydata2 = JSON.parse(myJSON);

$.ajax({
            url: "http://localhost:8080/BEAT-PD/Admin/Add/MoodCondition",
            cache: false,
            type: "POST",
            data: myJSON,
            contentType: "application/json;charset=utf-8",
            complete: function() {
}
        });

location.reload();


}

function insertNewLinkToDB()
{

   var LinkObject = new Link();
      LinkObject.linkHeadLine = $("#txtName").val();
      LinkObject.linkURL = $("#txtLinkUrl").val();
   var myJSON = JSON.stringify(LinkObject);
//var mydata2 = JSON.parse(myJSON);

$.ajax({
            url: "http://localhost:8080/BEAT-PD/Admin/Add/Link",
            cache: false,
            type: "POST",
            data: myJSON,
            contentType: "application/json;charset=utf-8",
            complete: function() {
}
        });

location.reload();

}


//////////////////////////////////////////////*******************************//////////////////////////////////////////



///////////////////////////////////////////************Control The Models PopUp Visibility**********///////////////////
function AddNewItemSleepQuality()
{

 $("#lblHead").text('איכות שינה');
 document.getElementById("ModalBody").style.display='none';
    document.getElementById("divForLinksUrl").style.display='none';
    modal.style.display = "block";

}

function AddNewItemUp()
{
   $("#lblHead").text('ביום-יום');
   $("#lblType").text('Up');
    document.getElementById("divForLinksUrl").style.display='none';
     modal.style.display = "block";

}

function AddNewItemDown()
{
   $("#lblHead").text('ביום-יום');
    $("#lblType").text('Down');
     document.getElementById("divForLinksUrl").style.display='none';
     modal.style.display = "block";
}


function AddNewItem()
{

 if($("#ActivitiesTab")[0].parentElement.classList[0] == "active")
 {
   $("#lblHead").text('פעילויות');
    document.getElementById("ModalBody").style.display='block';
        document.getElementById("divForLinksUrl").style.display='none';
    modal.style.display = "block";
 }

  if($("#MedicineTab")[0].parentElement.classList[0] == "active")
 {
   $("#lblHead").text('תרופות');
    document.getElementById("ModalBody").style.display='block';
    document.getElementById("divForLinksUrl").style.display='none';
    MedicineModal.style.display = "block";
 }

   if($("#SleepTab")[0].parentElement.classList[0] == "active")
 {
   $("#lblHead").text('הפרעות שינה');
      document.getElementById("ModalBody").style.display='none';
      document.getElementById("divForLinksUrl").style.display='none';

    modal.style.display = "block";
 }

   if($("#MoodTab")[0].parentElement.classList[0] == "active")
 {
   $("#lblHead").text('מצב-רוח');
    document.getElementById("ModalBody").style.display='none';
     document.getElementById("divForLinksUrl").style.display='none';
    modal.style.display = "block";
 }

   if($("#LinksTab")[0].parentElement.classList[0] == "active")
 {
   $("#lblHead").text('לינקים');
    document.getElementById("ModalBody").style.display='none';
     document.getElementById("divForLinksUrl").style.display='block';
    modal.style.display = "block";
 }


}

/////////////////////////////////////////////////****************************//////////////////////////////////////////