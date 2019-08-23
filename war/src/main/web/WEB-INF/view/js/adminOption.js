function addTeacher() {
    let studentInfo = document.getElementById("student").value;
    let studentId = studentInfo.substr(0, studentInfo.indexOf(" "));

    document.location.href = "newTeacher?studentId=" + studentId;
}

function addSubjectForTeacher() {
    let teacherInfo = document.getElementById("teachers").value;
    let teacherId = teacherInfo.substr(0, teacherInfo.indexOf(" "));

    let subjectInfo = document.getElementById("subjects").value;
    let subjectId = subjectInfo.substr(0, subjectInfo.indexOf(" "));

    document.location.href = "newTeacherForSubject?teacherId=" + teacherId + "&subjectId=" + subjectId;
}

function newSubject() {
    let subjectName = document.getElementById("subjectName").value;
    let subjectContent = document.getElementById("subjectContent").value;

    document.location.href = "newSubject?subjectName=" + subjectName + "&subjectContent=" + subjectContent;
}

function newTask() {
    let subjectName = document.getElementById("subjects").value;

    let taskName = document.getElementById("taskName").value;
    let taskContent = document.getElementById("taskContent").value;
    let maxMark = document.getElementById("maxMark").value;

    document.location.href = "newTask?subjectName=" + subjectName
        + "&taskName=" + taskName + "&taskContent=" + taskContent + "&maxMark=" + maxMark;
}

function logout() {
    document.location.href = "logout";
}