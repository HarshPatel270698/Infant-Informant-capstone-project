<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields
if(isset($_GET['lid']))
 {
    $lid=$_GET['lid'];
    // include db connect class
    require 'config.php';

    // connecting to db
  

    // mysql inserting a new row
     $sql_query="UPDATE  leave_detail SET L_Accepted_Or_Declined='1' WHERE L_Id=$lid";
    $result = mysqli_query($con,$sql_query);
  

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Leave accepted.";

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