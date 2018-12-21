<?php include_once './header.php'; ?>
<?php
$msg="";
if(isset($_POST["name"]))
{
    $name = filter_var($_POST["name"], FILTER_SANITIZE_MAGIC_QUOTES);
    $pass = filter_var($_POST["pass"], FILTER_SANITIZE_MAGIC_QUOTES);
    $query = "select * from tbl_users where name = '$name' and pass = '$pass' and status = 'approved'";
    $r = run_sql($query);    
    if(mysqli_num_rows($r)>0){
       $row = mysqli_fetch_array($r); 
       $_SESSION["name"]=$row["name"];
       $_SESSION["email"]=$row["email"];
      // $_SESSION["status"]=$row["status"];
       redirect("./success.php");
    }
    else {
        $err="Incorrect User Name or Password!!";
    }
}
?>
<div class="row">    
    <div class="col-sm-4 col-sm-offset-4">    
<h3 style="color: red;"><?php echo "$msg";?></h3>
<h1>Login</h1>
<form method="post" id="myform"  class="form-horizontal">
  <div class="form-group  has-feedback">
      <input required type="text" class="form-control" id="name" name='name' placeholder="Name"/>
      <span class="glyphicon glyphicon-user form-control-feedback"></span>
  </div>
  <div class="form-group has-feedback">
      <input  required type="password" class="form-control" id="pass" name='pass' placeholder="Password"/>
      <span class="glyphicon glyphicon-asterisk form-control-feedback"></span>
  </div> 
  <div class="form-group"> 
      <button type="submit" class="btn btn-primary" formaction = "./success.php"  style="background-color: #333333">Login</button>
  
      <button type="reset" class="btn btn-primary" style="background-color: #333333">Reset</button>
  </div>
    <div style="color: red; font-weight: bold;" class="form-group">
        <?=$err?>
    </div>
     <!-- <div  class="form-group">
        <a style="color: blue;" href="success.php">Forgot Password!</a>
    </div>  -->
    

</form>
</div>
</div>
<?php include_once './footer.php'; ?>