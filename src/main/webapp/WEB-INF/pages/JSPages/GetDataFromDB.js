
/////////////////////////////****************Get All Tables From Data-Base*************/////////////////////////////////

function AddNewActivity()
{


 $.ajax({
            url: "http://localhost:8080/BEAT-PD/User/GET/AllActivities",
            cache: false,
            contentType: "application/json;charset=utf-8",
            complete: function(data) {
              //  alert('test');
               if(data.status != 200 ) alert('Error in getting data from DB:' + jsonString);
               else
               {
              var jsonString = data.responseText;
               jsonStringList =jsonString.substring(54,jsonString.length - 1);
              // var ttt = eval(jsonStringList);
               var json = JSON.stringify(eval(jsonStringList));
               var mydata2 = JSON.parse(json);
var txt = "";
var j=0;
              for(var i=0;i<mydata2.length;i++)
              {
                 if(mydata2[i].activityName)
                 {
                  txt += "<tr>"+
                            "<td><input type='image' src='../resources/remove1.png' alt='Submit' onclick = 'DeleteItem(this)' style='width:20px;hight:20px'></td>"+
                            "<td><input type='image' src='../resources/Edit1.ico' alt='Edit' onclick = 'PrepareUpdateModals(this)' style='width:20px;hight:20px'></td>"+
                            "<td>"+mydata2[i].activityName+"</td>"+
                            "<td>"+ "<select style='width:100%' id='ddlMainGeneral'>"
                            for(var j=0;j<mydata2[i].subMenus.length;j++)
                            {
                            txt+=
                                "<option value='j'>"+mydata2[i].subMenus[j].subMenu +"</option>"
                            }

                             txt += "</select>"+
                            "</td>"+
                        "</tr>"
                 }

              }
              if(txt != "")
              {
                        $("#tblActivities").append(txt);

              }
              }
            }
        });



}

function AddHabitUp()
{
// $("#tblHabitDown").empty();

 /*for(var i =1; i <  $("#tblHabitUp tr").length ; i++)
 {
    $("#tblHabitUp tr#"+ i).remove();

 }*/

$.ajax({
            url: "http://localhost:8080/BEAT-PD/User/GET/AllHabits",
            cache: false,
           contentType: "application/json;charset=utf-8",
            complete: function(data) {
              //  alert('test');
              var jsonString = data.responseText;
               jsonStringList =jsonString.substring(46,jsonString.length - 1);
               var json = JSON.stringify(eval(jsonStringList));
               var mydata2 = JSON.parse(json);
var txt = "";
var txt2 = "";
var j=0;

              for(var i=0;i<mydata2.length;i++)
              {
                 if(mydata2[i].habitName)
                 {
                   if(mydata2[i].groupID == "0")
                   {
                            txt += "<tr>"+
                            "<td><input type='image' src='../resources/remove1.png' alt='Submit' onclick = 'DeleteItem(this)' style='width:20px;hight:20px'></td>"+
                            "<td><input type='image' src='../resources/Edit1.ico' alt='Edit1' onclick = 'PrepareUpdateModals(this)' style='width:20px;hight:20px'></td>"+
                            "<td>"+mydata2[i].habitName+"</td>"+
                            "<td>"+ "<select style='width:100%'>"
                            for(var j=0;j<mydata2[i].subMenus.length;j++)
                            {
                            txt+=

                                "<option value='j'>"+mydata2[i].subMenus[j].subMenu +"</option>"
                            }

                             txt += "</select>"+
                            "</td>"+
                        "</tr>"
                    }

                    else if(mydata2[i].groupID == "1")
                   {

                   txt2 += "<tr>"+
                            "<td><input type='image' src='../resources/remove1.png' alt='Submit' onclick = 'DeleteItem(this)' style='width:20px;hight:20px'></td>"+
                            "<td><input type='image' src='../resources/Edit1.ico' alt='Edit2' onclick = 'PrepareUpdateModals(this)' style='width:20px;hight:20px'></td>"+
                            "<td>"+mydata2[i].habitName+"</td>"+
                            "<td>"+ "<select style='width:100%'>"
                            for(var j=0;j<mydata2[i].subMenus.length;j++)
                            {
                            txt2+=

                                "<option value='j'>"+mydata2[i].subMenus[j].subMenu +"</option>"
                            }

                             txt2 += "</select>"+
                            "</td>"+
                        "</tr>"

                   }
                 }

              }
              if(txt != "")
              {
                        $("#tblHabitUp").append(txt);

              }

               if(txt2 != "")
              {
                        $("#tblHabitDown").append(txt2);

              }
            }
        });


}

