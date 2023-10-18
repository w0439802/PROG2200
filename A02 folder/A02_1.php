<?php
    $grade = $_GET["Grade"];
?>

<!DOCTYPE html>
<HTML>

<HEAD>
    <TITLE>Rounding Form</TITLE>
</HEAD>

<BODY>

<FORM action="" method="GET">
        Input Grade <INPUT type="text" name="Grade" step="any" value="" /><br />
        <INPUT TYPE="submit" name="form1" value="submit" /><br />
</FORM>
        <?php
            if ($grade == null)
            {
                echo "Please, input a value";
            } else {
                if(is_numeric($grade)) { 
                    if ($grade >= 85) {
                        echo "Your grade letter would be A";
                    } else if ($grade >= 75) { 
                        echo "Your grade letter would be B";
                    } else if ($grade >=60) {
                        echo "Your grade letter would be C";
                    } else {
                        echo "Your grade letter would be F";
                    }
                }
                elseif (is_string($grade)) {
                    switch($grade) {
                        case "A":
                            echo "85%-100%";
                            break;
                        case "B":
                            echo "75%-84.99%";
                            break;
                        case "C":
                            echo "60%-74.99%";
                            break;
                        case "F":
                            echo "0%-59.99%";
                            break;
                        default:
                            echo "That isnt a letter that I can use, I can use A,B,C and F";
                    }
                } 
            }
        ?>
</BODY>