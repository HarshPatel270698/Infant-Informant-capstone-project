<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields
if(isset($_POST['sgr']) && isset($_POST['std']) && isset($_POST['question']))
 {
    $sgr=$_POST['sgr'];
    $std=$_POST['std'];
	$question=$_POST['question'];
    // include db connect class
    require 'config.php';

    // connecting to db
  

    // mysql inserting a new row
     $sql_query="INSERT INTO query_detail(S_GR_Number,Std,Q_Question,isActive) VALUES('$sgr','$std','$question','0');";
    $result = mysqli_query($con,$sql_query);
  

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Query successfully created.";

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