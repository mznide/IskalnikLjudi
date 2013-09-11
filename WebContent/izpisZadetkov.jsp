<%@page import="java.util.Iterator"%>
<%@page import="java.util.TreeMap"%>
<%@page import="si.fri.iskalnikOseb.dataHelpers.OdvetnikStructure"%>
<%@page import="si.fri.iskalnikOseb.dataHelpers.ZdravnikStructure"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="si.fri.iskalnikOseb.dataHelpers.SicrisStructure"%>
<%@page import="si.fri.iskalnikOseb.dataHelpers.TisStructure"%>
<%@page import ="java.util.ArrayList" %>
<%@page import="si.fri.iskalnikOseb.dataHelpers.FinanceStructure"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
 String iskanaOseba = (String)session.getAttribute("iskanaOseba");
 SicrisStructure[] osebeSicris = (SicrisStructure[])session.getAttribute("osebeSicris");
 TisStructure[] osebeTis = (TisStructure[])session.getAttribute("osebeTis");
 ZdravnikStructure zdravnik = (ZdravnikStructure)session.getAttribute("zdravnik");
 OdvetnikStructure odvetnik = (OdvetnikStructure)session.getAttribute("odvetnik");
 ArrayList<FinanceStructure> povezave= (ArrayList<FinanceStructure>)session.getAttribute("povezave");
 String[] clanki = (String[])session.getAttribute("clanki");
%>

<html>
<head>
    <link rel="stylesheet" href="css/izpisZadetkov.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Prikaz zadetkov za <%=iskanaOseba %></title>
</head>
<body>

		<div class="header">		
			<form method="post" action="IskanjeOsebeHandlerServlet" >
			<input type="text" name="nameOfPerson" value="<%=iskanaOseba %>" class="imePriimek">
			<input type="submit" value="Isci osebo" class="styled-button-9">
			</form>
		</div>


<p>Rezultati za osebo <% out.print(iskanaOseba); %>:</p>

<div class="clanki">
<h3>Seznam clankov v katerih je oseba omenjena</h3>
<div class="vir">
(vir: <a href="http://www.finance.si/">Finance.si</a>)
</div>
<%
if(clanki!=null){
	for (String clanek : clanki) {
		out.print("<p>");
		out.print(clanek);
		out.print("</p>");
	}
}
else {
	out.print("<p>Oseba ni omenjena v nobenem članku.</p>");
}
%>
</div>
<div class="povezave">
<h3>Seznam povezav</h3>
<div class="vir">
(vir: <a href="http://www.finance.si/">Finance.si</a>)
</div>
<%
if(povezave!=null){
	int i=0;
	for (FinanceStructure fs : povezave) {
		i++;
		if (i!=1){
		out.println("<a href=\"skupniClanki.jsp?skupnaOseba=" +fs.getImePovezave() +"\">" 
		+fs.getImePovezave() +"( " +fs.getStClankov() + " )</a> <br />" );
		if (i>=10)
			break;
		}
	}
}
else {
	out.print("<p>Oseba nima povezav.</p>");
}
%>
<img src="css/clovecki1.png" class="slikaFixed"></img>
</div>
<h3>Telefonski imenik Slovenije</h3>
<div class="vir">
(vir: <a href="http://www.itis.si/">Telefonski imenik Slovenije</a>)
</div>
<%
if(osebeTis!=null){ 
	int i =0;
	for (TisStructure osebaS : osebeTis) {
		i++;
		out.print("<p>");
		out.print("Ime: " +osebaS.getName() +"<br />");
		out.print("Telefon: " +osebaS.getPhoneNumber() +"<br />");
		out.print("Naslov: " +osebaS.getAddress() +"<br />");
		out.print("Posta: " + osebaS.getZip() +"<br />");
		out.print("</p>");
		if (i>=3)
			break;
	}
out.print("<a href=\"http://www.itis.si/\">Več podatkov</a>");
}
%>

<h3>Raziskovalec</h3>
<div class="vir">
(vir: <a href="http://www.sicris.si/">Sicris.si</a>)
</div>
<%
if (osebeSicris!=null) {
//	for (SicrisStructure osebaS : osebeSicris) {
		SicrisStructure osebaS = osebeSicris[0];
		out.print("<p>");
		out.print("Ime: " +osebaS.getName() +"<br />");
		out.print("Telefon: " +osebaS.getPhoneNumber() +"<br />");
		out.print("Fax stevilka: " +osebaS.getFaxNumber() +"<br />");
		out.print("Email: " + osebaS.getEmailAddress() +"<br />");
		out.print("Website: " + osebaS.getWebsite() +"<br /> </p>");
	//}
}
		

%>
<h3>Zdravnik</h3>
<div class="vir">
(vir: <a href="http://www.zzzs.si/zzzs/internet/zzzs.nsf/o/6F2CAD56EE119706C125770B00390171">ZZZS</a>)
</div>
<%
if(zdravnik!=null){
		out.print("<p>");
		out.print("Ime: " +zdravnik.getIme() +"<br />");
		out.print("OE: " +zdravnik.getNazivIzvajalca() +"<br />");
		out.print("Naziv izvajalca: " +zdravnik.getNazivIzvajalca() +"<br />");
		out.print("</p>");
}
%>

<h3>Odvetnik</h3>
<div class="vir">
(vir: <a href="http://www.odv-zb.si/imenik/imenik-odvetnikov">Odvetniška zbornica slovenije</a>)
</div>
<%
if(odvetnik!=null){
		out.print("<p>");
		out.print("Ime: " +odvetnik.getIme() +"<br />");
		out.print("Sluzbeni telefon: " +odvetnik.getTelefon() +"<br />");
		out.print("Naslov: " +odvetnik.getNaslov() +"<br />");
		out.print("Kraj: " + odvetnik.getKraj() +"<br />");
		out.print("</p>");
}
%>
</body>
</html>