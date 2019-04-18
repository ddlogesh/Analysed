<?php

header('Content-Type: application/json');

$servername = "localhost";
$username = "analyzed";
$password = "V{_vih1vek94!)";
$dbname = "analyzed";
$table = "jobseekers";

$con = mysqli_connect($servername,$username,$password,$dbname);
 
if($con === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}

$_POST = json_decode(file_get_contents('php://input'), TRUE);

if($_POST) {
	$user_id= $_POST['user_id'];
	$fname= $_POST['fname'];
	$lname= $_POST['lname'];
	$Qualification= $_POST['Qualification'];
	$yearofpassing= $_POST['yearofpassing'];
	$Experience= $_POST['Experience'];
	$location= $_POST['location'];
	$Email= $_POST['Email'];
	$PhNumber= $_POST['PhNumber'];
	$resume= $_POST['resume'];
	$resumename= $_POST['resumename'];
	$skills= $_POST['skills'];
	$picture= $_POST['picture'];

	$sql = "INSERT INTO $table (user_id,fname,lname,Qualification,yearofpassing,Experience,location,Email,PhNumber,resume,resumename,skills,picture) VALUES ($user_id,'$fname','$lname','$Qualification','$yearofpassing','$Experience','$location','$Email','$PhNumber','$resume','$resumename','$skills','$picture')";
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