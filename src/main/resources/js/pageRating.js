AJS.toInit(function () {
	$('.star-rating__input').click(function(){
			ratePage($(this));
		});
	
		AJS.log("rate: " + pageRating);
		
		if(pageRating!=null){
			for(var i=1;i<=5;i++)
				setStarClass('#lsr'+i,pageRating,i-1);
		}
			for(var i=1;i<=5;i++)
				if(i<=pageUserRating)
					$('#star-rating-'+i).attr('checked',true);
				else
					$('#star-rating-'+i).attr('checked',false);

	
});

function setStarClass (objId, value, min){
	var half = min + 0.5;
	AJS.log("rate 2: " + value+' min:'+min +' half:'+half);
	if(value > min && value <= half){
		$(objId).removeClass('fa-star-o');
		$(objId).addClass('fa-star-half-o');
	}else if(value > half){
		$(objId).removeClass('fa-star-o');
		$(objId).addClass('fa-star');
	}else if(value < min){
		$(objId).removeClass('fa-star');
		$(objId).removeClass('fa-star-half-o');
		$(objId).addClass('fa-star-o')
	}
}

function ratePage(obj){
    AJS.$.ajax({
        url: AJS.params.contextPath +'/rest/ratepage/1.0/add-rate/'+pageId+'/'+obj.val(),
        type: "GET",
        cache : false,
        dataType: 'json',
        success: function(response){
        	if(pageRating !=null){}
			for(var i=1;i<=5;i++)
				setStarClass('#lsr'+i,-1,i);
    		if(response.value >= 0.0)
    			for(var i=1;i<=5;i++){
    				AJS.log("rate 1: " + response.value+' min:'+(i-1));
    				setStarClass('#lsr'+i,response.value,(i-1));
    			}	
        }	
        }
    });
}
