<?php

	require 'config.php';

    $sql_query="SELECT * FROM attendance;";
    $result = mysqli_query($con,$sql_query);
	
	
	if(mysqli_num_rows($result) > 0)
	{
		$response["attendance"] = array();
		
		while($Rows = mysqli_fetch_assoc($result))
		{
			$attendance  = array();
			
			$attendance = $Rows;
			array_push($response["attendance"],$attendance);
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