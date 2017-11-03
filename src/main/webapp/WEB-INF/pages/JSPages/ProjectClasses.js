
///////////////////////////////////////*************Classes**********///////////////////////////////////////////////////
function subMenu () {
    this.subMenu = String;

}

function Activity() {
    this.activityName = String;
    this.subMenus = [];
}


function Habit() {
    this.habitName = String;
    this.groupID = String;
    this.subMenus = [];
}

function Medicine() {
    this.medicineSerialNumber = String;
    this.medicineName = String;
    this.medicineLimitation = String;
    this.info = String;
}

function SleepQuality() {
    this.sleepQualityName = String;
}

function SleepDisorder() {
    this.sleepDisorderName = String;
}

function Link() {
    this.linkHeadLine = String;
    this.linkURL = String;
}


function MoodCondition() {
    this.moodConditionName = String;
}

function Patient(){
    this.patientID = String;
    this.patientFirstName = String;
    this.patientLastName = String;
    this.patientStatus = String;
    this.patientAge = String;
    this.patientPass = String;
}

function User(){
    this.id = String;
    this.name = String;
    this.pass = String;
    this.role = String;
    this.lastLogin = String;
}

function AuthEnc(){
    this.i=String;
    this.p=String;
}
//////////////////////////////////**********************************************///////////////////////////////////////
