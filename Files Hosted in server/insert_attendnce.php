<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields
if(isset($_POST['date']) && isset($_POST['time']) && isset($_POST['standard']) && isset($_POST['present']))
 {
    
    
	$date=$_POST['date'];
	$time=$_POST['time'];
	$standard=$_POST['standard'];
	$present=$_POST['present'];
    // include db connect class
    require 'config.php';

    // connecting to db
  

    // mysql inserting a new row
     $sql_query="INSERT INTO attendance(Date,Time,Standard,Present) VALUES('$date','$time','$standard','$present');";
    $result = mysqli_query($con,$sql_query);
  

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Successful";

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