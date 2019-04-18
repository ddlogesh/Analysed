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
$userRole = $_GET['userRole'];

$row_array = array();
if($userRole == "1"){
	$sql = "SELECT * FROM recruiters WHERE email = '$email'";
	$result = mysqli_query($con, $sql);

	if($result){
		$row = mysqli_fetch_assoc($result);
		if($row){
			$row_array[fname]= $row['fname'];
			$row_array[lname]= $row['lname'];
			$row_array[referal]= $row['referal'];
			$row_array[phone]= $row['phone'];
			$row_array[organisation]= $row['organisation'];
			$row_array[designation]= $row['designation'];
			$row_array[address]= $row['address'];
			$row_array[picture]= $row['picture'];
		}
	}	
}
else if($userRole == "0"){
	$sql = "SELECT * FROM jobseekers WHERE Email = '$email'";
	$result = mysqli_query($con, $sql);

	if($result){
		$row = mysqli_fetch_assoc($result);
		if($row){
			$row_array[fname]= $row['fname'];
			$row_array[lname]= $row['lname'];
			$row_array[referal]= $row['referal'];
			$row_array[Qualification]= $row['Qualification'];
			$row_array[yearofpassing]= $row['yearofpassing'];
			$row_array[Experience]= $row['Experience'];
			$row_array[location]= $row['location'];
			$row_array[PhNumber]= $row['PhNumber'];
			$row_array[resume]= $row['resume'];
			$row_array[resumename]= $row['resumename'];
			$row_array[skills]= $row['skills'];
			$row_array[picture]= $row['picture'];
		}
	}	
}
$res= json_encode($row_array,JSON_PRETTY_PRINT);
echo ($res);

mysqli_close($con);

?>