$(function(){
	picture();
});
$("#zhiwei>select").eq(0).change(function(){


	tu444();
});
$("#zhiwei>select").eq(1).change(function(){
	tu444();
});
$("#zhiwei>select").eq(2).change(function(){
	tu444();
});
$(function(){
	tu444();
});

var tu444=function(){


//	ajax


	var position=$("#zhiwei>select option:selected").eq(0).val();
	var year=$("#zhiwei>select option:selected").eq(1).val();
	var education=$("#zhiwei>select option:selected").eq(2).val();

	var city=[];
	var salary=[];
	$.ajax({
		url:"citysalary",
		type:"post",
		data:{"position":position,
			"experience":year,
			"education":education
		},
		dataType:"json",
		success:function(result){


			if(result.status==200 && result.data.length>=20){


				for(var i=0;i<20;i++){
					city[i]=result.data[i].city;
					salary[i]=result.data[i].salary;

				}

				picture(city,salary,position);


			}else if(result.status==200){

				for(var i=0;i<result.data.length;i++){
					city[i]=result.data[i].city;
					salary[i]=result.data[i].salary;

				}

				picture(city,salary,position);

			}
		},
		error:function(){
			alert("没数据啊");
		}
	});


}
function picture(city,salary,position){
	var chart = Highcharts.chart('tu1',{
		chart: {
			type: 'column',
			margin: 75,
			options3d: {
				enabled: true,
				alpha: 10,
				beta: 25,
				depth: 70,
				viewDistance: 100,      // 视图距离，它对于计算角度影响在柱图和散列图非常重要。此值不能用于3D的饼图
				frame: {                // Frame框架，3D图包含柱的面板，我们以X ,Y，Z的坐标系来理解，X轴与 Z轴所形成
					// 的面为bottom，Y轴与Z轴所形成的面为side，X轴与Y轴所形成的面为back，bottom、
					// side、back的属性一样，其中size为感官理解的厚度，color为面板颜色
					bottom: {
						size: 10
					},
					side: {
						size: 1,
						color: 'transparent'
					},
					back: {
						size: 1,
						color: 'transparent'
					}
				}
			},
		},
		title: {
			text: '你想去哪儿？'
		},
		subtitle: {
			text: '数据来自网络'
		},
		plotOptions: {
			column: {
				depth: 25
			}
		},
		xAxis: {
			categories: city
		},
		yAxis: {
			title: {
				text: null
			}
		},
		series: [{
			name: position+"(元)",
			data: salary
		}]
	});
}














