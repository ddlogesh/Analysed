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

$jobid = $_GET['jobid'];
 
$sql = "select * from jobseekers inner join jobapplications on jobseekers.user_id=jobapplications.userid where jobapplications.jobid='$jobid'"; 
$result = mysqli_query($con, $sql);
$json_array = array();

if($result){
	$row_array = array();
	while($row = mysqli_fetch_array($result))
	{
   		$row_array[fname]= $row['fname'];
		$row_array[Email]= $row['Email'];
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