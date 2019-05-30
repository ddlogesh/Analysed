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

$seeker_email = $_GET['seeker_email'];
$sql = "select * from e_book where seeker_email = '" . $seeker_email . "' order by id desc"; 
$result = mysqli_query($con, $sql);
$json_array = array();

if($result->num_rows > 0){

	$row_array = array();

	while($row = mysqli_fetch_assoc($result))
	{
		$row_array[id]= intval($row['id']);
		$row_array[seeker_email]= $row['seeker_email'];
		$row_array[email]= $row['email'];
		$row_array[eventId]= intval($row['eventId']);
		$row_array[rdate]= $row['rdate'];
		$row_array[event_name]= $row['event_name'];		
		$row_array[event_venue]= $row['event_venue'];
		$row_array[event_date]= $row['event_date'];
		$row_array[review]= $row['review'];
		$row_array[notif]= intval($row['notif']);
		$row_array[remainder]= $row['remainder'];

		$sql1 = "select * from e_book where eventId = $row_array[eventId]"; 
		$result1 = mysqli_query($con, $sql1);
		$row_array[ppl_count]= mysqli_num_rows($result1); 
	
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