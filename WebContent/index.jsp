<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/index.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Iskalnik ljudi</title>
</head>
<div>
	<div class="center">
		<form method="post" action="IskanjeOsebeHandlerServlet">
			<img alt="My banner" src="css/clovecki.png" /> <br /> <input
				type="text" name="nameOfPerson" placeholder="Ime in priimek"
				class="imePriimek"> <input type="submit"
				class="styled-button-9" value="Poišči osebo">
		</form>
	</div>
	<div class="right">
		<div class="right1">
		<a
				href="IskanjeOsebeHandlerServlet?nameOfPerson=Borut Pahor">Borut Pahor</a>, <a
				href="IskanjeOsebeHandlerServlet?nameOfPerson=Janez Ahlin">Janez Ahlin</a>, <a
				href="IskanjeOsebeHandlerServlet?nameOfPerson=Peter Čeferin">Peter Čeferin</a>, <a
				href="IskanjeOsebeHandlerServlet?nameOfPerson=Cvitan Goran">Cvitan Goran</a>, <a
				href="IskanjeOsebeHandlerServlet?nameOfPerson=Zupanc Unita">Zupanc Unita</a>, <a
				href="IskanjeOsebeHandlerServlet?nameOfPerson=Janez Novak">Janez Novak</a>, <a
				href="IskanjeOsebeHandlerServlet?nameOfPerson=Matej Žnideric">Matej Žnideric</a>,
				<a href="IskanjeOsebeHandlerServlet?nameOfPerson=Prelc Alenka">Alenka Prelc</a>,
				<a href="IskanjeOsebeHandlerServlet?nameOfPerson=Ralca Katarina">Nenad Latinović</a>,
				<a href="IskanjeOsebeHandlerServlet?nameOfPerson=Ralca Katarina">Katarina Ralc</a>,
				<a href="IskanjeOsebeHandlerServlet?nameOfPerson=Vučko Marija">Vučko Marija</a>,
				<a href="IskanjeOsebeHandlerServlet?nameOfPerson=Marjan Hrušovar">Marjan Hrušovar</a>,
				<a href="IskanjeOsebeHandlerServlet?nameOfPerson=Janez Verk">Janez Verk</a>,
				<a href="IskanjeOsebeHandlerServlet?nameOfPerson=Jelena Čivić">Jelena Čivić</a>,
				<a href="IskanjeOsebeHandlerServlet?nameOfPerson=Janez Tekavc">Janez Tekavc</a>
		</div>
	</div>
</div>
<div id="footer"></div>
</body>
</html>