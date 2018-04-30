
$(function() {
	var provinceno = $("#province").val();
	var cityno = $("#city").val();
	var districtno = $("#district").val();
	$.ajax( {
		type : "post",
		url : "personalinfoController.do?getPTerritory",
		data : {
			territorylevel : 2
		},
		dataType : "json",
		success : function(data) {
			var jsonarray = data.obj;
			for ( var i = 0; i < jsonarray.length; i++) {
				var json = jsonarray[i];
				var $addopt = $("<option value=''></option>");
				$addopt.val(json.territorycode);
				$addopt.text(json.territoryname);
				$("#provincecode").append($addopt);
			}
			if (provinceno != "") {
				$("#provincecode").val(provinceno);
				/*$("#provincecode option[value="+provinceno+"]").attr("selected","selected");*/
			}
			if (provinceno != "" && cityno != "") {
				getcterritory();
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			tip("操作失败");
		}
	});
});

var opertime = 0;
function getcterritory() {
	var cityno = $("#city").val();
	var districtno = $("#district").val();
	var territorycode = new String();
	territorycode = $("#provincecode option:selected").val();
	territorycode = "1" + territorycode;
	$
			.ajax( {
				type : "post",
				url : "personalinfoController.do?getCTerritory",
				data : {
					territorycode : territorycode
				},
				dataType : "json",
				success : function(data) {
					var jsonarray = data.obj;
					if (jsonarray.length > 0 && opertime == 0) {
						var $addrow = $("<select id='citycode' name='citycode' onChange='getdterritory()'>"
								+ "<option value=''>请选择</option>" + "</select>");
						$("#provincecode").after($addrow);
						opertime = 1;
					}
					if (jsonarray.length == 0 && opertime > 0) {
						$("#citycode").remove();
						$("#districtcode").remove();
						opertime = 0;
						dopertime = 0;
					}
					if (jsonarray.length > 0) {
						$("#citycode option").remove();
						$please = $("<option value=''>请选择</option>");
						$("#citycode").append($please);
						$("#districtcode").remove();
						dopertime = 0;
						for ( var i = 0; i < jsonarray.length; i++) {
							var json = jsonarray[i];
							var $addopt = $("<option value=''></option>");
							$addopt.val(json.territorycode);
							$addopt.text(json.territoryname);
							$("#citycode").append($addopt);
						}
					}
					if (cityno != "") {
						$("#citycode").val(cityno);
						$("#city").val("");
					}
					if (cityno != "" && districtno != "") {
						getdterritory();
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					tip("操作失败");
				}
			});
}
var dopertime = 0;
function getdterritory() {
	var districtno = $("#district").val();
	var territorycode = $("#citycode option:selected").val();
	territorycode = "1" + territorycode;
	$
			.ajax( {
				type : "post",
				url : "personalinfoController.do?getCTerritory",
				data : {
					territorycode : territorycode
				},
				dataType : "json",
				success : function(data) {
					var jsonarray = data.obj;
					if (jsonarray.length > 0 && dopertime == 0) {
						var $addrow = $("<select id='districtcode' name='districtcode'>"
								+ "<option value='''>请选择</option>"
								+ "</select>");
						$("#citycode").after($addrow);
						dopertime = 1;
					}
					if (jsonarray.length == 0 && opertime > 0) {
						$("#districtcode").remove();
						dopertime = 0;
					}
					if (jsonarray.length > 0) {
						$("#districtcode option").remove();
						$please = $("<option value=''>请选择</option>");
						$("#districtcode").append($please);
						for ( var i = 0; i < jsonarray.length; i++) {
							var json = jsonarray[i];
							var $addopt = $("<option value=''></option>");
							$addopt.val(json.territorycode);
							$addopt.text(json.territoryname);
							$("#districtcode").append($addopt);
						}
					}
					if (districtno != "") {
						$("#districtcode").val(districtno);
						$("#district").val("");
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					tip("操作失败");
				}
			});
}