<?php

$servername = "localhost";
$username = "analyzed";
$password = "V{_vih1vek94!)";
$dbname = "analyzed";

$id = $_POST['id'];

$con = mysqli_connect($servername,$username,$password,$dbname);
 
if($con === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}

$date=new DateTime("now");
$df=$date->format('Y-m-d H:i:s');
$sql="UPDATE joblistings SET endon='" . $df . "' WHERE id=$id";
 
$result = mysqli_query($con, $sql);
if ($result) 
	echo "done";

mysqli_close($con);
?>