function AddMedicine()
{

$.ajax({
            url: "http://localhost:8080/BEAT-PD/User/GET/AllMedicines",
            cache: false,
           contentType: "application/json;charset=utf-8",
            complete: function(data) {
              //  alert('test');
              var jsonString = data.responseText;
               jsonStringList =jsonString.substring(52,jsonString.length - 1);
               var json = JSON.stringify(eval(jsonStringList));
               var mydata2 = JSON.parse(json);
var txt = "";
              for(var i=0;i<mydata2.length;i++)
              {
                 if(mydata2[i].medicineName)
                 {
                  txt += "<tr>"+
                            "<td><input type='image' src='../resources/remove1.png' alt='Submit' onclick = 'DeleteItem(this)' style='width:20px;hight:20px'></td>"+
                            "<td><input type='image' src='../resources/Edit1.ico' alt='Edit' onclick = 'PrepareUpdateModals(this)' style='width:20px;hight:20px'></td>"+
                              "<td>"+mydata2[i].medicineSerialNumber+"</td>"+
                              "<td>"+mydata2[i].medicineName+"</td>"+
                              "<td>"+mydata2[i].medicineLimitation+"</td>"+
                              "<td>"+mydata2[i].info+"</td>"+
                            "</td>"+
                            "</tr>"
                 }

              }
              if(txt != "")
              {
                        $("#tblMedicine").append(txt);

              }
            }
        });


}


function AddMood()
{

$.ajax({
            url: "http://localhost:8080/BEAT-PD/User/GET/AllMoodConditions",
            cache: false,
           contentType: "application/json;charset=utf-8",
            complete: function(data) {
              //  alert('test');
              var jsonString = data.responseText;
               jsonStringList =jsonString.substring(62,jsonString.length - 1);
               var json = JSON.stringify(eval(jsonStringList));
               var mydata2 = JSON.parse(json);
var txt = "";
              for(var i=0;i<mydata2.length;i++)
              {
                 if(mydata2[i].moodConditionName)
                 {
                  txt += "<tr>"+
                            "<td><input type='image' src='../resources/remove1.png' alt='Submit' onclick = 'DeleteItem(this)' style='width:20px;hight:20px'></td>"+
                            "<td>"+mydata2[i].moodConditionName+"</td>"+
                            "</td>"+
                            "</tr>"
                 }

              }
              if(txt != "")
              {
                        $("#tblMood").append(txt);

              }
            }
        });

}

function AddLinks()
{

$.ajax({
            url: "http://localhost:8080/BEAT-PD/User/GET/AllLinks",
            cache: false,
           contentType: "application/json;charset=utf-8",
            complete: function(data) {
              //  alert('test');
              var jsonString = data.responseText;
               jsonStringList =jsonString.substring(44,jsonString.length - 1);
               var json = JSON.stringify(eval(jsonStringList));
               var mydata2 = JSON.parse(json);
var txt = "";
              for(var i=0;i<mydata2.length;i++)
              {
                 if(mydata2[i].linkURL)
                 {
                  txt += "<tr>"+
                            "<td><input type='image' src='../resources/remove1.png' alt='Submit' onclick = 'DeleteItem(this)' style='width:20px;hight:20px'></td>"+
                            "<td><input type='image' src='../resources/Edit1.ico' alt='EditLinks' onclick = 'PrepareUpdateModals(this)' style='width:20px;hight:20px'></td>"+
                            "<td>"+mydata2[i].linkHeadLine+"</td>"+
                            "<td><a target='_blank' href="+mydata2[i].linkURL+">"+mydata2[i].linkURL+"</a></td>"+
                            "</td>"+
                            "</tr>"
                 }

              }
              if(txt != "")
              {
                        $("#tblLinks").append(txt);

              }
            }
        });



}



function AddSleepQuality()
{

$.ajax({
            url: "http://localhost:8080/BEAT-PD/User/GET/AllSleepQuality",
            cache: false,
            contentType: "application/json;charset=utf-8",
            complete: function(data) {
              //  alert('test');
              var jsonString = data.responseText;
               jsonStringList =jsonString.substring(58,jsonString.length - 1);
               var json = JSON.stringify(eval(jsonStringList));
               var mydata2 = JSON.parse(json);
var txt = "";
              for(var i=0;i<mydata2.length;i++)
              {
                 if(mydata2[i].sleepQualityName)
                 {
                  txt += "<tr>"+
                            "<td><input type='image' src='../resources/remove1.png' alt='Submit' onclick = 'DeleteSleepQualityItem(this)' style='width:20px;hight:20px'></td>"+
                            "<td>"+mydata2[i].sleepQualityName+"</td>"+
                            "</td>"+
                            "</tr>"
                 }

              }
              if(txt != "")
              {
                        $("#tblSleepQuality").append(txt);

              }
            }
        });


}

