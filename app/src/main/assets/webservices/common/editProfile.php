<?php

$servername = "localhost";
$username = "analyzed";
$password = "V{_vih1vek94!)";
$dbname = "analyzed";

$title = $_POST['title'];
$text = $_POST['text'];
$userRole = $_POST['userRole'];
$email = $_POST['email'];

$con = mysqli_connect($servername,$username,$password,$dbname);
 
if($con === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}

if($userRole == "1")
	$sql = "UPDATE recruiters SET $title = '$text' WHERE email = '$email'";
else if($userRole == "0")
	$sql = "UPDATE jobseekers SET $title = '$text' WHERE Email = '$email'";

$result = mysqli_query($con, $sql);
if ($result) 
	echo "done";

mysqli_close($con);
?>