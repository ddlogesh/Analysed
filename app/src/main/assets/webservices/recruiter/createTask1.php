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
	date_default_timezone_set('Asia/Kolkata');

	$email= $_POST['email'];
	$taskId = "RT".mt_rand(100000, 999999);
	$task_acc= $_POST['task_acc'];
	$task_name= $_POST['task_name'];
	$task_desription= $_POST['task_desription'];
	$task_end= $_POST['task_end'];
	$task_time= $_POST['task_time'];
	$c_date=date("y-m-d");

	$task_sender="Recruiter";
	$redirect="timer.php";

	$way= $_POST['way'];
	$hint= $_POST['hint'];

	$sql = "INSERT INTO task (email,taskId,seeker_email,task_acc,task_name,task_desription,task_end,task_time,c_date) VALUES ";
	$sql1 = "INSERT INTO task_notif (email,taskId,seeker_email,task_name,task_sender,redirect) VALUES ";

	foreach($_POST['seeker_email_list'] as $seeker_email) {
		$sql .= "('$email','$taskId','$seeker_email','$task_acc','$task_name','$task_desription','$task_end','$task_time','$c_date'),";
		$sql1 .= "('$email','$taskId','$seeker_email','$task_name','$task_sender','$redirect'),";
	}
	$sql = rtrim($sql,",");
	$sql1 = rtrim($sql1,",");

	$result = mysqli_query($con, $sql);

	if ($result) {
		$result1 = mysqli_query($con, $sql1);

		if ($result1) {
			$sql2 = "INSERT into waynhint(uid,email,task_name,way,hint) VALUES ('$taskId','$email','$task_name','$way','$hint')";
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