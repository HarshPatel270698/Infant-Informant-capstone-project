<?php

    $response = array();
    require 'config.php';

    if(isset($_GET["sgr"])) 
    {
        $sgr = $_GET['sgr'];


        $sql_query="SELECT timetable.Month,timetable.Year,timetable.IMAGE_name,timetable.T_Id FROM student,timetable where student.S_GR_Number='$sgr' and student.Standard=timetable.Standard;";
         $result = mysqli_query($con,$sql_query);
    
    
        if(mysqli_num_rows($result) > 0)
        {
            $response["timetable"] = array();
        
            while($Rows = mysqli_fetch_assoc($result))
            {
                $timetable  = array();
            
                $timetable = $Rows;
                array_push($response["timetable"],$timetable);
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