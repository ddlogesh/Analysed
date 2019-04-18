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

$_POST = json_decode(file_get_contents('php://input'), TRUE);

if($_POST) {
	$position= $_POST['position'];
	$skills_req= $_POST['skills_req'];
	$qual_req= $_POST['qual_req'];
	$exp_req= $_POST['exp_req'];
	$job_location= $_POST['job_location'];
	$job_description= $_POST['job_description'];
	$package= $_POST['package'];
	$job_time= $_POST['job_time'];
	$postedby= $_POST['postedby'];
	
	$sql = "INSERT INTO joblistings (position,skills_req,qual_req,exp_req,job_location,job_description,package,job_time,postedby) VALUES ('$position','$skills_req','$qual_req','$exp_req','$job_location','$job_description','$package','$job_time','$postedby')";
	$result = mysqli_query($con, $sql);

	if ($result) {
		$status = 1;
		$message = "Successfully inserted";
	}
	else {
		$status = -1;
		$message = "Cannot insert the data";
	}
}
else{
	$status=0;
	$message="cannot parse the data";
}

$res = array('code' => $status, 'message' => $message);
echo json_encode($res, JSON_PRETTY_PRINT);
mysqli_close($con);

?>