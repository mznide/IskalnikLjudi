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
		<form method="post" action="isciServlet">
			<img alt="My banner" src="css/clovecki.png" /> <br /> <input
				type="text" name="nameOfPerson" placeholder="Ime in priimek"
				class="imePriimek"> <input type="submit"
				class="styled-button-9" value="Poišči osebo">
		</form>
	</div>
	<div class="right">
		<div class="right1">
		<a
				href="isciServlet?nameOfPerson=Borut Pahor">Borut Pahor</a>, <a
				href="isciServlet?nameOfPerson=Janez Ahlin">Janez Ahlin</a>, <a
				href="isciServlet?nameOfPerson=Peter Čeferin">Peter Čeferin</a>, <a
				href="isciServlet?nameOfPerson=Cvitan Goran">Cvitan Goran</a>, <a
				href="isciServlet?nameOfPerson=Zupanc Unita">Zupanc Unita</a>, <a
				href="isciServlet?nameOfPerson=Janez Novak">Janez Novak</a>, <a
				href="isciServlet?nameOfPerson=Matej Žnideric">Matej Žnideric</a>,
				<a href="isciServlet?nameOfPerson=Prelc Alenka">Alenka Prelc</a>,
				<a href="isciServlet?nameOfPerson=Ralca Katarina">Nenad Latinović</a>,
				<a href="isciServlet?nameOfPerson=Ralca Katarina">Katarina Ralc</a>,
				<a href="isciServlet?nameOfPerson=Vučko Marija">Vučko Marija</a>,
				<a href="isciServlet?nameOfPerson=Marjan Hrušovar">Marjan Hrušovar</a>,
				<a href="isciServlet?nameOfPerson=Janez Verk">Janez Verk</a>,
				<a href="isciServlet?nameOfPerson=Jelena Čivić">Jelena Čivić</a>,
				<a href="isciServlet?nameOfPerson=Janez Tekavc">Janez Tekavc</a>
		</div>
	</div>
</div>
<div id="footer"></div>
</body>
</html>