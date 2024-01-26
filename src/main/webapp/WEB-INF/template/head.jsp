<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= Objects.isNull(request.getAttribute("pageTitle")) ? "Livre d'or" : request.getAttribute("pageTitle") %></title>
<link href="css/normalize.css" rel="stylesheet">
<link href="css/skeleton.css" rel="stylesheet">
</head>
<body>