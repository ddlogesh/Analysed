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
 
$sql = "SELECT user_id, fname, lname, picture, position, jobid, userid, Qualification, yearofpassing, Experience, location, Email, skills from jobseekers, joblistings, jobapplications where jobapplications.jobid=joblistings.id and jobapplications.userid=jobseekers.user_id and joblistings.postedby='" . $username . "'";
$result = mysqli_query($con, $sql);

$json_array = array();

if($result->num_rows > 0){

	$row_array = array();

	while($row = mysqli_fetch_assoc($result))
	{
		$sql2="SELECT id FROM jobseekers WHERE user_id='" . $row['user_id'] . "'";
		$result2 = mysqli_query($con, $sql2);
		if($result2){
			$row2 = mysqli_fetch_assoc($result2);
			$row_array[id]= intval($row2['id']);
		}

		$row_array[fname]= $row['fname'];
		$row_array[lname]= $row['lname'];
		$row_array[picture]= $row['picture'];
		$row_array[position]= $row['position'];
		$row_array[Qualification]= $row['Qualification'];
		$row_array[yearofpassing]= $row['yearofpassing'];
		$row_array[Experience]= $row['Experience'];
		$row_array[location]= $row['location'];
		$row_array[Email]= $row['Email'];
		$row_array[skills]= $row['skills'];
	    
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