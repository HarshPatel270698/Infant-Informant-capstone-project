<?php

    $response = array();
	require 'config.php';

    if(isset($_GET["std"])) 
    {
        $std = $_GET['std'];


        $sql_query="SELECT * FROM query_detail where Std='$std' && isActive='0';";
         $result = mysqli_query($con,$sql_query);
	
	
	    if(mysqli_num_rows($result) > 0)
	    {
		    $response["query"] = array();
		
		    while($Rows = mysqli_fetch_assoc($result))
		    {
			    $query  = array();
			    $query = $Rows;
			    array_push($response["query"],$query);
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