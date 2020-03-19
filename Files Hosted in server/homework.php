<?php

    $response = array();
	require 'config.php';

    if(isset($_GET["std"])) 
    {
        $std = $_GET['std'];


        $sql_query="SELECT * FROM homework where Standard='$std';";
         $result = mysqli_query($con,$sql_query);
	
	
	    if(mysqli_num_rows($result) > 0)
	    {
		    $response["homework"] = array();
		
		    while($Rows = mysqli_fetch_assoc($result))
		    {
			    $homework  = array();
			
			    $homework = $Rows;
			    array_push($response["homework"],$homework);
		    }
	        $response["success"] = 1;

            echo json_encode($response);
        } 
        else 
        {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No record found";

            // echo no users JSON
            echo json_encode($response);
        }
    }
   

 else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>