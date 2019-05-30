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

$username = $_GET['username'];
 
$sql = "select * from task where email = '" . $username . "'";
$result = mysqli_query($con, $sql);

$json_array = array();

if($result->num_rows > 0){

	$row_array = array();

	while($row = mysqli_fetch_assoc($result))
	{
		$row_array[id]= intval($row['id']);
		$row_array[email]= $row['email'];
		$row_array[taskId]= $row['taskId'];
		$row_array[seeker_email]= $row['seeker_email'];
		$row_array[task_name]= $row['task_name'];
		$row_array[task_desription]= $row['task_desription'];		
		$row_array[task_time]= $row['task_time'];
		$row_array[end_task]= $row['end_task'];
		$row_array[task_end]= $row['task_end'];
		$row_array[file]= $row['file'];
		$row_array[t_score]= intval($row['t_score']);
		$row_array[score]= intval($row['score']);
		$row_array[c_date]= $row['c_date'];
	    
		array_push($json_array,$row_array);
	}
	
	$res= json_encode($json_array,JSON_PRETTY_PRINT);

	echo ($res);
} 
else {
    echo ("Could not access the data : " . mysqli_error($result));
}

mysqli_close($con);

?>