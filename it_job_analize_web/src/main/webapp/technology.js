$(function(){
	var tecName = technology();
	var num=number();
	picture(tecName,num);
});
function technology(){
	var t = [];
	$.ajax({
		url:"host/dbtechnology",
		type:"get",
		async:false,
		dataType:"json",
		success:function(result){
			if(result.status==200){
				for(var i=0;i<result.data.length;i++){
					t[i] = result.data[result.data.length-1-i].position;
				}
			}
		},
		error:function(){
			alert("请求失败!");
		}
	});
//	alert(t);
	return t;
}

function number(){
	var t = [];
	$.ajax({
		url:"host/dbtechnology",
		type:"get",
		async:false,
		dataType:"json",
		success:function(result){
			if(result.status==200){
				for(var i=0;i<result.data.length;i++){
					t[i] = result.data[result.data.length-1-i].count;
				}
			}
		},
		error:function(){
			alert("请求失败!");
		}
	});
//	alert(t);
	return t;
}

function picture(tecName,num){

	require.config({
		paths: {
			echarts: 'http://echarts.baidu.com/build/dist'
		}
	});

	require(
			[
				'echarts',
				'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
				],
				function (ec) {
				// 基于准备好的dom，初始化echarts图表
				var myChart = ec.init(document.getElementById('technology')); 
				var option = {
					    title : {
					        text: '大数据技术需求总览表',
					        subtext: '数据来自网络'
					    },
					    tooltip : {
					        trigger: 'axis'
					    },
					    legend: {
					        data:['2018年',' ']
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            mark : {show: true},
					            dataView : {show: true, readOnly: false},
					            magicType: {show: true, type: ['line', 'bar']},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : true,
					    xAxis : [
					        {
					            type : 'value',
					            boundaryGap : [0, 0.01]
					        }
					    ],
					    yAxis : [
					        {
					            type : 'category',
					            data : tecName,
//					            data:['jkd','jksjk','jksjd']
					            axisLabel:{
					            	interval:0
					            }
					        }
					    ],
					    series : [
					        {
					            name:'2018年',
					            type:'bar',
					            data:num
//					            data:[123,12312,121]
					        }
					      
					    ]
					};

				// 为echarts对象加载数据 
				myChart.setOption(option); 
				window.onresize = myChart.resize;
			}
	);

}



































//var chart = Highcharts.chart('technology', {
//chart: {
//type: 'bar'
//},
//title: {
//text: '大数据岗位对不同技术的市场需求量'
//},
//subtitle: {
//text: '数据来源于网络'
//},
//xAxis: {
//categories:tecName,
//title: {
//text: '技术名称'
//},
//labels:{
//formatter:function(){
//return this.value;

//}
//}
//},
//yAxis: {
//min: 0,
//title: {
//text: '需求量(个)',
//align: 'high'
//},
//labels: {
//overflow: 'justify'
//}
//},
//tooltip: {
//valueSuffix: ' 个'
//},
//plotOptions: {
//bar: {
//dataLabels: {
//enabled: true,
//allowOverlap: true // 允许数据标签重叠
//}
//}
//},
//legend: {
//layout: 'vertical',
//align: 'right',
//verticalAlign: 'top',
//x: 0,
//y: 100,
//floating: true,
//borderWidth: 1,
//backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
//shadow: true
//},
//series: [{
//name: '2017 年',
//data: num
//}]
//});