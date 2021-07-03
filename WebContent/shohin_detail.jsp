<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品詳細画面</title>
<style type="text/css">
table { width: 600px; }
th { padding: 3px; height: 50px; }
td { padding: 3px; height: 50px; }
th.cell_title { font-size: 150%; }
td.cell_image { width: 70%; }
td.cell_info { width: 30%; }
td.cell_comment { font-size: 120%;}
</style>
</head>
<body>
<h1>商品詳細</h1>
<jsp:useBean id="shohinBean" class="Bean.ShohinBean" scope="session"/>
<table border="1">
<tr>
<td colspan="2"><%=shohinBean.getShohin_name()%></td>
</tr>
<tr>
<td rowspan="4"><img src="img/<%=shohinBean.getShohin_image()%>"></td>
<td>内容量:<%=shohinBean.getShohin_val() %></td>
</tr>
<tr>
<td>価格:<%=shohinBean.getShohin_price() %></td>
</tr>
<tr>
<td>配達可能地域:<%=shohinBean.getShohin_area() %></td>
</tr>
<tr>
<td>
<%-- <select name="quantity">
<option value="1">1</option>
：
<option value="9">9</option>
</select>--%>
数量：<form action="AddCartServlet" method="post">
		<input type="hidden" name="code" value="<%= shohinBean.getShohin_code() %>" />
		<select name="quantity">
			<option value="1" selected>1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
		</select>
		<input type="submit" value="カートに追加する" />
	</form>
</td>
</tr>
<tr>
<td colspan="2"><%=shohinBean.getShohin_comment()%></td>
</tr>
</table>
<input type="button" value="戻る"
			onclick="location.href='shohin_query.html'" />

</body>
</html>