<?php

	require 'config.php';

    $sql_query="SELECT * FROM event;";
    $result = mysqli_query($con,$sql_query);
	
	
	if(mysqli_num_rows($result) > 0)
	{
		$response["event"] = array();
		
		while($Rows = mysqli_fetch_assoc($result))
		{
			$event  = array();
			
			$event = $Rows;
			array_push($response["event"],$event);
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