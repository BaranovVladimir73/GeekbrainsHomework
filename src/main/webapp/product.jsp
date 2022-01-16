<%@ page import="java.util.List" %>
<%@ page import="ru.gb.model.Product" %>
<%@ page contentType="Content-Type; text/html; character=utf-8" language = "java" %>

<html>
    <head>
    <title>Product</title>
    </head>
<body>
     <hr>
     <ul>
     <% for(Product value: (List<Product>)(request.getAttribute("product"))){ %>
        <li>id: <%=value.getId()%>; Title: <%=value.getTitle()%>; Cost: <%=value.getCost()%></li>
     <% } %>
    </ul>
    </hr>
</body>
</html>