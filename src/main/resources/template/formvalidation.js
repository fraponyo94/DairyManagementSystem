function validateform() {
    var X = document.getElementById("fname");

    if(X.value == "tolbert"){
        Alert("please enter correct name");
        return false;
    }
}