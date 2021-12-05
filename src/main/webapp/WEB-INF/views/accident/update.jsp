<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form  action="<c:url value='/updateSave?id=${accident.id}'/>" method='POST'>
    <table>
        <tr>
            <td><label>ID (для инфо)
                <input type='text' name='${accident.id}' placeholder="${accident.id}" readonly>
            </label></td>
        </tr>

        <tr>
            <td><label>Заявитель (ФИО)
                <input type='text' name='name' placeholder="${accident.name}">
            </label></td>
        </tr>

        <tr>
            <td><label>Адрес нарушения
                <input type='text' name='address' placeholder="${accident.address}">
            </label></td>
        </tr>

        <tr>
            <td><label>ГосНомер
                <input type='text' name='number' placeholder="${accident.number}">
            </label></td>
        </tr>

        <tr>
            <td><label>Описание нарушения
                <input type='text' name='description' placeholder="${accident.description}">
            </label></td>
        </tr>

        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>
