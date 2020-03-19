<?php

	require 'config.php';

    $sql_query="SELECT * FROM leave_detail where L_Accepted_Or_Declined='0';";
    $result = mysqli_query($con,$sql_query);
	
	$leave_detail=null;
	if(mysqli_num_rows($result) > 0)
	{
		$response["leave_detail"] = array();
		
		while($Rows = mysqli_fetch_assoc($result))
		{
			$leave_detail  = array();
			
			$leave_detail = $Rows;
			array_push($response["leave_detail"],$leave_detail);
		}
		$response["success"] = 1;
		
	}
	else
	{
	    
	    $response["success"] = 0;
	
	}
	
	echo json_encode($response);
?>