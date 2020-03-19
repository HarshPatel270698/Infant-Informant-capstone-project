<?php

    $response = array();
	require 'config.php';

    if(isset($_GET["standard"])) 
    {
        $standard = $_GET['standard'];


        $sql_query="SELECT * FROM student where Standard='$standard';";
         $result = mysqli_query($con,$sql_query);
	
	
	    if(mysqli_num_rows($result) > 0)
	    {
		    $response["standard_data"] = array();
		
		    while($Rows = mysqli_fetch_assoc($result))
		    {
			    $standard_data  = array();
			
			    $standard_data = $Rows;
			    array_push($response["standard_data"],$standard_data);
		    }
	        $response["success"] = 1;

            echo json_encode($response);
        } 
        else 
        {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No record found";

            // echo no users JSONs
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