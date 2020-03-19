<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields
if(isset($_POST['hdate']) && isset($_POST['htime']) && isset($_POST['hdes']) && isset($_POST['std']) && isset($_POST['fid']))
 {
    
    
	$hdate=$_POST['hdate'];
	$htime=$_POST['htime'];
	$hdes=$_POST['hdes'];
    $std=$_POST['std'];
	$fid=$_POST['fid'];
    // include db connect class
    require 'config.php';

    // connecting to db
  

    // mysql inserting a new row
     $sql_query="INSERT INTO homework(H_Date,H_Time,H_Description,Standard,F_Id) VALUES('$hdate','$htime','$hdes','$std','$fid');";
    $result = mysqli_query($con,$sql_query);
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Homework successfully created.";
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