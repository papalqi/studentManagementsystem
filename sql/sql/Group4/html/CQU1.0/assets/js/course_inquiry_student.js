$(function () {
	var cookie = localStorage.cook;
	var stuid = localStorage.account;
	
	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectCanChoiseCourse?cookie="+cookie+"&context=",
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	var n = 0;
                    	console.log("显示成功！");
                    	
    					localStorage.setItem("userInfo",JSON.stringify(data.info));
    					
                    	 
                    	$.each(data.info, function(i,val){   
                    		n=n+1;
                    		if(n<9){
                    		var content = "<tr><td><a >"+val.scourseid+"</a></td><td>"+val.scoursename+"</td><td class='couid_td'>"+val.iteachclassid+"</td><td class='am-hide-sm-only'>"+val.scollegename+"</td><td>"+val.smajorname+"</td><td class='am-hide-sm-only'>"+val.icredit+"</td><td>"+val.ihours+"</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs am-text-secondary select_butt'><span class='am-icon-pencil-square-o'></span> 选课</button></div></div></td></tr>";
						    $(".am-table tbody").append(content);
						    }else{
						    	return false;
						    }
						  }); 
	                }else if(data.status == 400){
	                	console.log("显示失败");
	                }
                },
                error: function (err) {
                    console.log("超时!");
                }
            });
            
           $(".select_butt").click(function(){
        	var couid = $(this).parents("tr").find(".couid_td").text();
            $.ajax({
                url:"http://192.168.1.187:8080/studentgrade/ChoiseCourse?cookie="+cookie+"&couid="+couid+"&stuid="+stuid,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	alert("选课成功！");
	                }else if(data.status == 400){
	                	console.log("失败");
	                }
                },
                error: function (err) {
                    console.log("超时!");
                }
            });
        });
           $(".am-icon-search").click(function(){
        	var keyword = $("#fuzzy_search").val();  
			$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectCanChoiseCourse?cookie="+cookie+"&context="+keyword,
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
                    	$(".am-table tbody").empty();
                    	alert("搜索完成！");
                    	$.each(data.info, function(i,val){   
                    		var n=i+1;
                    		var content = "<tr><td><input type='checkbox'></td><td><a class='couid_td'>"+val.iteachclassid+"</a></td><td>"+val.scoursename+"</td><td class='am-hide-sm-only'>"+val.smajorname+"</td><td>"+val.sname+"</td><td class='am-hide-sm-only'>"+val.icredit+"</td><td>"+val.ihours+"</td><td>"+val.istudentnumber+"</td><td>"+val.istudentmaxnumber+"</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs am-text-secondary change_butt'><span class='am-icon-pencil-square-o'></span> 选课</button></div></div></td></tr>";
						    $(".am-table tbody").append(content);
						  }); 
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