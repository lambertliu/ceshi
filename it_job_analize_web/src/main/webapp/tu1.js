

function typeName(){
	
	var name = [];
	var sa = [];
	$.ajax({
		url:"aveSalary",
		type:"get",
		async:true,
		dataType:"json",
		success:function(result){
			if(result.status==200){
				for(var i=0;i<result.data.length;i++){
					name[i]=result.data[i].type_name;
					sa[i]=result.data[i].salary;
				}
			}
		
		
			huatu(name,sa);
			
		},
		error:function(){
			alert("请求失败1!");
		}
	});
	return name;
}

var type_name = typeName();


var huatu=function(name,sa){
	
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
				var myChart = ec.init(document.getElementById('tu1')); 

				var option = {
//						backgroundColor:'#FFFAFA',
						title : {
							text: 'IT职位平均薪资',
//							padding:[5,10,5,710],
						
							textStyle:{
								fontSize:25,
								color:'#000000',
							}
						},
						tooltip : {
							trigger: 'axis'
						},
						toolbox: {
							show : true,
							feature : {
								mark : {show: false},
								dataView : {show: true, readOnly: false},
								magicType : {show: true, type: ['line', 'bar','pie']},
								restore : {show: true},
								saveAsImage : {show: true}
							}
						},
						calculable : true,
						grid:{
							y2:140
						},
						xAxis : [
							{
								type : 'category',
								data : type_name,
								axisLabel:{
									interval:0,
									rotate:-30,
									textStyle: {
	                                    color: "black",
	                                   fontSize:12
	                                }
									
								},
							
							axisLine:{  
                                lineStyle:{  
                                    color:'#0087EF',  
                                    width:1,//这里是为了突出显示加上的  
                                }  
                            }  
							}
							],
							yAxis : [
							
								{
									type : 'value',
									axisLabel:{
										textStyle: {
		                                    color: "black",
		                                   fontSize:16
		                                }
										
									},
										
								}
								
								],
								series : [
									

									{
										name:'平均工资',
										type:'bar',
										data:sa,
										itemStyle:{
											normal:{
												color:'#4ad2ff'
											}
										},
										markPoint : {
											data : []
										},
										markLine : {
											data : [
												{type : 'average', name : '平均值'}
												]
										}
									}
									]
				};


				// 为echarts对象加载数据 
				myChart.setOption(option); 
				window.addEventListener("resize",function(){
					myChart.resize();
				});
			}
	);
	
}
//使用
var position;
$(function(){
	position = $("#select option:selected").val();
	var tecName = data();
	var num=number();
	picture(tecName,num);
});

$("#select").change(function(){
	position = $("#select option:selected").val();
	var tecName = data();
	var num=number();
	picture(tecName,num);
});
function data(){
	var dt = [];
	$.ajax({
		url:"aveSalary/position",
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
		url:"aveSalary/position",
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



function picture(tecName,num,position){

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
				var myChart = ec.init(document.getElementById('tu2')); 
				var option = {
					    title : {
					        text: '对应各城市平均薪资',
					        subtext: '数据来源于网络'
					    },
					    tooltip : {
					        trigger: 'axis'
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            dataView : {show: true, readOnly: false},
					            magicType : {show: true, type: ['line', 'bar']},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : true,
					    xAxis : [
					        {
					            type : 'category',
					            data : tecName,
					            
					            
					            axisLabel:{
									interval:0
									
					            }
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value'
					        }
					    ],
					    series : [
					        {
					            name:position,
					            type:'bar',
					            data:num,
					            markPoint : {
					                data : [
					                    {type : 'max', name: '最大值'},
					                    {type : 'min', name: '最小值'}
					                ]
					            },
					            markLine : {
					                data : [
					                    {type : 'average', name: '平均值'}
					                ]
					            }
					        }
					    ]
					};
				// 为echarts对象加载数据 
				myChart.setOption(option); 
//				window.addEventListener("resize",function(){
//					myChart.resize();
//				});
			}
	);

}

