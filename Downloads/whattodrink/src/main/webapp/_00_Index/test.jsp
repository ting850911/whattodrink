<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script language="javascript" type="text/javascript">
        var wsUri = "ws://localhost:8080/whattodrink/websocket";
        var output;
            function init() {
                output = document.getElementById("output");
                testWebSocket();
            }
            function testWebSocket() {
                websocket = new WebSocket(wsUri);
                websocket.onopen = function(evt) { 
                    onOpen(evt)
                    };
//                 websocket.onclose = function(evt) {
//                     onClose(evt)
//                 };
                websocket.onmessage = function(evt) {
                    onMessage(evt)
                };
                websocket.onerror = function(evt) {
                    onError(evt)
                };
            }
            function onOpen(evt) {
                writeToScreen("CONNECTED"); 
                doSend("WebSocket rocks"); 
            }  
//             function onClose(evt) { 
//                 writeToScreen("DISCONNECTED"); 
//             }  
            function onMessage(evt) { 
                writeToScreen('<span style="color: blue;">RESPONSE: ' + evt.data+'</span>'); 
              
            }  
            function onError(evt) { 
                writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data); 
            }  
            function doSend(message) { 
                writeToScreen("SENT: " + message);
                websocket.send(message); 
            }  
            function writeToScreen(message) { 
                var pre = document.createElement("p"); pre.style.wordWrap = "break-word"; pre.innerHTML = message; output.appendChild(pre); 
            }  
            window.addEventListener("load", init, false);  
        </script>  

<h2>WebSocket Test</h2>
<div id="output"></div>
</body>
</html>