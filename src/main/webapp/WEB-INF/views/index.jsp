<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <title>Accident</title>
</head>
<body>

<br><br>

<div class="container">
    <p>Вы вошли как ${user.username}</p>
    <p><a href="/accident/logout">Выйти (logout)</a></p>
</div>

<br><br>

<div class="container">
    <p><a href="/accident/create">Создать новую заявку</a></p>
</div>

<br><br>

<div class="container">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Изменить</th>
            <th scope="col">Заявитель</th>
            <th scope="col">Адрес</th>
            <th scope="col">ГосНомер</th>
            <th scope="col">Описание</th>
            <th scope="col">Тип</th>
            <th scope="col">Статьи</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${list}" varStatus="loopCounter" >
            <tr>
                <td>
                        ${item.id}
                </td>

                <td>
                    <a href='<c:url value="/update?id=${item.id}"/>'>изменить</a>
                </td>

                <td>
                        ${item.name}
                </td>

                <td>
                        ${item.address}
                </td>

                <td>
                        ${item.number}
                </td>

                <td>
                        ${item.description}
                </td>

                <td>
                        ${item.type.name}
                </td>

                <td>
                    <c:forEach var="rule" items="${item.rules}" >
                        ${rule.name} <br>
                    </c:forEach>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>