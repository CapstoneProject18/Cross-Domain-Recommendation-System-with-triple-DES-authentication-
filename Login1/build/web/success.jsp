<%
    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
%>
<%--You are not logged in<br/>
<a href="index.jsp">Please Login</a>
<%} else {
%> --%>
Welcome 
<%--<%=session.getAttribute("userid")%><br><br><br> --%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>success</title>
    </head>
<body>
      <form action="${pageContext.request.contextPath}/myservlet1" method="post"> 
<tr>
     <td><br><br><br>Enter the OTP</td>
     <td><input type="text" name="otp" value="" /></td>
     
  </tr>
  <tr>
      <td><input type="submit" value="Submit" /></td> </tr>
<br><br><a href='logout.jsp'>Log out</a>
      </form>
</body>
</html>
<%
    }
%>