function AddSleepDisorders()
{

$.ajax({
            url: "http://localhost:8080/BEAT-PD/User/GET/AllSleepDisorders",
            cache: false,
            contentType: "application/json;charset=utf-8",
            complete: function(data) {
              //  alert('test');
              var jsonString = data.responseText;
               jsonStringList =jsonString.substring(62,jsonString.length - 1);
               var json = JSON.stringify(eval(jsonStringList));
               var mydata2 = JSON.parse(json);
               var txt = "";
              for(var i=0;i<mydata2.length;i++)
              {
                 if(mydata2[i].sleepDisorderName)
                 {
                  txt += "<tr>"+
                            "<td><input type='image' src='../resources/remove1.png' alt='Submit' onclick = 'DeleteSleepDisorderItem(this)'style='width:20px;hight:20px'></td>"+
                            "<td>"+mydata2[i].sleepDisorderName+"</td>"+
                            "</td>"+
                            "</tr>"
                 }

              }
              if(txt != "")
              {
                        $("#tblSleepDisorders").append(txt);


              }
            }
        });


}

function AddPatient()
{

$.ajax({
            url: "http://localhost:8080/BEAT-PD/Admin//GET/AllPatients",
            cache: false,
           contentType: "application/json;charset=utf-8",
            complete: function(data) {
              //  alert('test');
              var jsonString = data.responseText;
               jsonStringList =jsonString.substring(51,jsonString.length - 1);
               var json = JSON.stringify(eval(jsonStringList));
               var mydata2 = JSON.parse(json);
var txt = "";
              for(var i=0;i<mydata2.length;i++)
              {
                 if(mydata2[i].patientID)
                 {
                  txt += "<tr>"+
                            "<td><input type='image' src='../resources/remove1.png' id='remove' alt='Delete' onclick = 'DeleteItem(this)'  style='width:20px;hight:20px'></td>"+
                            "<td><input type='image' src='../resources/Edit1.ico' alt='EditPatient' onclick = 'PrepareUpdateModals(this)' style='width:20px;hight:20px'></td>"+
                            "<td><input type='image' src='../resources/report1.png' alt='report' onclick = 'GetPatientReportById(this)' style='width:20px;hight:20px'></td>"+
                               "<td>"+mydata2[i].patientID+"</td>"+
                              "<td>"+mydata2[i].patientFirstName+"</td>"+
                              "<td>"+mydata2[i].patientLastName+"</td>"+
                              "<td>"+mydata2[i].patientAge+"</td>"+
                              "<td>"+mydata2[i].patientStatus+"</td>"+
                            "</td>"+
                            "</tr>"
                 }

              }
              if(txt != "")
              {
                        $("#tblPatient").append(txt);

              }
            }
        });


}


function GetAllPatientReport()
{
    window.open('http://localhost:8080/BEAT-PD/Admin/GET/PatientsReport', '_blank');
}

