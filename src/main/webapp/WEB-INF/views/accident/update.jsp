<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form  action="<c:url value='/updateSave?id=${accident.id}'/>" method='POST'>
    <table>
        <tr>
            <td><label>ID (для инфо)
                <input type='text' name='${accident.id}' value="${accident.id}" readonly>
            </label></td>
        </tr>

        <tr>
            <td><label>Заявитель (ФИО)
                <input type='text' name='name' value="${accident.name}">
            </label></td>
        </tr>

        <tr>
            <td><label>Адрес нарушения
                <input type='text' name='address' value="${accident.address}">
            </label></td>
        </tr>

        <tr>
            <td><label>ГосНомер
                <input type='text' name='number' value="${accident.number}">
            </label></td>
        </tr>

        <tr>
            <td><label>Описание нарушения
                <input type='text' name='description' value="${accident.description}">
            </label></td>
        </tr>

        <tr>
            <td>Тип</td>
            <td>
                <select name="type.id" required>
                    <c:forEach var="type" items="${types}" >
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td>Статья(и)</td>
            <td>
                <select name="rIds" multiple required>
                    <c:forEach var="rule" items="${rules}" >
                        <option value="${rule.id}">${rule.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>
