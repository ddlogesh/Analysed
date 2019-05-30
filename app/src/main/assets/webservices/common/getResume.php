<?php

header('Content-Type: application/json');

$servername = "localhost";
$username = "analyzed";
$password = "V{_vih1vek94!)";
$dbname = "analyzed";

$con = mysqli_connect($servername,$username,$password,$dbname); 
if($con === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}

$email = $_GET['email'];

$sql = "SELECT * FROM jobseekers WHERE Email = '$email'";

$result = mysqli_query($con, $sql);

$status = 0;
$message = "";

if($result){
	$row = mysqli_fetch_assoc($result);
	if($row){
		$status = 1;
		$message = $row['resumename'];
	}
}
$res = array('code' => $status, 'message' => $message);
echo json_encode($res, JSON_PRETTY_PRINT);

mysqli_close($con);

?>