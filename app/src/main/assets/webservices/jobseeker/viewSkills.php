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
$user_id = $_GET['user_id'];
$row_array = array();
$per_count = 0;		//percent count
 
$sql = "select * from task where score!=0 and seeker_email = '$email' order by id desc"; 
$result = mysqli_query($con, $sql);

$row_array[r_total]= intval(mysqli_num_rows($result));
$row_count = $row_array[r_total];	//total count

if($result){
	$tscore=0;
	$ttscore=0;

	while ($row = mysqli_fetch_assoc($result)) {
		$tscore += $row['score'];
		$ttscore += $row['t_score'];
	}
	$row_array[r_skill] = floatval(round(($tscore / $ttscore) * 100, 2));
	$per_count = $row_array[r_skill];
		
	$sql1="update jobseekers_skills set task_skill='$row_array[r_skill]' where user_id = '$user_id'";
	$result1 = mysqli_query($con, $sql1);

}

$sql = "select * from challenge where score!=0 and seeker_email ='$email'  order by id desc"; 
$result = mysqli_query($con, $sql);

$row_array[c_total]= intval(mysqli_num_rows($result));
$row_count += $row_array[c_total];

if($result){
	$tscore=0;
	$ttscore=0;

	while ($row = mysqli_fetch_assoc($result)) {
		$tscore += $row['score'];
		$ttscore += $row['t_score'];
	}
	$row_array[c_skill] = floatval(round(($tscore / $ttscore) * 100, 2));
	$per_count += $row_array[c_skill];

	$row_array[t_total] = intval($row_count);
	$row_array[t_skill] = floatval(round(($per_count/2),2));
		
	$sql1="update jobseekers_skills set challenge_skill='$row_array[c_skill]', total='$row_array[t_total]' where user_id = '$user_id'";
	$result1 = mysqli_query($con, $sql1);
}

$res= json_encode($row_array,JSON_PRETTY_PRINT);
echo ($res); 

mysqli_close($con);

?>