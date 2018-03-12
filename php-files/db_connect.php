<?php

$con = mysqli_connect("localhost:8889", "root", "root", "db_images") or die("Could not connect");
$sql = "SELECT max(id) as id FROM images";
$result = mysqli_fetch_array(mysqli_query($con,$sql));
echo $result['id'];

// mysqli_query($con,"INSERT INTO Persons (FirstName,LastName,Age)
// VALUES ('Glenn','Quagmire',33)");
$sql = "INSERT INTO images (id, url, name) VALUES (NULL, 'file url here', 'name here');";
//adding the path and name to database
echo "<pre>Debug: $sql</pre>\n";
$result = mysqli_query($con, $sql);
if ( false===$result ) {
  printf("error: %s\n", mysqli_error($con));
}
else {
  echo 'done.';
}

mysqli_close($con);
?>
