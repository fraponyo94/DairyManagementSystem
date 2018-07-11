function validateform() {
    var X = document.getElementById("fname");

    if(X.value == ""){
        Alert("please enter correct name");
        return false;
    }

}