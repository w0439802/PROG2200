<?php
    $size = $_GET["Size"];
    $Total;

?>

<!DOCTYPE html>
<HTML>

<HEAD>
    <TITLE>Rounding Form</TITLE>
</HEAD>

<BODY>

<FORM action="" method="GET">
        Pepperoni <INPUT TYPE="checkbox" id="Pepperoni" name="Pepperoni" step="any" value="1" /><br />
        Cheese <INPUT TYPE="checkbox" id="Cheese" name="Cheese" step="any" value="1" /><br />
        Olive <INPUT TYPE="checkbox" id="Olive" name="Olive" step="any" value="1" /><br />
        Pineapple <INPUT TYPE="checkbox" id="Pineapple" name="Pineapple" step="any" value="1" /><br />
        Ham <INPUT TYPE="checkbox" id="Ham" name="Ham" step="any" value="1" /><br />
        Size <INPUT list="Size" name="Size"/><br />
        <datalist id="Size">
            <option value="Extra-Large">
            <option value="Large">
            <option value="Medium">
            <option value="Small">
        </datalist>
        <INPUT TYPE="submit" name="form1" value="submit" /><br />
</FORM>

<?php

    if ($_GET['Pepperoni']) {
        $Total += 1;
    } else {
        
    }
    if ($_GET['Olive']) {
        $Total += 1;
    } else {
        
    }
    if ($_GET['Pineapple']) {
        $Total += 1;
    } else {
        
    }
    if ($_GET['Ham']) {
        $Total += 1;
    } else {
        
    }

    if ($size == "Extra-Large") {
        $Total += 17.50;
    } elseif ($size == "Large") {
        $Total += 15.00;
    } elseif ($size == "Medium") {
        $Total += 12.50;
    } elseif ($size == "Small") {
        $Total += 9.00;
    } else {
        echo("You need to select a size");
    }

    echo "<h2> Your Total now is $". $Total ;

?>

</BODY>