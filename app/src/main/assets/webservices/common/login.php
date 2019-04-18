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
$password = $_GET['password'];
 
$sql = "SELECT * FROM users WHERE username = '$username' and password = '$password'"; 
$result = mysqli_query($con, $sql);

if($result){
	$row_array = array();
	$row = mysqli_fetch_assoc($result);

	if($row){
		$row_array[user_id]= intval($row['id']);
		$row_array[UserRole]= $row['UserRole'];

		if(strcmp($row['UserRole'], "1") === 0){
			$sql2 = "SELECT * FROM recruiters WHERE email = '$username'";
			$result2 = mysqli_query($con, $sql2);

			if($result2){
				$row2 = mysqli_fetch_assoc($result2);
				if($row2){
					$row_array[id]= intval($row2['id']);
					$row_array[fname]= $row2['fname'];
					$row_array[lname]= $row2['lname'];
					$row_array[referal]= $row2['referal'];
					$row_array[phone]= $row2['phone'];
					$row_array[organisation]= $row2['organisation'];
					$row_array[designation]= $row2['designation'];
					$row_array[address]= $row2['address'];
 					$row_array[picture]= $row2['picture'];
				}
			}
		}
		else if(strcmp($row['UserRole'], "0") === 0){
			$sql2 = "SELECT * FROM jobseekers WHERE Email = '$username'";
			$result2 = mysqli_query($con, $sql2);

			if($result2){
				$row2 = mysqli_fetch_assoc($result2);
				if($row2){
					$row_array[id]= intval($row2['id']);
					$row_array[fname]= $row2['fname'];
					$row_array[lname]= $row2['lname'];
					$row_array[referal]= $row2['referal'];
					$row_array[Qualification]= $row2['Qualification'];
					$row_array[yearofpassing]= $row2['yearofpassing'];
					$row_array[Experience]= $row2['Experience'];
					$row_array[location]= $row2['location'];
					$row_array[PhNumber]= $row2['PhNumber'];
					$row_array[resume]= $row2['resume'];
					$row_array[resumename]= $row2['resumename'];
					$row_array[skills]= $row2['skills'];
 					$row_array[picture]= $row2['picture'];
				}
			}
		}
		$res= json_encode($row_array,JSON_PRETTY_PRINT);
		echo ($res);		
	}
} 
else {
    echo ("Could not access the data : " . mysqli_error($result));
}

mysqli_close($con);

?>