$(document).ready(function() {
	
	function readURL(input) {

	    if (input.files && input.files[0]) {
	        var reader = new FileReader();

	        reader.onload = function (e) {
	            $('#imgEvent').attr('src', e.target.result);
	        }

	        reader.readAsDataURL(input.files[0]);
	    }
	}

	$("#imgInp").change(function(){
	    readURL(this);
	});	

	
	
	$('#datepicker').datepicker({
	    format: "dd/mm/yyyy",
	    forceParse: false,
	    todayHighlight: true,
	    autoclose: true
	});
//	$('#datepicker').datepicker("update", new Date());
	
	
	 $('#timepicker1').timepicker({
         template: false,
         showInputs: false,
         minuteStep: 5,
         showMeridian: false,
         defaultTime: false

     });
	 
	 
	 $("#searchdate")
	    .datepicker({
	    	todayHighlight: true,
	    	format: "dd-mm-yyyy",
	      onSelect: function(dateText) {
	        $(this).change();
	      }
	    })
	    .change(function(e) {
	    	if(this.value != "")
	    		window.location.href = "calander?day=" + this.value;
	    });
	 
	 
	 //$('#timepicker1').timepicker('setTime', '12:45 AM');
});
