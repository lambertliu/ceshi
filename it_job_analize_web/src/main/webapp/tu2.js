var position;

$("#select").change(function(){
	 position = $("#select option:selected").val();
	
	var tecName = data();
	var num=number();
	picture(tecName,num);
});
function data(){
	var dt = [];
	$.ajax({
		url:"http://localhost:8089/aveSalary/position",
		type:"get",
		async:false,
		data:{"position":position},
		dataType:"json",
		success:function(result){
			
			if(result.status==200 &&result.data.length>=20){				
			for(var i=0;i<20;i++){					
					dt[i] = result.data[i].city;
			}
		}
			
			if(result.status==200&&result.data.length<20 ){				
				for(var i=0;i<result.data.length;i++){					
						dt[i] = result.data[i].city;
				}
			}
		},
		error:function(){
			alert("请求失败!");
		}
	});

	return dt;
}

function number(){
	var dt = [];
	$.ajax({
		url:"http://localhost:8089/aveSalary/position",
		type:"get",
		async:false,
		data:{"position":position},
		dataType:"json",
		success:function(result){
			if(result.status==200 &&result.data.length>=20){				
				for(var i=0;i<20;i++){					
						dt[i] = result.data[i].salary;
				}
			}
				
				if(result.status==200&&result.data.length<20 ){				
					for(var i=0;i<result.data.length;i++){					
							dt[i] = result.data[i].salary;
					}
				}},
		error:function(){
			alert("请求失败!");
		}
	});
	
	return dt;
}


function picture(tecName,num){

	
var chart = Highcharts.chart('tu2', {
	chart: {
		type: 'bar'
	},
	title: {
		text: '各岗位对应各城市平均薪资'
	},
	subtitle: {
		text: '数据来自网络'
	},
	xAxis: {
		categories:tecName,
//		categories:['java','hadoop'],
		title: {
			text: '平均薪资'
		},
		labels:{
			formatter:function(){
				return this.value;
			}
		}
	},
	yAxis: {
		min: 0,
		title: {
			text: '平均薪资(￥)',
			align: 'high'
		},
		labels: {
			overflow: 'justify'
		}
	},
	tooltip: {
		valueSuffix: ' 元'
	},
	plotOptions: {
		bar: {
			dataLabels: {
				enabled: true,
				allowOverlap:false // 允许数据标签重叠
			}
		}
	},
	legend: {
		layout: 'vertical',
		align: 'right',
		verticalAlign: 'top',
		x: 0,
		y: 100,
		floating: true,
		borderWidth: 1,
		backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
		shadow: true
	},
	series: [{
		name: '2017 年',
		data: num
	}]
});


}