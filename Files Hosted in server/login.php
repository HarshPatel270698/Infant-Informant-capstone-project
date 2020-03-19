<?php

	require 'config.php';

    $sql_query="SELECT * FROM student;";
    $result = mysqli_query($con,$sql_query);
	
	
	if(mysqli_num_rows($result) > 0)
	{
		$response["student"] = array();
		
		while($Rows = mysqli_fetch_assoc($result))
		{
			$student  = array();
			
			$student = $Rows;
			array_push($response["student"],$student);
		}
		$response["success"] = 1;
		
	}
	else
	{
		$response["Error"] = "No Any Users Found";
		echo "not done";
	}
	
	echo json_encode($response);
?>