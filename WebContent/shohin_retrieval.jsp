<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Bean.ShohinBean" import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品検索結果</title>
</head>
<body>
<h1>商品検索結果</h1>
検索方法：
<form action="ShohinQueryServlet" method="get">
<table>
<tr>
<%
String radio = request.getParameter("radio");
if(radio.equals("shohin")){

%>
<td><input type="radio" value="shohin" name="radio" checked="checked">商品名</td>
<td><input type="radio" value="price" name="radio">価格</td>
<%
}else{
%>
<td><input type="radio" value="shohin" name="radio">商品名</td>
<td><input type="radio" value="price" name="radio" checked="checked">価格</td>
<%
}
String keyword = request.getParameter("keyword");
%>
</tr>
</table>
<br><br>
キーワード：(商品名の一部、価格を入力してください)<br>
<input type="text" name="keyword" size="20" value="<%=keyword%>"><br><br>
<input type="submit" value="送信">
<input type="reset" value="リセット">
</form>
<table border="1">
<tr><th rowspan="2">商品画像</th><th>商品名</th><th>容量</th><th>販売価格</th><td rowspan="2"></td></tr>
<tr><th colspan="3">商品説明</th></tr>
<jsp:useBean id="shohinArrayBean" class="Bean.ShohinArrayBean" scope="session"/>
<%
ArrayList<ShohinBean> shohinarrayBean = shohinArrayBean.getShohinRecordArray();
for(ShohinBean rcd : shohinarrayBean){
%>
  <tr>
  <td rowspan="2"><img src="img/<%=rcd.getShohin_image()%>"></td>
  <td><%=rcd.getShohin_name()%></td>
  <td><%=rcd.getShohin_val()%></td>
  <td><%=rcd.getShohin_price()%></td>
  <td rowspan="2">
  <form action="ShohinDetailServlet" method="get">
  <input type="hidden" name="code" value=<%=rcd.getShohin_code()%>>
  <input type="submit" value="詳細"/>
  </form>
  </td>
  </tr>
  <tr>
  <td colspan="3"><%=rcd.getShohin_comment()%></td>
  </tr>
<%
}
%>
</table>
</body>
</html>