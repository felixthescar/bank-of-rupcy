#!/bin/bash

# Local variables
addr=172.17.0.1:8088
path_to_csv=$(pwd)/csvs-to-upload

function sendEmail() {
    # Email details
    TO_EMAIL="felixscarmoc@yahoo.com"
    FILE_NAME=$1
    BASE_NAME=$(basename "$FILE_NAME")
    SUBJECT="Approval Required for $BASE_NAME"

    # Read contents of the file into a table format with dynamic headers and data
    if [ -f "$FILE_NAME" ]; then
        # Read the header from the first line and dynamically generate table headers
        HEADER=$(head -n 1 "$FILE_NAME" | awk -F ',' '{
            printf "<tr>"
            for (i=1; i<=NF; i++) {
                printf "<th>%s</th>", $i
            }
            printf "</tr>"
        }')
        
        # Read data rows and dynamically generate table rows
        DATA=$(tail -n +2 "$FILE_NAME" | awk -F ',' '{
            printf "<tr>"
            for (i=1; i<=NF; i++) {
                printf "<td>%s</td>", $i
            }
            printf "</tr>"
        }')
        
        # Combine header and data into a table
        FILE_CONTENT="<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\" style=\"border-collapse: collapse; margin: 0 auto;\">$HEADER$DATA</table>"
    else
        echo "File $FILE_NAME not found." >&2
        exit 1
    fi

    # HTML content for the email body
    HTML_BODY=$(cat <<EOF
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Approval Request</title>
<style>
    /* Center align the table */
    table {
        margin: 0 auto;
    }
</style>
</head>
<body>
    <div style="text-align: center; margin-top: 50px;">
        <h2>Approval Request</h2>
        <p>Your approval is requested for the file <strong>$BASE_NAME</strong>.</p>
        <p>File Contents:</p>
        $FILE_CONTENT
        <p>Please review the information and click the button below to approve:</p>
        <a href="http://10.241.109.82:5174/" style="padding: 10px 20px; background-color: #007bff; color: #fff; text-decoration: none; border-radius: 5px;">Approve File</a>
    </div>
</body>
</html>
EOF
    )

    # Create email content
    EMAIL_CONTENT=$(cat <<EOF
To: $TO_EMAIL
Subject: $SUBJECT
Content-Type: text/html

$HTML_BODY
EOF
    )

    # Log email content for debugging
    echo "Sending email to $TO_EMAIL with subject $SUBJECT"
    echo "$EMAIL_CONTENT"

    # Send email using msmtp with explicit config file
    echo -e "$EMAIL_CONTENT" | msmtp --file=/root/.msmtprc $TO_EMAIL

    # Check if email was sent successfully
    if [ $? -eq 0 ]; then
        echo "Email sent successfully."
    else
        echo "Failed to send email." >&2
    fi
}

function sendApprovalEmail() {
    # Email details
    TO_EMAIL="felixscarmoc@yahoo.com"
    FILE_NAME=$1
    BASE_NAME=$(basename "$FILE_NAME")
    SUBJECT="Approval Confirmation for $BASE_NAME"

    # Read contents of the file into a table format with dynamic headers and data
    if [ -f "$FILE_NAME" ]; then
        # Read the header from the first line and dynamically generate table headers
        HEADER=$(head -n 1 "$FILE_NAME" | awk -F ',' '{
            printf "<tr>"
            for (i=1; i<=NF; i++) {
                printf "<th>%s</th>", $i
            }
            printf "</tr>"
        }')
        
        # Read data rows and dynamically generate table rows
        DATA=$(tail -n +2 "$FILE_NAME" | awk -F ',' '{
            printf "<tr>"
            for (i=1; i<=NF; i++) {
                printf "<td>%s</td>", $i
            }
            printf "</tr>"
        }')
        
        # Combine header and data into a table
        FILE_CONTENT="<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\" style=\"border-collapse: collapse; margin: 0 auto;\">$HEADER$DATA</table>"
    else
        echo "File $FILE_NAME not found." >&2
        exit 1
    fi

    # HTML content for the email body
    HTML_BODY=$(cat <<EOF
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Approval Confirmation</title>
<style>
    /* Center align the table */
    table {
        margin: 0 auto;
    }
    /* Magenta accents */
    h2 {
        color: #ff00ff;
    }
    a {
        padding: 10px 20px;
        background-color: #ff00ff;
        color: #fff;
        text-decoration: none;
        border-radius: 5px;
    }
    a:hover {
        background-color: #cc00cc;
    }
</style>
</head>
<body>
    <div style="text-align: center; margin-top: 50px;">
        <h2>Approval Confirmation</h2>
        <p>Your request for the file <strong>$BASE_NAME</strong> has been approved.</p>
        <p>The information inside the file has been successfully uploaded into the database.</p>
        <p>File Contents:</p>
        $FILE_CONTENT
    </div>
</body>
</html>
EOF
    )

    # Create email content
    EMAIL_CONTENT=$(cat <<EOF
To: $TO_EMAIL
Subject: $SUBJECT
Content-Type: text/html

$HTML_BODY
EOF
    )

    # Log email content for debugging
    echo "Sending email to $TO_EMAIL with subject $SUBJECT"
    echo "$EMAIL_CONTENT"

    # Send email using msmtp with explicit config file
    echo -e "$EMAIL_CONTENT" | msmtp --file=/root/.msmtprc $TO_EMAIL

    # Check if email was sent successfully
    if [ $? -eq 0 ]; then
        echo "Email sent successfully."
    else
        echo "Failed to send email." >&2
    fi
}

