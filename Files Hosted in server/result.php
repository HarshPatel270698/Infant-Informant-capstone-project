<?php

    $response = array();
	require 'config.php';

    if(isset($_GET["gr"])) 
    {
        $gr = $_GET['gr'];


        $sql_query="SELECT * FROM result_detail where S_GR_Number='$gr';";
         $result = mysqli_query($con,$sql_query);
	
	
	    if(mysqli_num_rows($result) > 0)
	    {
		    $response["result_data"] = array();
		
		    while($Rows = mysqli_fetch_assoc($result))
		    {
			    $result_data  = array();
			
			    $result_data = $Rows;
			    array_push($response["result_data"],$result_data);
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