function GetPatientReportById(Row)
{
    var PatientId = Row.parentNode.parentNode.cells[3].innerText;

    $.ajax({
        url: 'http://localhost:8080/BEAT-PD/Admin/GET/PatientReportByID/'+PatientId,
        cache: false,
        type: "GET",
        async:false,
        contentType: "application/json;charset=utf-8",
        complete: function(response){
            if(response.status!==200){
                alert(response.statusText)
            }
            else{
                var s = response;
                window.open('http://localhost:8080/BEAT-PD/Admin/GET/PatientReportByID/'+PatientId, '_blank');
            }
        }
    });

}
function GetAllAdminUsers()
{

$.ajax({
            url: "http://localhost:8080/BEAT-PD/Admin/GET/AllUsers",
            cache: false,
            type:"GET",
           contentType: "application/json;charset=utf-8",
            complete: function(response) {
                if (response.status === 200) {
                    var jsonString = response.responseText;
                    var jsonStringList = jsonString.substring(44, jsonString.length - 1);
                    var json = JSON.stringify(eval(jsonStringList));
                    var admins = JSON.parse(json);
                    var txt = "";


                    for (var i = 0; i < admins.length; i++) {
                        if (admins[i].id) {
                            var lastLogin="";
                            if(admins[i].lastLogin !== "null")
                                lastLogin = admins[i].lastLogin;
                            txt += "<tr>" +
                                "<td><input type='image' src='../resources/remove1.png' id='remove' alt='Delete' onclick = 'DeleteItem(this)'  style='width:20px;hight:20px'></td>" +
                                "<td><input type='image' src='../resources/Edit1.ico' alt='EditAdminUser' onclick = 'PrepareUpdateModals(this)' style='width:20px;hight:20px'></td>" +
                                "<td>" + admins[i].id + "</td>" +
                                "<td>" + admins[i].name + "</td>" +
                                "<td>" + lastLogin + "</td>" +
                                "<td>" + admins[i].role + "</td>" +
                                "</td>" +
                                "</tr>"
                        }

                    }
                    if (txt !== "") {
                        $("#tblAdminUser").append(txt);

                    }
                }
                else
                    alert("Error: failed to load admins page" + response.status)
            }
        });


}


////////////////////////////////////////******************************//////////////////////////////////////////////////


////////////////////////////////////////*******************Fill Buttom Chart*************////////////////////////////////
function ViewTeamChart()
{

var chart = new CanvasJS.Chart("chartContainerTeam",
    {
      title:{
        text: ""
      },
      animationEnabled: true,
      axisY:{
        titleFontFamily: "arial",
        titleFontSize: 14,
        includeZero: false
      },
      toolTip: {
        shared: true
      },
	  data: [
	  	        {

        type: "bar",
        name: "ticketbis",
        showInLegend: true,
		        dataPoints: [
        {label:"הליכה", y: 254},
        {label: "קפה", y: 264},
        {label: "שחייה", y: 264},
        {label: "ביקור רופא", y: 290},
        {label: "Apomorphine", y: 285},
        {label: "הרבה חלומות", y: 369},
        {label: "קושי בהרדמות", y: 329}
		]
      },
	  	        {

        type: "bar",
        name: "ticket2",
        showInLegend: true,
		        dataPoints: [
        {label:"הליכה", y: 465},
        {label: "קפה", y: 463},
        {label: "שחייה", y: 463},
        {label: "ביקור רופא", y: 464},
        {label: "Apomorphine", y: 464},
        {label: "הרבה חלומות", y: 503},
        {label: "קושי בהרדמות", y: 503}
		]
      },
	  	  ],
      legend:{
        cursor:"pointer",
        itemclick:function(e){
          if(typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
          	e.dataSeries.visible = false;
          }
          else {
          	e.dataSeries.visible = true;
          }
          chart.render();
        }
      }
    });

chart.render();

}


///////////////////////////////////////////**************************************///////////////////////////////////////


//////////////////////////////////////////***********Fill Upper Chart************///////////////////////////////////////
function ViewProjectChart()
{
 var chart = new CanvasJS.Chart("chartContainer",
    {
      title:{
        text: "Patients update activity",
      },
      animationEnabled: true,
      axisY:{
        titleFontFamily: "arial",
        titleFontSize: 8,
        includeZero: false
      },
      toolTip: {
        shared: true
      },
	  data: [
	  	        {

        type: "pie",
        name: "ticketbis",
        showInLegend: true,
		        dataPoints: [
        {label:"הליכה", y: 254},
        {label: "קפה", y: 264},
        {label: "שחייה", y: 264},
        {label: "ביקור רופא", y: 290},
        {label: "Apomorphine", y: 285},
        {label: "הרבה חלומות", y: 369},
        {label: "קושי בהרדמות", y: 329}
		]
      },
	  	        {

        type: "pie",
        name: "ticket2",
        showInLegend: true,
		        dataPoints: [
        {label:"הליכה", y: 465},
        {label: "קפה", y: 463},
        {label: "שחייה", y: 463},
        {label: "ביקור רופא", y: 464},
        {label: "Apomorphine", y: 464},
        {label: "הרבה חלומות", y: 503},
        {label: "קושי בהרדמות", y: 503}
		]
      },
	  	  ],
      legend:{
        cursor:"pointer",
        itemclick:function(e){
          if(typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
          	e.dataSeries.visible = false;
          }
          else {
          	e.dataSeries.visible = true;
          }
          chart.render();
        }
      }
    });

chart.render();


}

/////////////////////////////*********************************************************//////////////////////////////////

