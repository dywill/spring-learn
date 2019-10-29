<html>
<head>
    <script src="/js/jquery.js"></script>
</head>

<body>

converter:

<form action="/test/conversion" method="get">
    person: <input type="text" name="person"/>  submit: <input type="submit"/>
</form>

-----------------------------------------------------------<br>

format:

<form action="/test/format" method="get">
    name: <input type="text" name="name"/>
    birth: <input type="text" name="birth"/>

    submit: <input type="submit"/>
</form>

-----------------------------------------------------------<br>

validate:

<form action="/test/validate" method="get">
    name: <input type="text" name="name"/>
    birth: <input type="text" name="birth"/>

    submit: <input type="submit"/>
</form>

-----------------------------------------------------------<br>

entity:

<form action="/test/httpentity" method="post">
    name: <input type="text" name="name"/>
    birth: <input type="text" name="birth"/>

    submit: <input type="submit"/>
</form>

-----------------------------------------------------------<br>

json:<a id="testJson"> test json</a> ------ <a id="testJson001"> test json001</a>

<script>
    $(document).ready(function(){
        $("#testJson").click(function(){
            $.ajax({
                //请求方式
                type : "POST",
                //请求的媒体类型
                contentType: "application/dy;charset=UTF-8",
                //请求地址
                url : "/test/json",
                //请求成功
                success : function(result) {
                    console.log(result);
                },
                //请求失败，包含具体的错误信息
                error : function(e){
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        });

        $("#testJson001").click(function(){
            $.ajax({
                //请求方式
                type : "POST",
                //请求的媒体类型
                contentType: "application/dy;charset=UTF-8",
                //请求地址
                url : "/test/json001",
                //请求成功
                success : function(result) {
                    console.log(result);
                },
                //请求失败，包含具体的错误信息
                error : function(e){
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
        });
    });
</script>

-----------------------------------------------------------<br>

view:<a href="/test/view"> dy view </a>

-----------------------------------------------------------<br>

view:<a href="/test/exception?i=10"> excption view </a>

</body>
</html>
