<?php
if(isset($_FILES['file'])) {
    $file_name = $_FILES['file']['name'];
    $file_tmp = $_FILES['file']['tmp_name'];
    $file_type = $_FILES['file']['type'];
    $file_size = $_FILES['file']['size'];
    $file_ext = strtolower(end(explode('.',$_FILES['file']['name'])));
    
    $extensions = array("jpeg","jpg","png");
    
    if(in_array($file_ext,$extensions) === false){
        echo "File extension not allowed, please choose a JPEG or PNG file.";
        exit();
    }
    
    if($file_size > 2097152) {
        echo 'File size must be less than 2 MB';
        exit();
    }
    
    $upload_dir = 'uploads/';
    
    if(move_uploaded_file($file_tmp,$upload_dir.$file_name)) {
        echo 'File uploaded successfully';
    } else {
        echo 'Error uploading file';
    }
}
?>
CREATE TABLE qrcodes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  content VARCHAR(255) NOT NULL,
  timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
<?php
// Establish database connection
$servername = 'localhost';
$username = 'your_username';
$password = 'your_password';
$dbname = 'your_database';

$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die('Connection failed: ' . $conn->connect_error);
}

// Get the QR code data from the POST request
$qrcode = $_POST['qrcode'];

// Insert the QR code data into the database
$sql = 'INSERT INTO qrcodes (content) VALUES (?)';
$stmt = $conn->prepare($sql);
$stmt->bind_param('s', $qrcode);
$stmt->execute();

$stmt
