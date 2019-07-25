function myFunction(){
var input = $("#info").val()

$.ajax({
 type : "POST",
      contentType : "application/json",
      url :  "answer",
      data : JSON.stringify(input),
      dataType : 'json',
      success : function(result) {
      $("#rezultat").empty()
              $("#rezultat").append(result)
      },
      error : function(e) {
        alert("Error!")
        console.log("ERROR: ", e);
      }
});}