const express = require("express");
const fileUpload = require("express-fileupload");
const path = require("path");
const fs = require("fs");
const shell = require("shelljs");
const cors = require("cors");
const app = express();

const port = 8069;
const dirPath = path.join(__dirname, "csvs-to-upload");

// Sanity check
if (!fs.existsSync(dirPath)) {
    fs.mkdirSync(dirPath);
}

app.use(cors());
app.use(express.json());
app.use(fileUpload());

app.post("/upload", async (request, response) => {
    if (!request.files) return response.status(400).send("No files found");
    let file = request.files.csv;

    function ftp(file, dir) {
        return new Promise((resolve, reject) => {
            file.mv(path.join(dir, file.name), (err) => {
                if (err) {
                    reject(err);
                } else resolve();
            });
        });
    }

    try {
        await ftp(file, dirPath);
        let test = shell.exec(path.join(__dirname, "main.sh"));
        console.log("Shell output:", test);

        setTimeout(() => {
            // Check if the output is valid JSON
            if (test.startsWith("[") && test.endsWith("]")) {
                try {
                    let parsedOutput = JSON.parse(test.replace(/\\/g, ""));
                    return response.status(200).send(parsedOutput);
                } catch (error) {
                    console.error("Error parsing JSON:", error);
                    return response.status(500).send({ error: "Error parsing JSON", details: test });
                }
            } else {
                // Output is not JSON
                return response.status(200).send({ output: test });
            }
        }, 2000);
    } catch (error) {
        console.log(error);
        return response.status(500).send({ error: "Internal server error" });
    }
});

app.post('/first-approval', async (req, res) => {
    const { filename } = req.body;

    if (!filename) {
        return res.status(400).json({ error: 'Filename is required' });
    }

    const filePath = path.join(dirPath, filename);

    try {
        // Execute bash script using shell.exec
        let command = `${path.join(__dirname, 'main.sh')} ${filename}`;
        console.log(`Executing command: ${command}`);
        shell.exec(path.join(__dirname, 'main.sh') + ` ${filename}`, (error, stdout, stderr) => {
            if (error) {
                console.error(`Error executing script: ${error}`);
                return res.status(500).json({ error: 'Error executing script' });
            }
            console.log(`Script output: ${stdout}`);
            console.error(`Script errors: ${stderr}`);

            // Respond with success message
            return res.status(200).json({ message: 'Approval process started successfully', output: stdout });
        });
    } catch (error) {
        console.error(`Error handling approval request: ${error}`);
        return res.status(500).json({ error: 'Internal server error' });
    }
});

app.listen(port, () => {
    console.log("Worko :)");
});
