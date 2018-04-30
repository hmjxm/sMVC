<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px; overflow: scroll;">
	    <img src=".${filepath }" onclick="closeWindow()" />
	</div>
</div>

<script type="text/javascript">

    function closeWindow() {
    	$("#previewImgWindow").window("close");
    }

</script>