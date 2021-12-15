<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Posts</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <div class="d-flex justify-content-around">
        <h4>Hello, <c:out value="${user.full_name}"/>!</h4>
        <a href="/logout ">Logout</a>
        <a href="/post/create">New Post</a>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Headline</th>
            <th scope="col">Post</th>
            <th scope="col">Number of Likes</th>
            <th scope="col">Like</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${posts}" var="post">
            <tr>
                <td><a href="/post/${post.id}/details"><c:out value="${post.headline}"></a> </c:out></td>
                <td><c:out value="${post.description}"/></td>
                <td><c:out value="${post.users.size()}"/> </td>
                <td>
                    <c:set value="${post.users}" var="likers"/>
                    <c:choose>
                        <c:when test="${likers.contains(user)}">
                            <form  action="/post/${post.id}/dislike" method="post">
                                <input type="hidden" name="_method" value="delete">
                                <button  class="btn btn-danger">Dislike</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form  action="/post/${post.id}/like" method="post">
                                <input type="hidden" name="_method" value="post">
                                <button class="btn btn-info btn-lg">Like</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:set var = "authorId" value = "${post.user.id}"/>
                    <c:set var = "userId"  value = "${user.id}"/>
                    <c:if test="${authorId==userId}">
                        <div class="d-flex justify-content-around">
                            <a href="/post/${post.id}/edit"><button class="btn btn-dark">Edit</button></a>
                            <form  action="/post/${post.id}" method="post">
                                <input type="hidden" name="_method" value="delete">
                                <button class="btn btn-dark">Delete</button>
                            </form>
                        </div>

                    </c:if>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>