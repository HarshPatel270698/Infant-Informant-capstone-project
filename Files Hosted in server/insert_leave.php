<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields
if(isset($_POST['sgr']) && isset($_POST['lreason']) && isset($_POST['lstart']) && isset($_POST['lend']))
 {
    $sgr=$_POST['sgr'];
	$lreason=$_POST['lreason'];
    $lstart=$_POST['lstart'];
	$lend=$_POST['lend'];
    // include db connect class
    require 'config.php';

    // connecting to db
  

    // mysql inserting a new row
     $sql_query="INSERT INTO leave_detail(S_GR_Number,L_Reason,L_start,L_end,L_Accepted_Or_Declined) VALUES('$sgr','$lreason','$lstart','$lend','0');";
    $result = mysqli_query($con,$sql_query);
  

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "User successfully created.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>