<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addInterestargBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delInterestargBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addInterestargBtn').bind('click', function(){   
 		 var tr =  $("#add_interestarg_table_template tr").clone();
	 	 $("#add_interestarg_table").append(tr);
	 	 resetTrNum('add_interestarg_table');
	 	 
    });  
	$('#delInterestargBtn').bind('click', function(){   
      	$("#add_interestarg_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_interestarg_table'); 
     
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	
		//将表格的表头固定
	    $("#interestarg_table").createhftable({
	    	height:'200px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addInterestargBtn" href="#">添加</a> <a id="delInterestargBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="interestarg_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号 </td>
		<td align="left" bgcolor="#EEEEEE">参数名</td>
		<td align="left" bgcolor="#EEEEEE">参数类型</td>
		<td align="left" bgcolor="#EEEEEE">参数长度</td>
		<td align="left" bgcolor="#EEEEEE">备注</td>
		
	</tr>
	<tbody id="add_interestarg_table">	
	<c:if test="${fn:length(interestargList)  <= 0 }">
			
   			<tr>
				<td align="center"><input style="width: 20px;" type="checkbox" name="ck" /></td>
				<td align="left"><input name="interestargList[0].argnames" maxlength="32" type="text" style="width: 120px;"datatype="*"></td>
				<td align="left"><input name="interestargList[0].argtypes" maxlength="100" type="text" style="width: 120px;"datatype="*"></td>
				<td align="left"><input name="interestargList[0].arglengths" maxlength="100" type="text" style="width: 120px;"datatype="*"></td>
				<td align="left"><input name="interestargList[0].remark" maxlength="200" type="text" style="width: 120px;" datatype="*"></td>
			</tr>
	</c:if>
	<c:if test="${fn:length(interestargList)  > 0 }">
		<c:forEach items="${interestargList}" var="poVal" varStatus="stuts">
			
   			<tr>
					<td align="center"><input style="width: 20px;" type="checkbox" name="ck" /></td>
					<td align="left"><input name="interestargList[${stuts.index }].argnames" maxlength="50" type="text" value="${poVal.argnames }" style="width: 120px;"datatype="*"></td>
					<td align="left"><input name="interestargList[${stuts.index }].argtypes" maxlength="32" type="text" value="${poVal.argtypes }" style="width: 120px;"datatype="*"></td>
					<td align="left"><input name="interestargList[${stuts.index }].arglengths" maxlength="32" type="text" value="${poVal.arglengths }" style="width: 120px;"datatype="*"></td>
					<td align="left"><input name="interestargList[${stuts.index }].remark" maxlength="100" type="text" value="${poVal.remark }" style="width: 120px;"datatype="*"></td>
				</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
