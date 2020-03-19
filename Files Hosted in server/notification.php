<?php 
	  
    $response = array();
	require 'config.php';

    if(isset($_POST["Token"])) 
    {
        $_uv_Token = $_POST["Token"];


        $sql_query="INSERT INTO fcm (Token) VALUES ( '$_uv_Token') "
              ." ON DUPLICATE KEY UPDATE Token = '$_uv_Token';";
         $result = mysqli_query($con,$sql_query);
	
	
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