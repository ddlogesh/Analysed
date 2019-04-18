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
	$email= $_POST['email'];
	$challengeId = 'RC'.mt_rand(100000, 999999);
	$challenge_end= $_POST['challenge_end'];
	$challenge_name= $_POST['challenge_name'];
	$challenge_description= $_POST['challenge_description'];
	$winners= $_POST['winners'];
	$challenge_time= $_POST['challenge_time'];
	$cash= $_POST['cash'];
	$coupan= $_POST['coupan'];
	$service= $_POST['service'];
	$job= $_POST['job'];
	$other= $_POST['other'];

	$challenge_sender="Recruiter";
	$redirect="challenge-timer.php";

	$way= $_POST['way'];
	$hint= $_POST['hint'];

	$sql = "INSERT INTO challenge (email,challengeId,challenge_end,challenge_name,challenge_description,winners,challenge_time,cash,coupan,service,job,other) VALUES ('$email','$challengeId','$challenge_end','$challenge_name','$challenge_description','$winners','$challenge_time','$cash','$coupan','$service','$job','$other')";
	$result = mysqli_query($con, $sql);

	if ($result) {
		$sql1 = "INSERT INTO challange_notif (email,challangeId,challange_name,challange_sender,redirect) VALUES ('$email','$challengeId','$challenge_name','$challenge_sender','$redirect')";
		$result1 = mysqli_query($con, $sql1);

		if ($result1) {
			$sql2 = "INSERT into waynhint(uid,email,task_name,way,hint) VALUES ('$challengeId','$email','$challenge_name','$way','$hint')";
			$result2 = mysqli_query($con, $sql2);

			if ($result2) {
				$status = 1;
				$message = "Successfully inserted";
			}
			else {
				$status = -3;
				$message = "Cannot insert the data";
			}
		}
		else {
			$status = -2;
			$message = "Cannot insert the data";
		}
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