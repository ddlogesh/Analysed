<?php

header('Content-Type: application/json');

$servername = "localhost";
$username = "analyzed";
$password = "V{_vih1vek94!)";
$dbname = "analyzed";
$table = "users";

$con = mysqli_connect($servername,$username,$password,$dbname);
 
if($con === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}

$_POST = json_decode(file_get_contents('php://input'), TRUE);

if($_POST) {
	$username= $_POST['username'];
	$password= $_POST['password'];
	$UserRole= $_POST['UserRole'];
	$verified= $_POST['verified'];

	$sql = "SELECT * FROM $table WHERE username = '$username'";
 
	$result = mysqli_query($con, $sql);

	if($result->num_rows === 0){
		$sql = "INSERT INTO $table (username,password,UserRole,verified) VALUES ('$username','$password','$UserRole',$verified)";
		$result = mysqli_query($con, $sql);

		if ($result) {
			$user_id = mysqli_insert_id($con);

			$status = $user_id;
			$message = "Successfully inserted";
		}
		else {
			$status = -1;
			$message = "Cannot insert the data";
		}
	}
	else {
		$status = -1;
		$message = "User already exists";
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