<?php

	require 'config.php';

    $sql_query="SELECT * FROM faculty;";
    $result = mysqli_query($con,$sql_query);
	
	
	if(mysqli_num_rows($result) > 0)
	{
		$response["faculty"] = array();
		
		while($Rows = mysqli_fetch_assoc($result))
		{
			$faculty  = array();
			
			$faculty = $Rows;
			array_push($response["faculty"],$faculty);
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