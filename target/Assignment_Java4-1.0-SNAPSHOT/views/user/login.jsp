<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <style>

        .form-group .label {
            font-size: 12px;
            text-transform: uppercase;
            letter-spacing: 1px;
            color: #000;
            font-weight: 700;
        }

        button[type="submit"].form-control:hover {
            background: #b7b7a4;
            border: #b7b7a4;
        }

        .form-control {
            height: 48px;
            background: rgba(0, 0, 0, 0.05);
            color: #000;
            font-size: 16px;
            border-radius: 50px;
            -webkit-box-shadow: none;
            box-shadow: none;
            border: 1px solid transparent;
            padding-left: 20px;
            padding-right: 20px;
            -webkit-transition: all 0.2s ease-in-out;
            -o-transition: all 0.2s ease-in-out;
            transition: all 0.2s ease-in-out;
        }

        .ftco-section {
            padding: 12rem 0;
        }
    </style>
</head>
<body>
<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-8 offset-4 m-auto" style="box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;">
                <div class="row">
                    <div class="col-5 text-wrap p-5 text-center d-flex align-items-center order-md-last"
                         style="background-color: #b7b7a4">
                        <div class="text w-100">
                            <h2>Welcome to login</h2>
                            <p>Don't have an account?</p>
                            <a href="#" class="btn btn-white btn-outline-dark">Sign Up</a>
                        </div>
                    </div>
                    <div class="col-7 login-wrap p-5">
                        <div class="d-flex">
                            <div class="w-100">
                                <h3 class="mb-4 text-center">Login</h3>
                            </div>
                        </div>
                        <c:if test="${!empty sessionScope.errorMess}">
                            <div class="d-flex">
                                <div class="w-100">
                                    <p class="alert alert-danger">${sessionScope.errorMess}</p>
                                </div>
                            </div>
                            <c:remove var="errorMess" scope="session"/>
                        </c:if>
                        <form action="/Assignment_Java4/login" method="post" class="login-form">
                            <div class="form-group mb-3">
                                <label class="label mb-1">Email</label>
                                <input type="email" class="form-control" name="email-login" placeholder="Email"
                                       required>
                            </div>
                            <div class="form-group mb-3">
                                <label class="label mb-1">Password</label>
                                <input type="password" class="form-control" name="password-login" placeholder="Password"
                                       autocomplete="on"
                                       required>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="form-control btn btn-primary submit px-3 btn-submit">Sign
                                    In
                                </button>
                            </div>
                            <div class="form-bottom d-flex justify-content-between align-items-center mt-3">
                                <div class="form-group">
                                    <input type="checkbox" name="remember" id="remember" style="cursor: pointer" value="true">
                                    <label class="checkbox-wrap checkbox-primary mb-0" for="remember"
                                           style="cursor: pointer">
                                        Remember Me
                                    </label>
                                </div>
                                <div class="form-group ">
                                    <a href="#" class="text-dark">Forgot Password</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%--<script src="/Assignment_Java4/assets/vendor/jquery/jquery-3.2.1.min.js"></script>--%>
<%--<script>--%>
<%--    $(document).ready(function () {--%>
<%--        $(".btn-submit").click(function (e) {--%>

<%--            const email = $("input[name='email-login']").val();--%>
<%--            const password = $("input[name='password-login']").val();--%>

<%--            $.ajax({--%>
<%--                url: "/Assignment_Java4/login",--%>
<%--                type: 'POST',--%>
<%--                data: {--%>
<%--                    email: email,--%>
<%--                    password: password--%>
<%--                },--%>
<%--                success: function (data) {--%>
<%--                    if ($.isEmptyObject(data.error)) {--%>
<%--                        alert(0);--%>
<%--                    } else {--%>
<%--                        alert(1);--%>
<%--                    }--%>
<%--                }--%>
<%--            });--%>


<%--        });--%>


<%--    });--%>
<%--</script>--%>
</body>
</html>
