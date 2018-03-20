$(function () {
	var cookie = localStorage.cook;
	var classid = localStorage.classid;
	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectClassGradeRanking?cookie="+cookie+"&classid="+classid,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	console.log("http://192.168.1.187:8080/studentgrade/SelectClassGradeRanking?cookie="+cookie+"&classid="+classid);
                    	var n = 0;
                    	console.log("显示成功！");
                    	console.log(data.info.length);
                    	$.each(data.info, function(i,val){
                    		n = n+1;
                    		var content = "<tr><td>"+n+"</td><td><a class='istudentid_td'>"+val.istudentid+"</a></td><td>"+val.sname+"</td><td>"+val.iclassid+"</td><td class='am-hide-sm-only'>"+val.sclassname+"</td><td class='am-hide-sm-only'>"+val.djidian+"</td></tr>";
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