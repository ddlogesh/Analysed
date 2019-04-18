<?php

header('Content-Type: application/json');

$servername = "localhost";
$username = "analyzed";
$password = "V{_vih1vek94!)";
$dbname = "analyzed";
$table = "recruiters";

$con = mysqli_connect($servername,$username,$password,$dbname);
 
if($con === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}

$_POST = json_decode(file_get_contents('php://input'), TRUE);

if($_POST) {
	$fname= $_POST['fname'];
	$lname= $_POST['lname'];
	$email= $_POST['email'];
	$phone= $_POST['phone'];
	$organisation= $_POST['organisation'];
	$designation= $_POST['designation'];
	$address= $_POST['address'];
	$picture= $_POST['picture'];

	$upper = strtoupper($fname);
	$upper1 = strtoupper($lname);
    	$b = str_split($upper);
    	$c = str_split($upper1);
    	$f = mt_rand(100000,999999);
    	$profileId =  $b[0].$c[0].$f;

	$sql = "INSERT INTO $table (fname,lname,referal,email,phone,organisation,designation,address,picture) VALUES ('$fname','$lname','$profileId','$email','$phone','$organisation','$designation','$address','$picture')";
	$result = mysqli_query($con, $sql);

	if ($result) {
		$id = mysqli_insert_id($con);

		$status = $id;
		$message = "Successfully inserted";
	}
	else {
		$status = -1;
		$message = "Cannot insert the data";
	}
}
else{
	$status=-1;
	$message="cannot parse the data";
}

$res = array('code' => $status, 'message' => $message);
echo json_encode($res, JSON_PRETTY_PRINT);
mysqli_close($con);

?>