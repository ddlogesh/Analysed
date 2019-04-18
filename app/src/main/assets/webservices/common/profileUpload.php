<?php

header('Content-Type: application/json');

$userRole = $_POST['userRole'];
if($userRole == "1")
	$target_file_name = '/var/www/analysed.in/analysed/Pages/avatars/' . basename($_FILES['file']['name']);
else if($userRole == "0")
	$target_file_name = '/var/www/analysed.in/analysed/Pages/jobseeker/user-images/' . basename($_FILES['file']['name']);

if(move_uploaded_file($_FILES['file']['tmp_name'], $target_file_name)) 
{
	$status = 1;
	$message = "Successfully uploaded";
}
else 
{
      $status = 0;
      $message = "Error while uploading";
}

$res = array('code' => $status, 'message' => $message);
echo json_encode($res, JSON_PRETTY_PRINT);

?>