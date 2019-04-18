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
 
$sql = "SELECT * FROM joblistings WHERE postedby='" . $username . "'";
$result = mysqli_query($con, $sql);

$json_array = array();

if($result->num_rows > 0){

	$row_array = array();

	while($row = mysqli_fetch_assoc($result))
	{
		$row_array[id]= intval($row['id']);
		$row_array[position]= $row['position'];
		$row_array[skills_req]= $row['skills_req'];
		$row_array[qual_req]= $row['qual_req'];
		$row_array[exp_req]= $row['exp_req'];
		$row_array[job_location]= $row['job_location'];
		$row_array[job_description]= $row['job_description'];
		$row_array[package]= $row['package'];
		$row_array[job_time]= $row['job_time'];
		$row_array[postedby]= $row['postedby'];
		$row_array[createdon]= $row['createdon'];
		$row_array[endon]= $row['endon'];
	    
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