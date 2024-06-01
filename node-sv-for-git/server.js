const express = require("express")
const fileUpload = require("express-fileupload")
const path = require("path")
const fs = require("fs")
const shell = require("shelljs")
const cors = require("cors")
const app = express() 

const port = 8069
const dirPath = path.join(__dirname, "csvs-to-upload")

// Sanity check
if (!fs.existsSync(dirPath)) {
    fs.mkdirSync(dirPath)
}

app.use(cors())

app.use(express.json())
app.use(fileUpload())

app.post("/upload", async (request, response) => {
    if(!request.files) return response.status(400).send("no files found")
    let file = request.files.csv
    let test

    function ftp (file, dir) {
        return new Promise ((resolve, rejects) => {
            file.mv(path.join(dir, file.name), (err)=> {
                if(err) {
                    rejects(err)
                }
                else resolve()
            })
        })
    }

    try {
        await ftp(file, dirPath)
        test = shell.exec(path.join(__dirname, "main.sh"))
    } catch (error) {
        console.log(error)
    }
    setTimeout(() => {
        console.log("waiting for presentation")
        return response.status(200).send(JSON.parse(test.replace(/\\/g, ""))) // ????? (replaces \ with nothing)
    }, 2000);
})

app.listen(port, () => {
    console.log("Worko :)")
})