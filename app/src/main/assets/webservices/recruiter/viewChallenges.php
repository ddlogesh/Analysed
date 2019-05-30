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
 
$sql = "select * from challenge INNER join rchalllenge_record on challenge.email = rchalllenge_record.email where challenge.challengeId=rchalllenge_record.challangeId and challenge.email = '" . $username ."'";
$result = mysqli_query($con, $sql);

$json_array = array();

if($result->num_rows > 0){

	$row_array = array();

	while($row = mysqli_fetch_assoc($result))
	{
		$row_array[id]= intval($row['id']);
		$row_array[email]= $row['email'];
		$row_array[challengeId]= $row['challengeId'];
		$row_array[seeker_email]= $row['seeker_email'];
		$row_array[challenge_name]= $row['challenge_name'];
		$row_array[challenge_description]= $row['challenge_description'];		
		$row_array[winners]= $row['winners'];
		$row_array[challenge_time]= $row['challenge_time'];
		$row_array[end_challenge]= $row['end_challenge'];
		$row_array[challenge_end]= $row['challenge_end'];
		$row_array[file]= $row['file'];
		$row_array[t_score]= intval($row['t_score']);
		$row_array[score]= intval($row['score']);
	    
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