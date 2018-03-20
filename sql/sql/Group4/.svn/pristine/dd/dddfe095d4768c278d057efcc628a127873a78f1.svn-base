$(function () {
	var cookie = localStorage.cook;
	var teaid= localStorage.account;
	var couclassid = localStorage.couclassid;
	var n = 0;
	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectClassGrade?cookie="+cookie+"&couclassid="+couclassid,
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
                    		 n=n+1;
							var content = "<tr><td>"+val.istudentid+"</td><td>"+val.sname+"</td><td class='am-hide-sm-only'>"+val.scoursename+"<td ><input id='score1h"+i+"' type='text'  /></td><td><input id='score2h"+i+"' type='text'  /></td></tr>";
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
            console.log(n);
    $("#rate_submit").click(function(){
    	var score1h;
    	var score2h;
    	var options=$("#proportion option:selected").val();
    	switch(Number(options)){
 		case 1:{
 			console.log("1");
 			score1h = 30;
 			score2h = 70;
 			console.log(score1h);
  			break;
 		}
 		case 2:{
 			score1h = 40;
 			score2h = 60;
 			break;
 		}
 		case 3:{
 			score1h = 50;
 			score2h = 50;
 			break;
 		}
 		case 4:{
 			score1h = 60;
 			score2h = 40;
 			break;
 		}
 		case 5:{
 			score1h = 70;
 			score2h = 30;
 			break;
 		}
 		case 0:{
 			alert("请选择分数比例");
 			break;
 		}
 		}
    	console.log(score2h);
    	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SetScoreH?cookie="+cookie+"&couclassid="+couclassid+"&score1h="+score1h+"&score2h="+score2h,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:true,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	console.log("提交比例成功！");
	                }else if(data.status == 400){
	                	console.log("显示失败");
	                }
                },
                error: function (err) {
                    console.log("超时!");
                }
            });
    });
    $("#grade_submit").click(function(){
        var info = [];
		
        for(var num=0;num<n;num++){
        	var score ={};
        	score.studentid = Number($(".am-table tbody").find("tr").eq(num).find("td").eq(0).text());
        	score.courseID = Number(couclassid);
        	score.score1 = Number($(".am-table tbody").find("tr").eq(num).find("td").eq(3).find("input").val());
        	score.score2 = Number($(".am-table tbody").find("tr").eq(num).find("td").eq(4).find("input").val());
//      	console.log($("#score2h"+n).val);s
        	info.push(score);
        }
        var data ={};
        console.log(info);
        data.cookie = cookie;
        data.info = info;
        var data_string = JSON.stringify(data);
        $.ajax({
                url:"http://192.168.1.187:8080/studentgrade/UpdataClassGrade",
                type:"post",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:true,
                data:data_string,
                dataType:"json",
                  contentType: "application/json",
                success:function(data){
                    if(data.status == 200){
                    	alert("提交成功");
                    }else if(data.status == 400){
                    	console.log("错误");
                    	console.log("http://192.168.1.187:8080/studentgrade/ChangeCourse?cookie="+cookie+"&couid="+couid+"&couname="+couname+"&couno="+couno+"&majorid="+majorid+"&icredit="+icredit+"&ihours="+ihours);
                    }else if(data.status == 408){
                    	console.log("超时");
                    }
                },
                error: function (err) {
                    console.log("情况异常!");
                    
                }
           });
    });
            
});