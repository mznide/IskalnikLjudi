<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import ="java.util.ArrayList" %>
<%@page import="si.fri.iskalnikOseb.dataHelpers.FinanceStructure"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
request.setCharacterEncoding("UTF-8");
	String iskanaOseba = (String)session.getAttribute("iskanaOseba");
	String drugaOseba = (String)request.getParameter("skupnaOseba");
	FinanceStructure drugaOsebaStructure = FinanceStructure.getFinanceStructure(drugaOseba);

	
%>
<html>
<head>
    <link rel="stylesheet" href="css/skupniClanki.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Skupni clanki oseb  <%=iskanaOseba %> in <%=drugaOseba %></title>
</head>
<body>

	<div class="header">		
		<form method="post" action="IskanjeOsebeHandlerServlet" >
		<input type="text" name="nameOfPerson"  placeholder="Ime in priimek" class="imePriimek">
		<input type="submit" value="Isci osebo" class="styled-button-9">
		</form>
	</div>

<br />
<div class= clanki>
Prikaz skupnih člankov oseb <br /> <b> <%=iskanaOseba %> </b> in <b><%= drugaOseba %></b>: <br />
<% 
	if(drugaOsebaStructure!=null){
		ArrayList<String> clanki = drugaOsebaStructure.getClanki();
		int i=0;
		for (String clanek : clanki) {
			i++;
			out.println("<p>" +clanek +"</p>");
			if (i>=10)
				break;
		}
	}

%>

<br />

<script>
    document.write('<a href="' + document.referrer + '">Pojdi nazaj</a>');
</script>
</div>
</body>
</html>