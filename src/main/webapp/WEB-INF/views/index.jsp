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

<div class="container">
    <p><a href="/accident/create">Создать новую заявку</a></p>
</div>

<div class="container">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Редактировать</th>
            <th scope="col">Заявитель</th>
            <th scope="col">Адрес</th>
            <th scope="col">ГосНомер</th>
            <th scope="col">Описание</th>
            <th scope="col">Фото</th>
            <th scope="col">Статус</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${list}" varStatus="loopCounter" >
            <tr>
                <td>
                        ${item.id}
                </td>

                <td>
                    <a href='<c:url value="/update?id=${item.id}"/>'>редактировать</a>
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
                        Здесь скоро будет фото
                </td>

                <td>
                        ${item.status}
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>