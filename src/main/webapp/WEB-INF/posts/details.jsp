<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Post Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <div class="d-flex justify-content-around">
        <h3>Hello, <c:out value="${user.full_name}"/> </h3>
        <a href="/posts">Posts</a>
        <a href="/post/create">New Post</a>
    </div>
    <h5><c:out value="${post.headline}"/></h5>
    <h5><c:out value="${post.description}"/></h5>
    <div class="t-flex">

        <p> number of likes: <c:out value="${post.users.size()}"/></p>
        <p>People who liks this post:</p>
        <ul>
            <c:forEach var="liker" items="${post.users}">
                <li><c:out value="${liker.username}"/></li>
            </c:forEach>

        </ul>

        <c:set value="${post.users}" var="likers"/>
            <c:choose>
                <c:when test="${likers.contains(user)}">
                    <form  action="/post/${id}/dislike" method="post">
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

    </div>


</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>