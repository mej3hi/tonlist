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
	 
	 
     $(".romoveBtn").click(function(){	        	
     	var id = $(this).attr('id');
     	var id3 = '#'+id;   
     	$('.conf').addClass("hidden");
     	$(id3+'.conf').removeClass("hidden");
	       

     });
 	$(".noBtn").click(function(){
 		var id2 = $(this).attr('id');
 		var id4 = '#'+id2;
 		$(id4+'.conf').addClass("hidden");
 		
     });
	 
	 
	 if(document.getElementById("searchdate"))
		 var availableDates = $('#searchdate').data("dates").split(",");

	     $('#searchdate').datepicker({ 
	    	 beforeShowDay: function (date) {
	    	        var dt_ddmmyyyy = f(date.getDate()) + '-' + f((date.getMonth() + 1)) + '-' + date.getFullYear();
	    	        return (availableDates.indexOf(dt_ddmmyyyy) != -1);
	    	    },
	    	    format: "dd-mm-yyyy"
	     }).on('changeDate', function (e) {
	    	 if(this.value != "")
	    		 window.location.href = "calander?day=" + this.value;});
	     
		    function f(x){
		    	return ("0"+x).slice(-2);
		    }

	 
	    $('.confirmation').on('click', function () {
	        return confirm('Are you sure?');
	    });
	 
	    
	 
	        $("#noo").click(function(){
	            $("p").hide();
	        });
	        

	        
	  
	    
	 
	 //$('#timepicker1').timepicker('setTime', '12:45 AM');
});
