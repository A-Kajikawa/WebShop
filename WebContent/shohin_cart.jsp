<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList" import="Bean.CartBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>カート画面</title>
</head>
<body>
<h1>カート内容</h1>
<button onclick="location.href='order_check.jsp'">これらの商品を注文する</button>
<table border="1">
<tr>
<th>商品名</th><th>容量</th><th>販売価格</th><th>数量</th><th>金額</th><th>取消</th>
</tr>
<jsp:useBean id="cartArrayBean" class="Bean.CartArrayBean" scope="session"/>
<%
ArrayList<CartBean> cartarrayBean = cartArrayBean.getCartArray();
for(CartBean rcd : cartarrayBean){
%>
<tr>
<td><%=rcd.getShohin_name() %></td>
<td><%=rcd.getShohin_val() %></td>
<td><%=rcd.getShohin_price() %></td>
<td><%=rcd.getQuantity() %></td>
<td><%=rcd.getTotalPrice() %></td>
<td>
<form action="DeleteCartServlet" method="get">
  <input type="hidden" name="code" value=<%=rcd.getShohin_code()%>>
  <input type="submit" value="取消"/>
  </form>
  </td>
</tr>
<%
}
%>
</table>
総合計：<%=cartArrayBean.getTotalAllPrice()%>
<br>
<input type="button" value="戻る"
			onclick="location.href='shohin_query.html'" />
</body>
</html>