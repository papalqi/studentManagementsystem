$(function () {
	var cookie = localStorage.cook;
	var couid = localStorage.couid;
	var username = localStorage.account;
	var identity = localStorage.identity;
	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectMessage?cookie="+cookie+"&couid="+couid,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	console.log("显示成功！");
                    	$.each(data.info, function(i,val){  
                    		     var oDate = new Date(val.tcommenttime);  
						         var oYear = oDate.getFullYear();  
						         var oMonth = oDate.getMonth()+1;  
						         var oDay = oDate.getDate();  
						         var oHour = oDate.getHours();  
						         var oMin = oDate.getMinutes();  
						         var oSen = oDate.getSeconds();  
						    	var leadate = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);
						    	 function getzf(num){  
							          if(parseInt(num) < 10){  
							              num = '0'+num;  
							          }  
							          return num;  
							      }
						    
						var content = "<a class='panel-body bk-bg-very-light-gray bk-bg-lighten bk-padding-off-top bk-padding-off-bottom'><div class='pull-left bk-margin-top-10 bk-margin-right-10'><div class='bk-avatar'><img src='"+val.spageaddress+"' alt='' class='img-circle bk-img-60 bk-border-off' /></div></div><div class='bk-fg-inverse bk-margin-top-10'><strong class='bk-fg-primary'>"+val.sname+"</strong><span class='pull-right'><i class='fa fa-clock-o'></i><small> "+leadate+" </small></span></div><p>"+val.scomment+"</p></a><hr class='bk-margin-off' />";
						$("#leavemess_box").append(content);
						  }); 
	                }else if(data.status == 400){
	                	console.log("显示失败");
	                }
                },
                error: function (err) {
                    console.log("超时!");
                }
            });
        
        $("#send_leavemess").click(function(){
        	var context = $("#leavemess_context").val();
        	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SendMessage?cookie="+cookie+"&username="+username+"&identity="+identity+"&context="+context+"&couid="+couid,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	alert("留言成功！");
                    	
	                }else if(data.status == 400){
	                	console.log("显示失败");
	                }
                },
                error: function (err) {
                    console.log("超时!");
                }
            });
        });
        
     });