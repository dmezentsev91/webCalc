<%@ page import="WebCalc1.LogicWebProgram" %>
<% LogicWebProgram calculator = new LogicWebProgram(); %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>WebCalc</title>
    <meta charset="utf-8">
    <%--<link href="${pageContext.request.contextPath}/css/calc.css" rel="stylesheet" type="text/css">--%>
    <style>
        <%@include file='css/calc.css' %>
    </style>
</head>
<body>

<div>
    <fieldset>

        <form id="form6" name="form6" method="GET" action="mypage.jsp">

            <input style="width : 338pt" value="<%=calculator.nullExpression(request.getParameter("expression"))%>"
                   name="expression" type="text" id="expression" placeholder="put expression"
                   autofocus>

            <input type="button" name="button" id="button" value="BackSpace" onClick="backSpace()">
            <input type="button" name="button13" id="button13" value="Clear" onClick="ccclear()">

            <input style="width : 338pt " type="text" name="textfield" id="textfield"
                   value="<%=calculator.mainMethod(request.getParameter("expression"))%>" placeholder="result">

            <input style="width:166pt" name="button12" type="submit" id="button12" value="=">
        </form>


    </fieldset>
</div>
<div>

    <fieldset>
        <form id="form2" name="form2" method="post">
            <input name="button7" type="button" id="button7" value="7" onClick="addSymbol('7')">
            <input type="button" name="button8" id="button8" value="8" onClick="addSymbol('8')">
            <input type="button" name="button9" id="button9" value="9" onClick="addSymbol('9')">
            <input type="button" name="button15" id="button15" value="+" onClick="addSymbol('+')">
            <input type="button" name="button18" id="button18" value="sqrt()" onClick="addSymbol('sqrt()')">
            <input type="button" name="button19" id="button19" value="sin()" onClick="addSymbol('sin()')">
            <input type="button" name="button20" id="button20" value="Pi" onClick="addSymbol('Pi')">
        </form>
        <form id="form3" name="form3" method="post">
            <input type="button" name="button4" id="button4" value="4" onClick="addSymbol('4')">
            <input name="button5" type="button" id="button5" value="5" onClick="addSymbol('5')">
            <input type="button" name="button6" id="button6" value="6" onClick="addSymbol('6')">
            <input type="button" name="button14" id="button14" value="-" onClick="addSymbol('-')">
            <input type="button" name="button21" id="button21" value="^2" onClick="addSymbol('^2')">
            <input type="button" name="button22" id="button22" value="cos()" onClick="addSymbol('cos()')">
            <input type="button" name="button23" id="button23" value="e" onClick="addSymbol('e')">
        </form>
        <form id="form4" name="form4" method="post">
            <input type="button" name="button1" id="button1" value="1" onClick="addSymbol('1')">
            <input type="button" name="button2" id="button2" value="2" onClick="addSymbol('2')">
            <input type="button" name="button3" id="button3" value="3" onClick="addSymbol('3')">
            <input type="button" name="button16" id="button16" value="*" onClick="addSymbol('*')">
            <input type="button" name="button24" id="button24" value="^n" onClick="addSymbol('^n')">
            <input type="button" name="button25" id="button25" value="tan()" onClick="addSymbol('tan()')">
            <input type="button" name="button26" id="button26" value="(" onClick="addSymbol('(')">
        </form>
        <form id="form5" name="form5" method="post">
            <input type="button" name="button10" id="button10" value="0" onClick="addSymbol('0')">
            <input type="button" name="button11" id="button11" value="." onClick="addSymbol('.')">
            <input type="button" name="button100" id="button100" value="00" onClick="addSymbol('00')">
            <input type="button" name="button17" id="button17" value="/" onClick="addSymbol('/')">
            <input type="button" name="button27" id="button27" value="n!" onClick="addSymbol('!')">
            <input type="button" name="button28" id="button28" value="ctan()" onClick="addSymbol('ctan()')">
            <input type="button" name="button29" id="button29" value=")" onClick="addSymbol(')')">
        </form>
    </fieldset>
</div>

</body>
</html>

<script type="text/javascript">
    function addSymbol(s) {

        var carPos = getCaretPosition(document.getElementById('expression'));
        var str = document.getElementById("expression").value;

        var result = str.substring(0, carPos) + s + str.substring(carPos, str.length);
        document.getElementById("expression").value = result;
        if (s.indexOf(")") + 1)
            setCaretPosition("expression", carPos + s.length - 1);
        else
            setCaretPosition("expression", carPos + s.length);
    }
    function backSpace() {
        var str = document.getElementById('expression').value;
        var carPos = getCaretPosition(document.getElementById('expression'));
        document.getElementById('expression').value = str.substring(0, carPos - 1) + str.substring(carPos, str.length);
        setCaretPosition("expression",carPos - 1);
    }
    function ccclear() {

        document.getElementById('expression').value = '';
    }
    function getCaretPosition(ctrl) {
        var CaretPos = 0;
        if (document.selection) {
            ctrl.focus();
            var Sel = document.selection.createRange();
            Sel.moveStart('character', -ctrl.value.length);
            CaretPos = Sel.text.length;
        }

        else if (ctrl.selectionStart || ctrl.selectionStart == '0')
            CaretPos = ctrl.selectionStart;
        return (CaretPos);
    }

    function setCaretPosition(elemId, caretPos) {
        var elem = document.getElementById(elemId);

        if (elem != null) {
            if (elem.createTextRange) {
                var range = elem.createTextRange();
                range.move('character', caretPos);
                range.select();
            }
            else {
                if (elem.selectionStart) {
                    elem.focus();
                    elem.setSelectionRange(caretPos, caretPos);
                }
                else
                    elem.focus();
            }
        }
    }

</script>

</body>
</html>