function uploadedEmail() {
    # Email details
    TO_EMAIL="felixscarmoc@yahoo.com"
    SUBJECT="Approval Required"
    FILE_NAME=$1
    BASE_NAME=$(basename "$FILE_NAME")

    # Read contents of the file into a table format with dynamic headers and data
    if [ -f "$FILE_NAME" ]; then
        # Read the header from the first line and dynamically generate table headers
        HEADER=$(head -n 1 "$FILE_NAME" | awk -F ',' '{
            printf "<tr>"
            for (i=1; i<=NF; i++) {
                printf "<th>%s</th>", $i
            }
            printf "</tr>"
        }')
        
        # Read data rows and dynamically generate table rows
        DATA=$(tail -n +2 "$FILE_NAME" | awk -F ',' '{
            printf "<tr>"
            for (i=1; i<=NF; i++) {
                printf "<td>%s</td>", $i
            }
            printf "</tr>"
        }')
        
        # Combine header and data into a table
        FILE_CONTENT="<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\" style=\"border-collapse: collapse; margin: 0 auto;\">$HEADER$DATA</table>"
    else
        echo "File $FILE_NAME not found." >&2
        exit 1
    fi

    # HTML content for the email body
    HTML_BODY=$(cat <<EOF
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Approval Request</title>
<style>
    /* Center align the table */
    table {
        margin: 0 auto;
    }
</style>
</head>
<body>
    <div style="text-align: center; margin-top: 50px;">
        <h2>Approval Request</h2>
        <p>Your approval is requested for the file <strong>$BASE_NAME</strong>.</p>
        <p>File Contents:</p>
        $FILE_CONTENT
        <p>Please review the information and click the button below to approve:</p>
        <a href="http://localhost:5174/" style="padding: 10px 20px; background-color: #007bff; color: #fff; text-decoration: none; border-radius: 5px;">Approve File</a>
    </div>
</body>
</html>
EOF
    )

    # Create email content
    EMAIL_CONTENT=$(cat <<EOF
To: $TO_EMAIL
Subject: $SUBJECT
Content-Type: text/html

$HTML_BODY
EOF
    )

    # Log email content for debugging
    echo "Sending email to $TO_EMAIL with subject $SUBJECT"
    echo "$EMAIL_CONTENT"

    # Send email using msmtp with explicit config file
    echo -e "$EMAIL_CONTENT" | msmtp --file=/root/.msmtprc $TO_EMAIL

    # Check if email was sent successfully
    if [ $? -eq 0 ]; then
        echo "Email sent successfully."
    else
        echo "Failed to send email." >&2
    fi
}


function prepCSV() {
    local filename="$path_to_csv/$table.csv"
    
    if [ -f "$filename" ]; then
        # found=true
        local current_time=$(date +%s) # Get current time in Unix timestamp format
        local new_filename="$filename.$current_time"
        
        mv "$filename" "$new_filename"
        echo "$new_filename"
    fi
    # else
    #     # found=false
    #     echo ""
    # fi
}

function addRowsFromCSV() {
    local file_path="$1"
    local header=()
    local first_line=true
    
    while IFS=, read -r line; do
        if [ "$first_line" = true ]; then
            # Read header line and split into array
            IFS=',' read -r -a header <<< "$line"
            first_line=false
        else
            # Process data lines
            IFS=',' read -r -a object <<< "$line"
            local row='{'

            for ((i=0; i<${#header[@]}; i++)); do
                row="${row}\"${header[i]}\":\"${object[i]}\","
            done

            row=${row::-1}'}'

            curl -H "Content-Type: application/json" -d "$row" "$addr/$request" -s -o /dev/null
        fi
    done <"$file_path"
    rm -rf "$file_path"
}

function addRowsFromCSVNode() {
    local file_path="$1"
    local header=()
    local first_line=true
    local array=()

    while IFS=, read -r line; do
        if [ "$first_line" = true ]; then
            # Read header line and split into array
            IFS=',' read -r -a header <<< "$line"
            first_line=false
        else
            # Process data lines
            IFS=',' read -r -a object <<< "$line"
            local row='{'

            for ((i=0; i<${#header[@]}; i++)); do
                row="${row}\"${header[i]}\":\"${object[i]}\","
            done

            row=${row::-1}'}'
            array+=("$row")
        fi
    done <"$file_path"
    
    if [ "${#array[@]}" -gt 0 ]; then
        # Join array elements with comma to form JSON array
        local json_array="["
        for ((i=0; i<${#array[@]}; i++)); do
            json_array="${json_array}${array[i]}"
            if [ $i -lt $((${#array[@]} - 1)) ]; then
                json_array="${json_array},"
            fi
        done
        json_array="${json_array}]"
        
        echo "$json_array"
    else
        echo "[]"  # Output empty array if no rows matched the condition
    fi
}

function main() {
    if [ -z "$1" ]; then
        while IFS=',' read -r table keyword request; do
            new_filename=$(prepCSV)
            
            if [ -n "$new_filename" ]; then
                addRowsFromCSVNode "$new_filename"
                # Call sendEmail silently
                sendEmail "$new_filename" > /dev/null 2>&1 &
            fi
        done < "$(pwd)/tables-and-keywords.csv"
    else
        local filename="$1"
        while IFS=',' read -r table keyword request; do
          if [ "$table" == "${filename%%.*}" ]; then
            addRowsFromCSV "$path_to_csv/$filename"
            # Call sendEmail silently
            sendApprovalEmail "$path_to_csv/$filename" > /dev/null 2>&1 &
          fi
        done < "$(pwd)/tables-and-keywords.csv"
    fi
    
    exit 0
}

main "$@"
