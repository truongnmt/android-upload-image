<?php
include 'DatabaseConfig.php';
// Create connection
// $db_connect = mysql_connect("localhost","root", "truongke", "upload_image")
//     or die('Could not connect:');
    // mysql_select_db('upload_image', $db_connect) or die('Could not select database.');
$conn = mysqli_connect($HostName, $HostUser, $HostPass, $DatabaseName);
echo "connected";
echo $_POST;
if($_SERVER['REQUEST_METHOD'] == 'POST'){
  echo "incomming connect";
  $DefaultId = 0;
  $ImageData = $_POST['image_path'];
  $ImageName = $_POST['image_name'];
  $GetOldIdSQL ="SELECT id FROM UploadImageToServer ORDER BY id ASC";
  $Query = mysqli_query($conn,$GetOldIdSQL);
  while($row = mysqli_fetch_array($Query)){
    $DefaultId = $row['id'];
  }
  $ImagePath = "images/$DefaultId.png";
  $ServerURL = "http://localhost:8888/$ImagePath";
  $InsertSQL = "insert into UploadImageToServer (image_path,image_name) values ('$ServerURL','$ImageName')";
  if(mysqli_query($conn, $InsertSQL)){
    file_put_contents($ImagePath,base64_decode($ImageData));
    echo "Your Image 123 Has Been Uploaded.";
  }
  mysqli_close($conn);
}else{
  echo "Not Uploaded";
}

?>
