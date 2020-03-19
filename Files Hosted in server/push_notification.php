<?php

/**
 * 
 */
	 $token = "fi5ehn6Kuvc:APA91bFXE18CgDkSqS0dfjVjYosup5fx6MInoxKRsx-8NgMbA8btH2czhopb4knJ6kVNBbBrZSS0sEi7gNZAfKDx2IjH8clnISNIQJHDC3mIeJ4VdhRs5PPLkjQOq9LwwfTkxQEHG9kJ";

	 $r = send_notification($token,"TEST","test");
	 echo $r;	
	function send_notification ($token, $title, $body) {

	$url = 'https://fcm.googleapis.com/fcm/send';
	$t = array($token);
	$fields = array(
				 'registration_ids' => $t,
				 "notification" => array(
				       "body" => "Body of the test",
				       "title" => "Test",
				       "sound" => "default"
				     ),
				 'data' => array(
				      "title" => $title,
				      "body" => $body
				    )
				);

			$headers = array(
				'Authorization:key = AAAArcDZmFw:APA91bHkAO3pguoV_fAGxvsRmPQsnwCEAbgrWgUMJ_0ETx4LK6uahOlo58xTTAj6Jpjo__XkGXMJkGRp-YtaOBph4XJs32upEkmXy8VZiwO0lsP2aMtpB0ABkGgvQ3g_c1I35oanzL3N ',
				'Content-Type: application/json'
				);

		   $ch = curl_init();
	       curl_setopt($ch, CURLOPT_URL, $url);
	       curl_setopt($ch, CURLOPT_POST, true);
	       curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
	       curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
	       curl_setopt ($ch, CURLOPT_SSL_VERIFYHOST, 0);  
	       curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
	       curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
	       $result = curl_exec($ch);           
	       if ($result === FALSE) {
	           die('Curl failed: ' . curl_error($ch));
	       }
	       curl_close($ch);
	      	return $result;


}



?>